package fau.de.queryrepository.deviceConfiguration.models;

import fau.datastreamMetric.models.Datastream;
import fau.deviceConfiguration.Repository.DeviceRepository;
import fau.deviceConfiguration.models.*;
import fau.dataMart.Service.QueryFeatureService;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeviceTest {
    @MockBean
    QueryFeatureService queryFeatureService;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @MockBean
    ConnectionFactory connectionFactory;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DeviceRepository deviceRepository;

    String cost = "{\n" +
            "\t\"5\": 5\n" +
            "}";
    String cost2 = "{\n" +
            "\t\"1111\": 5\n" +
            "}";

    @Test
    void insertTest() {
        Device device = new Device("rpu1", "145.111.55.223");
        entityManager.persist(device);
        entityManager.flush();
    }

    @Test
    void insertDeviceWithPorts() {
        System.out.println("Test to check Device Logic for setting Ports");
        Device device = new Device("rpu1", "145.111.55.223");
        ArrayList<Port> ports = new ArrayList<>();
        Datastream datastream = new Datastream("test", "{}");
        Port port1 = new Port(22, "test","test","test",null);
        Port port2 = new Port(22, "test","test","test",null);
        Port port4 = new Port(23, "test","test","test",null);

        Port port3 = new Port(25, "test", "test","test",datastream);
        ports.add(port1);
        ports.add(port3);
        ports.add(port4);
        assertTrue("Should be True, unique Ports", device.setPorts(ports));
        ports.add(port2);
        assertFalse("Should be False, duplicate Ports", device.setPorts(ports));
        this.entityManager.persist(datastream);
        this.entityManager.persist(device);
        this.entityManager.flush();
        //
        Device retrived_device = this.deviceRepository.findDeviceById(device.getId());
        List<Port> portList = retrived_device.getNormalPorts();
        List<Port> datastreamPorts = retrived_device.getDatastreamPorts();
        assertEquals(2, portList.size());
        assertEquals(1, datastreamPorts.size());


    }

    @Test
    void insertTestWithRelations() {
        System.out.println("Test to check Device Logic to add new Costmodel");

        Device device = new Device("rpu1", "145.111.55.223");
        Costmodel costmodel = new Costmodel(cost);
        Costmodel costmodel2 = new Costmodel(cost2);
        device.addCostmodel(costmodel);
        device.addCostmodel(costmodel2);
        entityManager.persist(device);
        entityManager.flush();
        Device retrived_device = this.deviceRepository.findDeviceById(device.getId());
        assertEquals(retrived_device, device);
        assertEquals(2, device.getCostModels().size());
        assertNotNull("Costmodel was not set", device.getCostModels().get(0));
        assertNotNull("Costmodel was not set", device.getCostModels().get(1));
        // same for cap
        device.addRPUCapabilities(new RPUCapabilities(cost));
        device.addRPUCapabilities(new RPUCapabilities(cost2));
        entityManager.persist(device);
        entityManager.flush();
        assertEquals(retrived_device, device);
        assertEquals(2, device.getCostModels().size());
        assertNotNull("Capability was not set", device.getCostModels().get(0));
        assertNotNull("Capability was not set", device.getCostModels().get(1));
    }

    @Test
    void insertTestDuplicationCheck() {
        System.out.println("Test to check Device Logic if adding the same Costmodel twice");

        // the device should not add the SAME JSON Costmodel/capa again, if the last element is it already
        LocalDateTime start = LocalDateTime.now();
        Device device = new Device("rpu1", "145.111.55.223");
        Costmodel costmodel = new Costmodel(cost);
        Costmodel costmodel2 = new Costmodel(cost);
        Costmodel costmodel3 = new Costmodel(cost2);

        RPUCapabilities rpuCapabilities = new RPUCapabilities(cost);
        RPUCapabilities rpuCapabilitie2 = new RPUCapabilities(cost);
        RPUCapabilities rpuCapabilitie3 = new RPUCapabilities(cost2);

        assertTrue("This should be true, first capabilities", device.addRPUCapabilities(rpuCapabilities));
        assertFalse("This should be false, same capabilities json", device.addRPUCapabilities(rpuCapabilitie2));
        assertTrue("This should be true, different json", device.addRPUCapabilities(rpuCapabilitie3));
        assertTrue("This should be true", device.addRPUCapabilities(rpuCapabilitie2));

        entityManager.persist(device);
        entityManager.flush();


    }
}
