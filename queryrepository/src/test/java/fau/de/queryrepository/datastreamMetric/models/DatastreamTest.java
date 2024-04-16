package fau.de.queryrepository.datastreamMetric.models;

import fau.datastreamMetric.models.Datastream;
import fau.dataMart.Service.QueryFeatureService;
import org.hibernate.exception.DataException;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DatastreamTest {


    @MockBean
    RabbitTemplate rabbitTemplate;

    @MockBean
    ConnectionFactory connectionFactory;

    @Autowired
    private TestEntityManager entityManager;

    Datastream datastream = new Datastream("test", "{\n" +
            "\t\"5\": 5\n" +
            "}"
    );

    @BeforeEach
    public void setup() {

        entityManager.persist(datastream);
        entityManager.flush();


    }



    @Test
    void insertNotValidJSONTest() {
        System.out.println("Check if only valid JSON allow Test");
        Datastream datastream = new Datastream("test", "a");

        assertThrows(DataException.class, () -> {
            entityManager.persist(datastream);
            entityManager.flush();
        });


    }

}