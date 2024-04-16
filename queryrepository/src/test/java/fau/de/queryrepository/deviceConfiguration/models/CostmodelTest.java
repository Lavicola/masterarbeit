package fau.de.queryrepository.deviceConfiguration.models;

import fau.deviceConfiguration.Repository.CostmodelRepository;
import fau.deviceConfiguration.models.Costmodel;
import fau.deviceConfiguration.models.Device;
import fau.dataMart.Service.QueryFeatureService;
import org.hibernate.exception.DataException;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CostmodelTest {
    @MockBean
    QueryFeatureService queryFeatureService;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @MockBean
    ConnectionFactory connectionFactory;
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CostmodelRepository costmodelRepository;


    String cost = "{\n" +
            "\t\"5\": 5\n" +
            "}";


    @Test
    void insertTest() {
        Costmodel costmodel = new Costmodel(cost);
        entityManager.persist(costmodel);
        entityManager.flush();

        Costmodel retrived_costmodel = this.costmodelRepository.findCostmodelById(costmodel.getId());
        assertNotNull(retrived_costmodel);
    }

    @Test
    void insertTestReturnSpecificCostmodel() {
        System.out.println("Test to Check if we return the right Costmodel for a specific time");

        LocalDateTime start = LocalDateTime.now();

        Device device = new Device("t", "d");
        entityManager.persist(device);

        Costmodel costmodel = new Costmodel(cost, device, start);
        Costmodel costmodel2 = new Costmodel(cost, device, start);
        entityManager.persist(costmodel);
        entityManager.flush();
        // only one result
        Costmodel costmodel1 = this.costmodelRepository.findCostmodelAtTimeForDevice(device.getId(), start);
        assertNotNull(costmodel1);
        entityManager.persist(costmodel2);
        entityManager.flush();
        // two costplans but we only return one there exception
        assertThrows(IncorrectResultSizeDataAccessException.class, () -> {
            this.costmodelRepository.findCostmodelAtTimeForDevice(device.getId(), LocalDateTime.now());
        });
        // now set the costplan to an enddate and try again
        costmodel2.setEndDate(LocalDateTime.now().minusDays(55));
        Costmodel retrvied_costmodel = this.costmodelRepository.findCostmodelAtTimeForDevice(device.getId(), LocalDateTime.now());
        assertEquals(retrvied_costmodel, costmodel1);
    }



    @Test
    void insertNotValidJSONTest() {
        Costmodel costmodel = new Costmodel("a");
        assertThrows(DataException.class, () -> {
            entityManager.persist(costmodel);
            entityManager.flush();
        });


    }


}
