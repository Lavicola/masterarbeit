package fau.deviceConfiguration.Repository;

import fau.deviceConfiguration.models.Costmodel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CostmodelRepository extends ListCrudRepository<Costmodel,Integer> {


    @Query("SELECT e FROM CostModel e WHERE :timestamp >= e.start AND :timestamp <= e.end AND e.device.id = :deviceId")
    Costmodel findCostmodelAtTimeForDevice(@Param("deviceId") Long deviceId, @Param("timestamp") LocalDateTime timestamp);

    Costmodel findCostmodelById(Long id);

}