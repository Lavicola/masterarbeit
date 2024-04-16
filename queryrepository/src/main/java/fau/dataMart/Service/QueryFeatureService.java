package fau.dataMart.Service;

import fau.dataMart.Messages.QueryExecutionplanMessage;
import fau.dataMart.dto.QueryExecutionplanDTO;
import fau.dataMart.models.Executionplan;
import fau.dataMart.models.ExecutionplanStep;
import fau.dataMart.models.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;
import java.util.List;

public interface QueryFeatureService {

    // Incoming Message
    public boolean BeginStoringProcess(String executionplan, String statement, LocalDateTime time);

    public Page<Query> findByMetadataInAndTimestampBetween(List<String> metadata, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    public List<Executionplan> getAllExecutionplansForaQuery(Query query);

    public Query sendQueryAndWait(String executionplan);

    public void sendToBroker(String name);

    public void receiveFromBroker(QueryExecutionplanMessage queryExecutionplanMessage);

    public List<Executionplan> getExecutionplans(String statementHash);

    public List<ExecutionplanStep> getExecutionplanSteps(String executionplanhash);





}
