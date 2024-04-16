package fau.deviceConfiguration.Controller;

import fau.deviceConfiguration.Service.DeviceFeatureService;
import fau.deviceConfiguration.dto.DeviceDTO;
import fau.deviceConfiguration.models.Device;
import fau.deviceConfiguration.models.Port;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeviceApiControllerImpl implements DevicesApiDelegate {

    @Autowired
    DeviceFeatureService deviceFeatureService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ResponseEntity<List<DeviceDTO>> devicesGet() {
        List<Device> devices = this.deviceFeatureService.getDevices();
        List<DeviceDTO> dto_devices = devices.stream().map(
                source -> this.modelMapper.map(source, DeviceDTO.class)).toList();
        dto_devices.forEach(dtoDevice -> removeRelationship(dtoDevice));
        return ResponseEntity.status(HttpStatus.OK).body(dto_devices);

    }

    public ResponseEntity<DeviceDTO> devicesNameGet(String name) {
        Device device = this.deviceFeatureService.getDevicebyName(name);
        DeviceDTO dtoDevice = new DeviceDTO();
        dtoDevice.setIp(device.getIp());
        dtoDevice.setName(device.getName());
        dtoDevice.setCapabilities(device.getCapabilities());
        dtoDevice.setCostmodels(device.getCostModels());
        List<fau.deviceConfiguration.dto.DatastreamPort> ports = new ArrayList<>();
        fau.deviceConfiguration.dto.DatastreamPort dtoport;
        for (Port port : device.getPorts()) {
            dtoport = new fau.deviceConfiguration.dto.DatastreamPort();
            dtoport.setIp(port.getIp());
            dtoport.setDescription(port.getDescription());
            dtoport.setInternalip(port.getInternalIp());
            dtoport.setNumber(port.getNumber());
            if (port.getDatastream() != null) {
                dtoport.setDatastreamName(port.getDatastream().getName());
            } else {
                dtoport.setDatastreamName(null);
            }
            ports.add(dtoport);
        }
        dtoDevice.setPorts(ports);

        return ResponseEntity.status(HttpStatus.OK).body(removeRelationship(dtoDevice));
    }

    public ResponseEntity<DeviceDTO> devicesPost(DeviceDTO deviceDTO) {
        Device device1 = null;

        device1 = this.deviceFeatureService.createEmptyDevice(deviceDTO.getName(), deviceDTO.getIp());
        if (device1 != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(deviceDTO);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

    }


    public ResponseEntity<DeviceDTO> devicesPut(DeviceDTO deviceDTO) {
        Device device1 = null;

        device1 = this.modelMapper.map(deviceDTO, Device.class);
        ArrayList<Port> ports = new ArrayList<>();
        for (fau.deviceConfiguration.dto.DatastreamPort port : deviceDTO.getPorts()) {
            ports.add(new Port(port.getNumber(),
                    port.getDescription(),
                    port.getIp(),
                    port.getInternalip(),
                    this.deviceFeatureService.getDatastream(port.getDatastreamName())));
        }
        device1 = this.deviceFeatureService.fillDevice(device1.getName(), device1.getIp(),
                ports, device1.getCostModels().get(0),
                device1.getCapabilities().get(0));

        if (device1 != null) {
            return ResponseEntity.status(HttpStatus.OK).body(deviceDTO);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

    }


    private DeviceDTO removeRelationship(DeviceDTO dtoDevice) {
        // Either more DTO Objects without device references or simply removing it
        // I decided to remove them, otherwise exception thrown because costmodel --> device --> costmodel
        if (dtoDevice.getCostmodels() != null) {
            dtoDevice.getCostmodels().forEach(costmodel -> costmodel.setDevice(null));
        }
        if (dtoDevice.getCapabilities() != null) {
            dtoDevice.getCapabilities().forEach(capabilities -> capabilities.setDevice(null));
        }

        return dtoDevice;
    }

}