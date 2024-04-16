package fau.deviceConfiguration.Service;

import fau.datastreamMetric.Repository.DatastreamRepository;
import fau.datastreamMetric.models.Datastream;
import fau.deviceConfiguration.Messages.DeviceMessage;
import fau.deviceConfiguration.Messages.DevicePayload;
import fau.deviceConfiguration.Messages.PortPayload;
import fau.deviceConfiguration.Repository.DeviceRepository;
import fau.deviceConfiguration.models.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceFeatureServiceImpl implements DeviceFeatureService {


    @Autowired
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DatastreamRepository datastreamRepository;


    public DeviceFeatureServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public List<Device> getDevices() {
        return this.deviceRepository.findAll();
    }

    @Override
    public Device getDevicebyName(String name) {
        return this.deviceRepository.findDeviceByName(name);
    }

    @Override
    public Device createEmptyDevice(String devicename, String ip) {
        Device device = new Device(devicename, ip);
        return this.deviceRepository.save(device);
    }

    @Override
    public Device fillDevice(String devicename, String ip, List<Port> ports, Costmodel costmodel, RPUCapabilities rpuCapabilities) {
        Device device1 = this.deviceRepository.findDeviceByName(devicename);
        if (device1 == null) {
            return null;
        }

        device1.setName(devicename);
        device1.setIp(ip);

        // if it actually matters in the future we could check if one of them changed and if we only send a new Message
        boolean isCapa = (rpuCapabilities == null) ? false : device1.addRPUCapabilities(new RPUCapabilities(rpuCapabilities.getCapabilities()));
        boolean isCost = (costmodel == null) ? false : device1.addCostmodel(new Costmodel(costmodel.getCostmodel()));
        boolean isPorts = device1.setPorts(ports);
        this.deviceRepository.save(device1);
        List<Costmodel> costmodels = device1.getCostModels();
        List<RPUCapabilities> capabilities = device1.getCapabilities();
        String current_costmodel;
        String current_capabilities;
        if (costmodels.isEmpty()) {
            // this should never happen!
            current_costmodel = null;
        } else {
            current_costmodel = costmodels.get(costmodels.size() - 1).getCostmodel();
        }
        if (capabilities.isEmpty()) {
            // this should never happen!
            current_capabilities = null;
        } else {
            current_capabilities = capabilities.get(capabilities.size() - 1).getCapabilities();
        }

        if (!(current_capabilities != null && current_costmodel != null)) {
            // if no capa or costmodel exist we dont send the config
            this.sendDeviceConfig(device1, current_costmodel, current_capabilities);
        }

        return device1;
    }

    @Override
    public boolean sendDeviceConfig(Device device, String costmodel, String capability) {
        DevicePayload devicePayload = new DevicePayload();
        PortPayload portPayload;
        List<PortPayload> ports = new ArrayList<>();
        DeviceMessage deviceMessage = new DeviceMessage();
        //set device stuff
        devicePayload.setCostmodel(costmodel);
        devicePayload.setRpuCapabilities(capability);
        devicePayload.setId(device.getId().intValue());
        devicePayload.setName(device.getName());
        devicePayload.setIp(device.getIp());
        //
        for (Port port : device.getPorts()) {
            portPayload = new PortPayload();
            if (port.getDatastream() != null) {
                portPayload.setDatastream(port.getDatastream().getId());
            } else {
                portPayload.setDatastream(null);
            }
            portPayload.setInternalIp(port.getInternalIp());
            portPayload.setIp(port.getIp());
            portPayload.setNumber(port.getNumber());
            ports.add(portPayload);
        }
        devicePayload.setPorts(ports);
        deviceMessage.setPayload(devicePayload);
        String exchangeName = environment.getProperty("rabbitmq.exchange.name");
        String routingKey = environment.getProperty("rabbitmq.device_feature.device_queueKey");
        System.out.println(exchangeName);
        System.out.println(routingKey);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, deviceMessage);

        return true;
    }

    @Override
    public Datastream getDatastream(String name) {
        return this.datastreamRepository.findByName(name);
    }


}
