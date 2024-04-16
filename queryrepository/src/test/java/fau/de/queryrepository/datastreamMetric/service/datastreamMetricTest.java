package fau.de.queryrepository.datastreamMetric.service;

import fau.datastreamMetric.Repository.MetricFactTableRepository;
import fau.datastreamMetric.Repository.MetricRepository;
import fau.datastreamMetric.Service.DatastreamFeatureService;
import fau.datastreamMetric.models.Datastream;
import fau.datastreamMetric.models.Metric;
import fau.datastreamMetric.models.MetricFactTable;
import org.junit.jupiter.api.Assertions;
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
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class datastreamMetricTest {

    @MockBean
    RabbitTemplate rabbitTemplate;

    @MockBean
    RabbitListenerContainerFactory rabbitListenerContainerFactory;

    @Autowired
    private TestEntityManager entityManager;



    @MockBean
    ConnectionFactory connectionFactory;

    @Autowired
    private DatastreamFeatureService datastreamFeatureService;

    @Autowired
    private MetricFactTableRepository metricFactTableRepository;

    @Autowired
    private MetricRepository metricRepository;


    @Test
    void insertTest() {
    System.out.println("Starting Test to check if Storing new Measurements using the Method proxy Method for MessageBroker");
    String metric_name = "NEU";
    Datastream datastream = new Datastream("test","{\n" +
            "\t\"5\": 5\n" +
            "}");
    List<MetricFactTable> metricFactTables = new ArrayList<>();
    Metric metric;

    entityManager.persist(datastream);
    entityManager.flush();
    this.datastreamFeatureService.storeMetricDataPoint(metric_name, "unit", "format", 555, datastream.getId(), LocalDateTime.now());
    metric = this.metricRepository.findByName(metric_name);
    metricFactTables = this.metricFactTableRepository.findMetricFactTablesByDatastreamAndMetric(datastream,metric);

    Assertions.assertEquals(1, metricFactTables.size(), "ArrayList length should match");

    }




    }
