package fau.de.queryrepository.datastreamMetric.models;
import fau.datastreamMetric.Repository.MetricRepository;
import fau.datastreamMetric.models.Metric;
import fau.dataMart.Service.QueryFeatureService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MetricTest {

    @MockBean
    QueryFeatureService queryFeatureService;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @MockBean
    ConnectionFactory connectionFactory;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MetricRepository metricRepository;

    @Test
    void insertTest(){
        System.out.println("Test to Insert Metric");
        Metric metric = new Metric("test","km/h","x.xx");
        Metric metricSelectivity = new Metric("test2","lmn/h","x.xx");
        entityManager.persist(metric);
        entityManager.persist(metricSelectivity);
        entityManager.flush();

        Metric retrviedMetric = this.metricRepository.findById(metric.getId()).get();
        Metric retrviedSelectivityMetric = this.metricRepository.findById(metricSelectivity.getId()).get();
        Assertions.assertEquals(metric.getClass() ,retrviedMetric.getClass(), "Class should match");
        Assertions.assertEquals(metric.getName() ,retrviedMetric.getName(), "Name should match");

        Assertions.assertEquals(metricSelectivity.getClass() ,retrviedSelectivityMetric.getClass(), "Class should match");
        Assertions.assertEquals(metricSelectivity.getName() ,metricSelectivity.getName(), "Name should match");


    }


}
