package fau.deviceConfiguration.Service;

import fau.datastreamMetric.models.Datastream;
import fau.deviceConfiguration.models.*;

import java.util.List;

public interface DeviceFeatureService {

    List<Device> getDevices();
    Device getDevicebyName(String name);
    // first a device without anything
    Device createEmptyDevice(String devicename,String ip);
    Device fillDevice(String devicename,String ip, List<Port> ports, Costmodel costmodel, RPUCapabilities rpuCapabilities);
    boolean sendDeviceConfig(Device device,String costmodel,String capability);
    Datastream getDatastream(String name);

}
