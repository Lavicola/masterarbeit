package fau.deviceConfiguration.Repository;

import fau.deviceConfiguration.models.Device;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends ListCrudRepository<Device,Integer> {

    Device findDeviceById(long id);
    Device findDeviceByName(String name);

}
