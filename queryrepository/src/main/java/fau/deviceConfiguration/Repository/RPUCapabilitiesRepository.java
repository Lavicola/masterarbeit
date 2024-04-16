package fau.deviceConfiguration.Repository;


import fau.deviceConfiguration.models.RPUCapabilities;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface RPUCapabilitiesRepository extends ListCrudRepository<RPUCapabilities,Integer> {

    @Query("SELECT e FROM Capabilities  e WHERE :timestamp >= e.start AND :timestamp <= e.end AND e.device.id = :deviceId")
    RPUCapabilities findRPUCapabilitiesAtTimeForDevice(@Param("deviceId") Long deviceId, @Param("timestamp") LocalDateTime timestamp);

    RPUCapabilities findRPUCapabilitiesById(long id);

}
