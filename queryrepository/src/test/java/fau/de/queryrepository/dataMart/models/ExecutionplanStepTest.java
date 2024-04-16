package fau.de.queryrepository.dataMart.models;

import fau.deviceConfiguration.models.Costmodel;
import fau.deviceConfiguration.models.RPUCapabilities;
import fau.dataMart.Repository.ExecutionplanStepRepository;
import fau.dataMart.Service.QueryFeatureService;
import fau.dataMart.models.Executionplan;
import fau.dataMart.models.ExecutionplanStep;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ExecutionplanStepTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExecutionplanStepRepository executionplanStepRepository;

    @MockBean
    QueryFeatureService queryFeatureService;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @MockBean
    ConnectionFactory connectionFactory;

    String string_executionplan = "{\n" +
            "\t\"5\": 5\n" +
            "}";

    Executionplan executionplan = new Executionplan("hash1", string_executionplan);
    Executionplan executionplan1 = new Executionplan("hash2", string_executionplan);

    Costmodel costmodel = new Costmodel(string_executionplan, LocalDateTime.now());
    RPUCapabilities rpuCapabilities = new RPUCapabilities(string_executionplan);

    @BeforeEach
    public void setup() {

        entityManager.persist(executionplan);
        entityManager.persist(executionplan1);
        entityManager.persist(costmodel);
        entityManager.persist(rpuCapabilities);

        entityManager.flush();


    }


    @Test
    void insertTestExecutionplanStepRetrival() {
        System.out.println("Starting Test to retrieve Executionplansteps for an execctuionplan");
        String operator = "TABLESCAN";
        String operator2 = "AGGREGATE";
        ExecutionplanStep executionplanStep = new ExecutionplanStep(operator, 1, string_executionplan, executionplan, costmodel, rpuCapabilities);
        ExecutionplanStep executionplanStep2 = new ExecutionplanStep(operator2, 2, string_executionplan, executionplan1, costmodel, rpuCapabilities);
        entityManager.persist(executionplanStep);
        entityManager.persist(executionplanStep2);
        entityManager.flush();

        List<ExecutionplanStep> executionplanStepList = this.executionplanStepRepository.findByExecutionplan(executionplan);
        List<ExecutionplanStep> executionplanStepListCostmodel = this.executionplanStepRepository.findByCostmodel(costmodel);
        List<ExecutionplanStep> executionplanStepListCappa = this.executionplanStepRepository.findByRpuCapabilities(rpuCapabilities);

        Assertions.assertEquals(1, executionplanStepList.size(), "ArrayList length should match");
        Assertions.assertEquals(2, executionplanStepListCostmodel.size(), "ArrayList length should match");
        Assertions.assertEquals(2, executionplanStepListCappa.size(), "ArrayList length should match");


    }


}
