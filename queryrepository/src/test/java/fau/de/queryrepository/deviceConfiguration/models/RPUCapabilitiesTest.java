package fau.de.queryrepository.deviceConfiguration.models;

import fau.deviceConfiguration.Repository.RPUCapabilitiesRepository;
import fau.deviceConfiguration.models.Costmodel;
import fau.deviceConfiguration.models.Device;
import fau.deviceConfiguration.models.RPUCapabilities;
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
public class RPUCapabilitiesTest {
    @MockBean
    QueryFeatureService queryFeatureService;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @MockBean
    ConnectionFactory connectionFactory;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RPUCapabilitiesRepository rpuCapabilitiesRepository;


    String capa = "{\n" +
            "\t\"5\": 5\n" +
            "}";


    @Test
    void insertTest() {
        System.out.println("Simple Insert and Retrive Test for RPUCapabilities");

        RPUCapabilities capabilities = new RPUCapabilities(capa);
        entityManager.persist(capabilities);
        entityManager.flush();

        RPUCapabilities retrived_costmodel = this.rpuCapabilitiesRepository.findRPUCapabilitiesById(capabilities.getId());
        assertNotNull(retrived_costmodel);


    }

    @Test
    void insertTestReturnSpecificostplan() {
        System.out.println("Test to Check if we return the right Capabilities for a specific time");

        LocalDateTime start = LocalDateTime.now();
        Device device = new Device("t", "d");
        entityManager.persist(device);

        RPUCapabilities capabilities1 = new RPUCapabilities(capa, device, start);
        RPUCapabilities capabilities2 = new RPUCapabilities(capa, device, start);
        entityManager.persist(capabilities1);
        entityManager.flush();
        // only one result
        RPUCapabilities capabilities = this.rpuCapabilitiesRepository.findRPUCapabilitiesAtTimeForDevice(device.getId(), LocalDateTime.now());
        assertNotNull(capabilities);
        entityManager.persist(capabilities2);
        entityManager.flush();
        // two costplans but we only return one there exception
        assertThrows(IncorrectResultSizeDataAccessException.class, () -> {
            this.rpuCapabilitiesRepository.findRPUCapabilitiesAtTimeForDevice(device.getId(), LocalDateTime.now());
        });
        // now set the costplan to an enddate and try again
        capabilities2.setEndDate(LocalDateTime.now().minusDays(55));
        RPUCapabilities retrvied_costmodel = this.rpuCapabilitiesRepository.findRPUCapabilitiesAtTimeForDevice(device.getId(), LocalDateTime.now());
        assertEquals(retrvied_costmodel, capabilities);
    }

    @Test
    void insertNotValidJSONTest() {
        System.out.println("Test to Check if only valid JSON are allowed for Capability");

        Costmodel costmodel = new Costmodel("a");
        assertThrows(DataException.class, () -> {
            entityManager.persist(costmodel);
            entityManager.flush();
        });


    }


}




