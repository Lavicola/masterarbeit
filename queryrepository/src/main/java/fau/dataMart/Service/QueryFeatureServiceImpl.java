package fau.dataMart.Service;

import fau.dataMart.Messages.QueryExecutionMessage;
import fau.dataMart.Messages.QueryExecutionPayload;
import fau.dataMart.Messages.QueryExecutionplanMessage;
import fau.dataMart.Messages.QueryExecutionplanPayload;
import fau.dataMart.dto.QueryExecutionDTO;
import fau.dataMart.dto.QueryExecutionplanDTO;
import org.springframework.core.env.Environment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fau.HashingStrategies.HashingStrategy;
import fau.HashingStrategies.HashingStrategyMD5;
import fau.deviceConfiguration.Repository.CostmodelRepository;
import fau.deviceConfiguration.Repository.RPUCapabilitiesRepository;
import fau.deviceConfiguration.models.Costmodel;
import fau.deviceConfiguration.models.RPUCapabilities;
import fau.dataMart.Repository.ExecutionplanRepository;
import fau.dataMart.Repository.MetadataRepository;
import fau.dataMart.Repository.QueryRepository;
import fau.dataMart.models.Query;
import fau.dataMart.models.Executionplan;
import fau.dataMart.models.ExecutionplanStep;
import fau.dataMart.models.Metadata;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class QueryFeatureServiceImpl implements QueryFeatureService {

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment environment;


    HashingStrategy hashingStrategy = new HashingStrategyMD5();

    @Autowired
    QueryRepository queryRepository;

    @Autowired
    ExecutionplanRepository executionplanRepository;

    @Autowired
    CostmodelRepository costmodelRepository;

    @Autowired
    RPUCapabilitiesRepository rpuCapabilitiesRepository;

    @Autowired
    MetadataRepository metadataRepository;

    public QueryFeatureServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    @Transactional
    public boolean BeginStoringProcess(String executionplan, String statement, LocalDateTime time) {

        String executionplanHash = this.hashingStrategy.hash(executionplan);
        String statementHash = this.hashingStrategy.hash(statement);

        Executionplan executionplan1;
        Query query;
        String TABLE = "tableName";
        String table_name;
        ArrayList<ExecutionplanStep> executionplanStep = new ArrayList<>();
        ArrayList<Metadata> metadata = new ArrayList<>();

        ArrayList<JsonNode> found_referenced_tables = new ArrayList<>(); // references to tables
        Set<String> tables_stored = new HashSet<>();

        JsonNode jsonNode;
        Metadata queryMeta;
        // first make a json object out of it
        try {
            jsonNode = new ObjectMapper().readTree(executionplan);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        executionplan1 = this.executionplanRepository.findByHash(executionplanHash);
        if (executionplan1 == null) {
            // new plan and Steps!
            executionplan1 = new Executionplan(executionplanHash, executionplan);
            // prepare everything for extraction Step
            String DEVICE_ID = "deviceId";
            String OPERATOR = "operatorType";
            ArrayList<JsonNode> found_steps = new ArrayList<>(); // execution plan steps
            RPUCapabilities rpuCapabilities;
            Costmodel costmodel;
            traverseAndGetJsonNodes(jsonNode, Arrays.asList(DEVICE_ID, OPERATOR), found_steps);
            for (int i = 0; i < found_steps.size(); i++) {
                JsonNode step = found_steps.get(i);
                String operator = step.get(OPERATOR).toString();
                long device_id = step.get(DEVICE_ID).asLong();
                costmodel = this.costmodelRepository.findCostmodelAtTimeForDevice((long) 1, time);
                rpuCapabilities = this.rpuCapabilitiesRepository.findRPUCapabilitiesAtTimeForDevice(device_id, time);
                executionplanStep.add(new ExecutionplanStep(operator, i, step.toString(), executionplan1, costmodel, rpuCapabilities));
            }
        }
        // Query Part
        traverseAndGetJsonNodes(jsonNode, Arrays.asList(TABLE), found_referenced_tables);
        for (int i = 0; i < found_referenced_tables.size(); i++) {
            table_name = found_referenced_tables.get(i).get(TABLE).toString();
            if (tables_stored.contains(table_name)) {
                continue;
            }
            tables_stored.add(table_name);

            queryMeta = this.metadataRepository.findByName(table_name);
            if (queryMeta == null) {
                metadata.add(new Metadata(table_name));
            } else {
                metadata.add(queryMeta);
            }
        }

        for (ExecutionplanStep step : executionplanStep) {
            executionplan1.addExectuionplanStep(step);
        }
        this.executionplanRepository.save(executionplan1);
        query = new Query(statement, statementHash, executionplan1, time);
        for (Metadata table : metadata) {
            this.metadataRepository.save(table);
            query.addMetadata(table);
        }
        this.queryRepository.save(query);

        return false;
    }

    @Override
    public Page<Query> findByMetadataInAndTimestampBetween(List<String> metadataNames, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        if (metadataNames == null) {
            return this.queryRepository.findQueriesByTimestampBetween(startDate, endDate, pageable);
        } else {
            List<Metadata> metadataList = metadataNames.stream()
                    .map(name -> metadataRepository.findByName(name))
                    .filter(Objects::nonNull).toList();
            return this.queryRepository.findByMetadataInAndTimestampBetween(metadataList, metadataList.size(), startDate, endDate, pageable);

        }


    }

    @Override
    public List<Executionplan> getAllExecutionplansForaQuery(Query query) {
        return this.queryRepository.findAllDistinctExecutionplanByStatementHash(query.getStatementHash());
    }

    @Override
    public Query sendQueryAndWait(String statement) {
        int MAX_WAITING_TIME_IN_MIN = 1;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusMinutes(MAX_WAITING_TIME_IN_MIN);
        int result;
        Query query = null;
        List<Executionplan> executionplans = null;
        String statement_hash = this.hashingStrategy.hash(statement);
        // send
        this.sendToBroker(statement);
        // busy waiting and checking
        while (true) {
            result = LocalDateTime.now().compareTo(endTime);
            if (result >= 0) {
                break;
            }
            query = this.queryRepository.findQueryByStatementHashAndTimestampAfter(statement_hash, now);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        return query;
    }

    @Override
    public void sendToBroker(String statement) {

        try {
            QueryExecutionPayload queryExecutionPayload = new QueryExecutionPayload();
            queryExecutionPayload.setStatement(statement);
            QueryExecutionMessage message = new QueryExecutionMessage();
            message.setPayload(queryExecutionPayload);
            String exchangeName = environment.getProperty("rabbitmq.exchange.name");
            String routingKey = environment.getProperty("rabbitmq.query__feature.queryExecutionQueueKey");
            rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    @RabbitListener(queues = "${rabbitmq.query__feature.queryExecutionplanQueue}")
    public void receiveFromBroker(QueryExecutionplanMessage queryExecutionplanMessage) {
        QueryExecutionplanPayload queryExecutionplanPayload = queryExecutionplanMessage.getPayload();
        this.BeginStoringProcess(queryExecutionplanPayload.getExecutionplan(), queryExecutionplanPayload.getStatement(), queryExecutionplanPayload.getTimestamp().toLocalDateTime());
    }


    @Override
    public List<Executionplan> getExecutionplans(String statementHash) {
        return this.queryRepository.findDistinctExecutionPlansByStatementHash(statementHash);
    }

    @Override
    public List<ExecutionplanStep> getExecutionplanSteps(String executionplanhash) {
        Executionplan executionplan = this.executionplanRepository.findByHash(executionplanhash);
        if (executionplan != null) {
            return executionplan.getSteps();
        }

        return new ArrayList<ExecutionplanStep>();
    }


    private static void traverseAndGetJsonNodes(JsonNode jsonNode, List<String> needed_keys, List<JsonNode> sorted_list) {
        /*
        This method takes a json object.
        The second para is the keys you are looking for in a certain hierarchy. e.g --> deviceId and operatorTyp
        The fourth parameter is a list which contains the Items in an ordered list

         */

        if (jsonNode.isObject()) {
            for (int i = 0; i <= needed_keys.size(); i++) {
                if (i == needed_keys.size()) {
                    sorted_list.add(jsonNode);
                    break;
                }

                if (jsonNode.get(needed_keys.get(i)) == null) {
                    break;
                }
            }
            jsonNode.fields().forEachRemaining(entry -> {
                JsonNode node = entry.getValue();
                if (!node.isValueNode()) {
                    traverseAndGetJsonNodes(node, needed_keys, sorted_list);
                }
            });
        } else if (jsonNode.isArray()) {
            for (JsonNode node : jsonNode) {
                if (!node.isValueNode()) {
                    traverseAndGetJsonNodes(node, needed_keys, sorted_list);
                }

            }
        } else if (jsonNode.isValueNode()) {
            // ignore
        }

    }

}
