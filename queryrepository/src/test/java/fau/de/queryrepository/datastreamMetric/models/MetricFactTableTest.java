package fau.de.queryrepository.datastreamMetric.models;

import fau.datastreamMetric.Repository.MetricFactTableRepository;
import fau.datastreamMetric.models.Datastream;
import fau.datastreamMetric.models.Metric;
import fau.datastreamMetric.models.MetricFactTable;
import fau.dataMart.Service.QueryFeatureService;
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
public class MetricFactTableTest {
    @MockBean
    QueryFeatureService queryFeatureService;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @MockBean
    ConnectionFactory connectionFactory;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MetricFactTableRepository metricFactTableRepository;

    Datastream datastream = new Datastream("test", "{\n" +
            "\t\"5\": 5\n" +
            "}"
    );

    Metric metric = new Metric("test", "km/h", "x.xx");
    Metric metricSelectivity = new Metric("test2", "km/h", "x.xx");


    @BeforeEach
    public void setup() {
        // Code to execute before each test
        entityManager.persist(datastream);
        entityManager.persist(metric);
        entityManager.persist(metricSelectivity);
        entityManager.flush();

    }


    @Test
    void insertTest() {
        System.out.println("Test to Insert MetricFactTable");

        MetricFactTable entry = new MetricFactTable(metric, datastream, 5.5, LocalDateTime.now());
        entityManager.persist(entry);
        entityManager.flush();
        Assertions.assertEquals(1, this.metricFactTableRepository.findAll().size(), "ArrayList length should match");

    }

    @Test
    void insertTestDistinct() {
        System.out.println("Test to Check Distinct MetricFactTable Feature");

        // add second metric
        Metric metric2 = new Metric("test2", "l/h", "x.xx");
        List<Metric> unique_metric;
        List<MetricFactTable> metricFactTables;
        List<MetricFactTable> metricFactTables2;
        List<MetricFactTable> metricFactTables3;

        entityManager.persist(metric2);
        entityManager.flush();
        // add Entries to the Metric
        for (int i = 1; i <= 10; i++) {
            datastream.addMetricValue(new MetricFactTable(metric2, datastream, i + 55, LocalDateTime.now().plusDays(i)));
            datastream.addMetricValue(new MetricFactTable(metric, datastream, i + 65, LocalDateTime.now().plusDays(i)));
        }

        unique_metric = this.metricFactTableRepository.findDistinctByDatastream(datastream);
        metricFactTables = this.metricFactTableRepository.findMetricFactTablesByDatastream(datastream);
        metricFactTables2 = this.metricFactTableRepository.findMetricFactTablesByDatastreamAndMetric(datastream, metric);
        metricFactTables3 = this.metricFactTableRepository.findMetricFactTablesByDatastreamAndMetric(datastream, metric2);

        Assertions.assertEquals(2, unique_metric.size(), "Only two distinct Metric should exist");
        Assertions.assertEquals(20, metricFactTables.size(), "10+10 Measurements");
        Assertions.assertEquals(10, metricFactTables2.size(), "10 Measurements for Metric A");
        Assertions.assertEquals(10, metricFactTables3.size(), "10 Measurements for Metric B");
    }


    @Test
    void insertTestWithDataMetricFactTableEntriesRepositoryTest() {
        System.out.println("Test to Check if we find all Measurements for one Datastream");
        int loops = 9;
        List<MetricFactTable> entries;
        List<MetricFactTable> entries2;

        for (int i = 1; i <= loops; i++) {
            datastream.addMetricValue(new MetricFactTable(metricSelectivity, datastream, i + 0.5, LocalDateTime.now()));
        }
        datastream.addMetricValue(new MetricFactTable(metric, datastream, 55, LocalDateTime.now()));
        entries = this.metricFactTableRepository.findMetricFactTablesByDatastream(datastream);
        entries2 = this.metricFactTableRepository.findMetricFactTablesByDatastreamAndMetric(datastream, metric);

        Assertions.assertEquals(loops + 1, entries.size(), "ArrayList length should match");
        Assertions.assertEquals(1, entries2.size(), "ArrayList length should match");
    }


}
