package fau.de.queryrepository.dataMart.service;


import fau.HashingStrategies.HashingStrategy;
import fau.HashingStrategies.HashingStrategyMD5;
import fau.deviceConfiguration.Repository.CostmodelRepository;
import fau.deviceConfiguration.models.Costmodel;
import fau.deviceConfiguration.models.Device;
import fau.deviceConfiguration.models.RPUCapabilities;
import fau.dataMart.Repository.ExecutionplanRepository;
import fau.dataMart.Repository.QueryRepository;
import fau.dataMart.Service.QueryFeatureService;
import fau.dataMart.models.Executionplan;
import fau.dataMart.models.Query;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataMartTest {

    @MockBean
    RabbitTemplate rabbitTemplate;


    @MockBean
    RabbitListenerContainerFactory rabbitListenerContainerFactory;


    @MockBean
    ConnectionFactory connectionFactory;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CostmodelRepository costmodelRepository;


    @Autowired
    private QueryFeatureService queryFeatureService;

    @Autowired
    private ExecutionplanRepository executionplanRepository;

    @Autowired
    private QueryRepository queryRepository;

    private HashingStrategy strategy = new HashingStrategyMD5();




    @Test
    void insertTest() {
    System.out.println("Starting Integration QueryFeature Test. ");
    System.out.println("Store a Query, Executionplan and its steps into the Database  ");
    LocalDateTime now = LocalDateTime.now();
    String planhash = this.strategy.hash(exectuionplan);
    // create Devices
        Device device = new Device("device1","222");
        Device device2 = new Device("device2","332");
        this.entityManager.persist(device);
        this.entityManager.persist(device2);
        RPUCapabilities rpuCapabilities = new RPUCapabilities("{}",now);
        RPUCapabilities rpuCapabilities2 = new RPUCapabilities("{}",now);
        Costmodel costmodel = new Costmodel("{}",now);
        Costmodel costmodel1 = new Costmodel("{}",now);
        device.addCostmodel(costmodel);
        device.addRPUCapabilities(rpuCapabilities);
        device2.addCostmodel(costmodel1);
        device2.addRPUCapabilities(rpuCapabilities2);

        this.entityManager.persist(rpuCapabilities2);
        this.entityManager.persist(rpuCapabilities);
        this.entityManager.persist(costmodel1);
        this.entityManager.persist(costmodel);
        this.entityManager.flush();

        Costmodel costmodel2 = this.costmodelRepository.findCostmodelAtTimeForDevice((long) 1, now);

        this.queryFeatureService.BeginStoringProcess(exectuionplan,statement,now);

        Executionplan executionplan = this.executionplanRepository.findByHash(planhash);
        List<Query> query = this.queryRepository.findByStatementHash(this.strategy.hash(statement));
        assertEquals(3, executionplan.getSteps().size(),"Should have three steps");
        assertEquals(2, query.get(0).getMetadata().size(),"Should have three steps");

        return;




    }

    String statement = "SELECT * FROM";
    String exectuionplan = "{\n" +
            "  \"groupSets\": {\n" +
            "    \"1\": {\n" +
            "      \"tableId\": 2,\n" +
            "      \"tableName\": \"cash_register\",\n" +
            "      \"columnId\": 5,\n" +
            "      \"columnName\": \"store\",\n" +
            "      \"columnDataType\": \"INTEGER\"\n" +
            "    },\n" +
            "    \"2\": {\n" +
            "      \"tableId\": 2,\n" +
            "      \"tableName\": \"cash_register\",\n" +
            "      \"columnId\": 6,\n" +
            "      \"columnName\": \"place\",\n" +
            "      \"columnDataType\": \"STRING\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"nodeId\": \"d9db14bda18854f4cc0ca8a96c9e3463fd9912b28eb623b8df9929086a9fedef\",\n" +
            "  \"operatorType\": \"AGGREGATE\",\n" +
            "  \"deviceId\": 1,\n" +
            "  \"operations\": [\n" +
            "    {\n" +
            "      \"alias\": \"EXPR$2\",\n" +
            "      \"dataType\": \"INTEGER\",\n" +
            "      \"operation\": {\n" +
            "        \"name\": \"SUM\",\n" +
            "        \"operatorType\": \"Aggregation\",\n" +
            "        \"symbol\": \"SUM\"\n" +
            "      },\n" +
            "      \"operands\": [\n" +
            "        {\n" +
            "          \"index\": 8,\n" +
            "          \"attribute\": {\n" +
            "            \"tableId\": 5,\n" +
            "            \"tableName\": \"purchase\",\n" +
            "            \"columnId\": 19,\n" +
            "            \"columnName\": \"amount\",\n" +
            "            \"columnDataType\": \"INTEGER\"\n" +
            "          },\n" +
            "          \"operationId\": \"6162f165e5fef3889b1572209dfe08343f0b52dcc07be841c47af531e96b202f\",\n" +
            "          \"operationType\": \"REF\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"operationId\": \"5f0daa031c066557a3169f516ea1cc88b15a29b2c9aa5983e1489a0891695fa5\",\n" +
            "      \"operationType\": \"CALL\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"children\": [\n" +
            "    {\n" +
            "      \"nodeId\": \"c432c7a2fa29c8e73c35acba1822be2273cac2302153dfe57f0d01cab3040f1e\",\n" +
            "      \"operatorType\": \"JOIN\",\n" +
            "      \"operations\": [\n" +
            "        {\n" +
            "          \"dataType\": \"INTEGER\",\n" +
            "          \"operation\": {\n" +
            "            \"name\": \"EQUAL\",\n" +
            "            \"operatorType\": \"Comparator\",\n" +
            "            \"symbol\": \"\\u003d\"\n" +
            "          },\n" +
            "          \"operands\": [\n" +
            "            {\n" +
            "              \"index\": 0,\n" +
            "              \"attribute\": {\n" +
            "                \"tableId\": 2,\n" +
            "                \"tableName\": \"cash_register\",\n" +
            "                \"columnId\": 4,\n" +
            "                \"columnName\": \"id\",\n" +
            "                \"columnDataType\": \"INTEGER\"\n" +
            "              },\n" +
            "              \"operationId\": \"c40c9b90919a2ecef68c8ee3658343e9b4081eb981fa71ba14abbde8fc43742e\",\n" +
            "              \"operationType\": \"REF\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"index\": 4,\n" +
            "              \"attribute\": {\n" +
            "                \"tableId\": 5,\n" +
            "                \"tableName\": \"purchase\",\n" +
            "                \"columnId\": 15,\n" +
            "                \"columnName\": \"cash_register\",\n" +
            "                \"columnDataType\": \"INTEGER\"\n" +
            "              },\n" +
            "              \"operationId\": \"f3d7dc1a7d487a5854dad84eadfcd9e654022868f14bb1c9f5466f9c7074e698\",\n" +
            "              \"operationType\": \"REF\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"operationId\": \"ec42752ee80c28930d9137812a9e2ef8f65ca678076c6982f96158e7af1db136\",\n" +
            "          \"operationType\": \"CALL\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"children\": [\n" +
            "        {\n" +
            "          \"nodeId\": \"0ab1bbcb5f49e547433c630c429dd245118bbdba168c2daf72a58779384a8dca\",\n" +
            "          \"operatorType\": \"TABLESCAN\",\n" +
            "          \"deviceId\" : 2,\n" +
            "          \"operations\": [\n" +
            "            {\n" +
            "              \"tableId\": 2,\n" +
            "              \"tableName\": \"cash_register\",\n" +
            "              \"operationId\": \"720851db1dc18b48b95445ca526f7e6c88b5fe73983ff448f91d7cd52268bdc0\",\n" +
            "              \"operationType\": \"REF\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"children\": [],\n" +
            "          \"attributes\": {\n" +
            "            \"0\": {\n" +
            "              \"tableId\": 2,\n" +
            "              \"tableName\": \"cash_register\",\n" +
            "              \"columnId\": 4,\n" +
            "              \"columnName\": \"id\",\n" +
            "              \"columnDataType\": \"INTEGER\"\n" +
            "            },\n" +
            "            \"1\": {\n" +
            "              \"tableId\": 2,\n" +
            "              \"tableName\": \"cash_register\",\n" +
            "              \"columnId\": 5,\n" +
            "              \"columnName\": \"store\",\n" +
            "              \"columnDataType\": \"INTEGER\"\n" +
            "            },\n" +
            "            \"2\": {\n" +
            "              \"tableId\": 2,\n" +
            "              \"tableName\": \"cash_register\",\n" +
            "              \"columnId\": 6,\n" +
            "              \"columnName\": \"place\",\n" +
            "              \"columnDataType\": \"STRING\"\n" +
            "            }\n" +
            "          },\n" +
            "          \"isStream\": false\n" +
            "        },\n" +
            "        {\n" +
            "          \"nodeId\": \"3a513c2dfc62a3724577abbe497600157a94f26983d9b1152d8ba98859bd5076\",\n" +
            "          \"deviceId\" : 2,\n" +
            "          \"operatorType\": \"TABLESCAN\",\n" +
            "          \"operations\": [\n" +
            "            {\n" +
            "              \"tableId\": 5,\n" +
            "              \"tableName\": \"purchase\",\n" +
            "              \"operationId\": \"0207551797ef4a639bc8eaa4f319c5e32b06444dd717629a0be7bbca4b986265\",\n" +
            "              \"operationType\": \"REF\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"children\": [],\n" +
            "          \"attributes\": {\n" +
            "            \"0\": {\n" +
            "              \"tableId\": 5,\n" +
            "              \"tableName\": \"purchase\",\n" +
            "              \"columnId\": 14,\n" +
            "              \"columnName\": \"id\",\n" +
            "              \"columnDataType\": \"INTEGER\"\n" +
            "            },\n" +
            "            \"1\": {\n" +
            "              \"tableId\": 5,\n" +
            "              \"tableName\": \"purchase\",\n" +
            "              \"columnId\": 15,\n" +
            "              \"columnName\": \"cash_register\",\n" +
            "              \"columnDataType\": \"INTEGER\"\n" +
            "            },\n" +
            "            \"2\": {\n" +
            "              \"tableId\": 5,\n" +
            "              \"tableName\": \"purchase\",\n" +
            "              \"columnId\": 16,\n" +
            "              \"columnName\": \"product\",\n" +
            "              \"columnDataType\": \"INTEGER\"\n" +
            "            },\n" +
            "            \"3\": {\n" +
            "              \"tableId\": 5,\n" +
            "              \"tableName\": \"purchase\",\n" +
            "              \"columnId\": 17,\n" +
            "              \"columnName\": \"customer\",\n" +
            "              \"columnDataType\": \"INTEGER\"\n" +
            "            },\n" +
            "            \"4\": {\n" +
            "              \"tableId\": 5,\n" +
            "              \"tableName\": \"purchase\",\n" +
            "              \"columnId\": 18,\n" +
            "              \"columnName\": \"timestamp\",\n" +
            "              \"columnDataType\": \"TIMESTAMP\"\n" +
            "            },\n" +
            "            \"5\": {\n" +
            "              \"tableId\": 5,\n" +
            "              \"tableName\": \"purchase\",\n" +
            "              \"columnId\": 19,\n" +
            "              \"columnName\": \"amount\",\n" +
            "              \"columnDataType\": \"INTEGER\"\n" +
            "            }\n" +
            "          },\n" +
            "          \"isStream\": false\n" +
            "        }\n" +
            "      ],\n" +
            "      \"attributes\": {\n" +
            "        \"0\": {\n" +
            "          \"tableId\": 2,\n" +
            "          \"tableName\": \"cash_register\",\n" +
            "          \"columnId\": 4,\n" +
            "          \"columnName\": \"id\",\n" +
            "          \"columnDataType\": \"INTEGER\"\n" +
            "        },\n" +
            "        \"1\": {\n" +
            "          \"tableId\": 2,\n" +
            "          \"tableName\": \"cash_register\",\n" +
            "          \"columnId\": 5,\n" +
            "          \"columnName\": \"store\",\n" +
            "          \"columnDataType\": \"INTEGER\"\n" +
            "        },\n" +
            "        \"2\": {\n" +
            "          \"tableId\": 2,\n" +
            "          \"tableName\": \"cash_register\",\n" +
            "          \"columnId\": 6,\n" +
            "          \"columnName\": \"place\",\n" +
            "          \"columnDataType\": \"STRING\"\n" +
            "        },\n" +
            "        \"3\": {\n" +
            "          \"tableId\": 5,\n" +
            "          \"tableName\": \"purchase\",\n" +
            "          \"columnId\": 14,\n" +
            "          \"columnName\": \"id\",\n" +
            "          \"columnDataType\": \"INTEGER\"\n" +
            "        },\n" +
            "        \"4\": {\n" +
            "          \"tableId\": 5,\n" +
            "          \"tableName\": \"purchase\",\n" +
            "          \"columnId\": 15,\n" +
            "          \"columnName\": \"cash_register\",\n" +
            "          \"columnDataType\": \"INTEGER\"\n" +
            "        },\n" +
            "        \"5\": {\n" +
            "          \"tableId\": 5,\n" +
            "          \"tableName\": \"purchase\",\n" +
            "          \"columnId\": 16,\n" +
            "          \"columnName\": \"product\",\n" +
            "          \"columnDataType\": \"INTEGER\"\n" +
            "        },\n" +
            "        \"6\": {\n" +
            "          \"tableId\": 5,\n" +
            "          \"tableName\": \"purchase\",\n" +
            "          \"columnId\": 17,\n" +
            "          \"columnName\": \"customer\",\n" +
            "          \"columnDataType\": \"INTEGER\"\n" +
            "        },\n" +
            "        \"7\": {\n" +
            "          \"tableId\": 5,\n" +
            "          \"tableName\": \"purchase\",\n" +
            "          \"columnId\": 18,\n" +
            "          \"columnName\": \"timestamp\",\n" +
            "          \"columnDataType\": \"TIMESTAMP\"\n" +
            "        },\n" +
            "        \"8\": {\n" +
            "          \"tableId\": 5,\n" +
            "          \"tableName\": \"purchase\",\n" +
            "          \"columnId\": 19,\n" +
            "          \"columnName\": \"amount\",\n" +
            "          \"columnDataType\": \"INTEGER\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"isStream\": false\n" +
            "    }\n" +
            "  ],\n" +
            "  \"attributes\": {\n" +
            "    \"0\": {\n" +
            "      \"tableId\": 2,\n" +
            "      \"tableName\": \"cash_register\",\n" +
            "      \"columnId\": 5,\n" +
            "      \"columnName\": \"store\",\n" +
            "      \"columnDataType\": \"INTEGER\"\n" +
            "    },\n" +
            "    \"1\": {\n" +
            "      \"tableId\": 2,\n" +
            "      \"tableName\": \"cash_register\",\n" +
            "      \"columnId\": 6,\n" +
            "      \"columnName\": \"place\",\n" +
            "      \"columnDataType\": \"STRING\"\n" +
            "    },\n" +
            "    \"2\": {\n" +
            "      \"alias\": \"EXPR$2\",\n" +
            "      \"provenance\": {\n" +
            "        \"alias\": \"EXPR$2\",\n" +
            "        \"dataType\": \"INTEGER\",\n" +
            "        \"operation\": {\n" +
            "          \"name\": \"SUM\",\n" +
            "          \"operatorType\": \"Aggregation\",\n" +
            "          \"symbol\": \"SUM\"\n" +
            "        },\n" +
            "        \"operands\": [\n" +
            "          {\n" +
            "            \"index\": 8,\n" +
            "            \"attribute\": {\n" +
            "              \"tableId\": 5,\n" +
            "              \"tableName\": \"purchase\",\n" +
            "              \"columnId\": 19,\n" +
            "              \"columnName\": \"amount\",\n" +
            "              \"columnDataType\": \"INTEGER\"\n" +
            "            },\n" +
            "            \"operationId\": \"6162f165e5fef3889b1572209dfe08343f0b52dcc07be841c47af531e96b202f\",\n" +
            "            \"operationType\": \"REF\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"operationId\": \"5f0daa031c066557a3169f516ea1cc88b15a29b2c9aa5983e1489a0891695fa5\",\n" +
            "        \"operationType\": \"CALL\"\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  \"isStream\": false\n" +
            "}";



}
