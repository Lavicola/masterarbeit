package fau.de.queryrepository.dataMart.models;


import fau.dataMart.Repository.QueryRepository;
import fau.dataMart.Service.QueryFeatureService;
import fau.dataMart.models.Executionplan;
import fau.dataMart.models.Query;
import fau.dataMart.models.Metadata;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class QueryTest {

    @MockBean
    QueryFeatureService queryFeatureService;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @MockBean
    ConnectionFactory connectionFactory;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private QueryRepository queryRepository;

    String string_executionplan = "{\n" +
            "\t\"5\": 5\n" +
            "}";


    Executionplan executionplan = new Executionplan("hash1", string_executionplan);
    Executionplan executionplan1 = new Executionplan("hash2", string_executionplan);


    @BeforeEach
    public void setup() {

        entityManager.persist(executionplan);
        entityManager.persist(executionplan1);
        entityManager.flush();


    }


    @Test
    void insertTest() {
        System.out.println("Starting Test to simply insert and retrieve one and more Queries");
        String statementHash1 = "selet1Hash";
        String statementHash2 = "selet2Hash";

        LocalDateTime localDateTime = LocalDateTime.now();
        Query query1 = new Query("Select1", statementHash1, executionplan, localDateTime);
        Query query2 = new Query("Select1", statementHash1, executionplan, localDateTime);
        Query query3 = new Query("Select1", statementHash2, executionplan, localDateTime);


        entityManager.persist(query1);
        entityManager.persist(query2);
        entityManager.persist(query3);

        entityManager.flush();

        List<Query> queries = this.queryRepository.findByStatementHash(statementHash1);
        Assertions.assertEquals(2, queries.size(), "ArrayList length should match");
        queries = this.queryRepository.findByStatementHash(statementHash2);
        Assertions.assertEquals(1, queries.size(), "ArrayList length should match");

    }

    @Test
    void insertTestBetweenTime() {
        System.out.println("Starting Test to check if all Queries are found at a certain Time interval ");
        String statementHash1 = "selet1Hash";
        LocalDateTime start_interval = LocalDateTime.now();
        LocalDateTime end_interval = start_interval.plusMinutes(3);
        LocalDateTime between_interval1 = start_interval.plusMinutes(2);
        LocalDateTime after_interval1 = start_interval.plusMinutes(4);

        Query query1 = new Query("Select1", statementHash1, executionplan, start_interval);
        Query query2 = new Query("Select1", statementHash1, executionplan, end_interval);
        Query query3 = new Query("Select1", statementHash1, executionplan, between_interval1);
        Query query4 = new Query("Select1", statementHash1, executionplan, after_interval1);


        entityManager.persist(query1);
        entityManager.persist(query2);
        entityManager.persist(query3);
        entityManager.persist(query4);
        entityManager.flush();
        Pageable pageable = PageRequest.of(1, 20);

        Page<Query> queries = this.queryRepository.findQueriesByTimestampBetween(start_interval, end_interval, pageable);
        Assertions.assertEquals(3, queries.getTotalElements(), "ArrayList length should match");
        // edge case end time a bit later
        queries = this.queryRepository.findQueriesByTimestampBetween( start_interval, end_interval.minusSeconds(1), pageable);
        Assertions.assertEquals(2, queries.getTotalElements(), "ArrayList length should match");
        // edge case start time a bit later
        queries = this.queryRepository.findQueriesByTimestampBetween(start_interval.plusSeconds(1), end_interval, pageable);
        Assertions.assertEquals(2, queries.getTotalElements(), "ArrayList length should match");


    }


    @Test
    void insertTestRetriveExecutionplans() {
        System.out.println("Starting Test to return one/all Executionplans for a Query");
        String statementHash1 = "selet1Hash";
        String statementHash2 = "selet2Hash";

        LocalDateTime localDateTime = LocalDateTime.now();
        Query query1 = new Query("Select1", statementHash1, executionplan, localDateTime);
        Query query2 = new Query("Select1", statementHash1, executionplan, localDateTime);

        entityManager.persist(query1);
        entityManager.persist(query2);
        entityManager.flush();
        List<Executionplan> executionplanList = this.queryRepository.findAllDistinctExecutionplanByStatementHash(statementHash1);
        Query query3 = new Query("Select1", statementHash1, executionplan1, localDateTime);
        entityManager.persist(query3);
        entityManager.flush();
        List<Executionplan> executionplanList2 = this.queryRepository.findAllDistinctExecutionplanByStatementHash(statementHash1);

        Assertions.assertEquals(1, executionplanList.size(), "ArrayList length should match");
        Assertions.assertEquals(2, executionplanList2.size(), "ArrayList length should match");

        return;


    }


    @Test
    void insertTestBetweenTimeAndMetadata() {
        System.out.println("Starting Test to check if all Queries are found at a certain Time interval AND Metadata references ");
        String statementHash1 = "selet1Hash";

        LocalDateTime start_interval = LocalDateTime.now();
        LocalDateTime end_interval = start_interval.plusMinutes(3);
        LocalDateTime between_interval1 = start_interval.plusMinutes(2);
        LocalDateTime after_interval1 = start_interval.plusMinutes(4);

        Query query1 = new Query("Select1", statementHash1, executionplan, start_interval);
        Query query2 = new Query("Select1", statementHash1, executionplan, end_interval);
        Query query3 = new Query("Select1", statementHash1, executionplan, between_interval1);
        Query query4 = new Query("Select1", statementHash1, executionplan, after_interval1);
        Metadata table1 = new Metadata("Customer");
        Metadata table2 = new Metadata("Product");
        Metadata table3 = new Metadata("NoName");
        ArrayList<Metadata> selected_tables1 = new ArrayList<Metadata>();
        ArrayList<Metadata> selected_tables2 = new ArrayList<Metadata>();
        ArrayList<Metadata> selected_tables3 = new ArrayList<Metadata>();

        query1.addMetadata(table1);

        query2.addMetadata(table2);
        query2.addMetadata(table1);

        query3.addMetadata(table3);
        query3.addMetadata(table2);
        query3.addMetadata(table1);

        entityManager.persist(table1);
        entityManager.persist(table2);
        entityManager.persist(table3);
        entityManager.persist(query1);
        entityManager.persist(query2);
        entityManager.persist(query3);
        entityManager.persist(query4);
        entityManager.flush();
        Pageable pageable = PageRequest.of(0, 10);

        selected_tables1.add(table1);

        selected_tables2.add(table2);

        selected_tables3.add(table3);
        selected_tables3.add(table1);
        selected_tables3.add(table2);

        // Time does not matter here
        Page<Query> queries3 = this.queryRepository.findByMetadataInAndTimestampBetween(selected_tables1,selected_tables1.size(),start_interval,end_interval,pageable);
        Page<Query> queries2 = this.queryRepository.findByMetadataInAndTimestampBetween(selected_tables2, selected_tables2.size(),start_interval,end_interval,pageable);
        Page<Query> queries1 = this.queryRepository.findByMetadataInAndTimestampBetween(selected_tables3, selected_tables3.size(),start_interval,end_interval,pageable);
        // time matters
        Page<Query> queries4 = this.queryRepository.findByMetadataInAndTimestampBetween(selected_tables1,selected_tables1.size(),start_interval,start_interval.plusSeconds(1),pageable);
        Page<Query> queries5 = this.queryRepository.findByMetadataInAndTimestampBetween(selected_tables1, selected_tables1.size(),start_interval,between_interval1.plusSeconds(1),pageable);
        Page<Query> queries6 = this.queryRepository.findByMetadataInAndTimestampBetween(selected_tables1, selected_tables1.size(),start_interval,end_interval,pageable);


        // time does not matter
        Assertions.assertEquals(3, queries3.getNumberOfElements(), "All Queries have this table reference");
        Assertions.assertEquals(2, queries2.getNumberOfElements(), "Only two Queries reference this Table");
        Assertions.assertEquals(1, queries1.getNumberOfElements(), "Only one Queries should be found");
        // time matters
        Assertions.assertEquals(1, queries4.getNumberOfElements(), "All Queries have this table reference");
        Assertions.assertEquals(2, queries5.getNumberOfElements(), "Only two Queries reference this Table");
        Assertions.assertEquals(3, queries6.getNumberOfElements(), "Only one Queries should be found");

    }


    @Test
    void insertMetaDataTest() {
        System.out.println("Starting Test QuerymetaData");
        LocalDateTime start_interval = LocalDateTime.now();
        LocalDateTime end_interval = start_interval.plusMinutes(3);

        Metadata queryMetadata = new Metadata("customer");
        Metadata queryMetadata2 = new Metadata("flight");
        entityManager.persist(queryMetadata);
        entityManager.persist(queryMetadata2);
        String statementHash1 = "selet1Hash";

        LocalDateTime localDateTime = LocalDateTime.now();
        Query query1 = new Query("Select1", statementHash1, executionplan, localDateTime);
        Query query2 = new Query("Select1", statementHash1, executionplan, localDateTime);
        query1.addMetadata(queryMetadata);
        query1.addMetadata(queryMetadata2);
        query2.addMetadata(queryMetadata2);
        entityManager.persist(query1);
        entityManager.persist(query2);
        entityManager.flush();

        Pageable pageable = PageRequest.of(1, 20);
        List<Metadata> metadata = new ArrayList<>();
        List<Metadata> metadataList = new ArrayList<>();
        metadataList.add(queryMetadata2);
        metadata.add(queryMetadata);


        List<Query> queries = this.queryRepository.findQueriesByMetadataIn(metadata);

        List<Query> queries2 = this.queryRepository.findQueriesByMetadataIn(metadataList);

        Assertions.assertEquals(1, queries.size(), "ArrayList length should match");
        Assertions.assertEquals(2, queries2.size(), "ArrayList length should match");


    }
}