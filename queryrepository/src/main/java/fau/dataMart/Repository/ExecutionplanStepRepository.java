package fau.dataMart.Repository;

import fau.deviceConfiguration.models.Costmodel;
import fau.deviceConfiguration.models.RPUCapabilities;
import fau.dataMart.models.Executionplan;
import fau.dataMart.models.ExecutionplanStep;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExecutionplanStepRepository extends ListCrudRepository<ExecutionplanStep, Integer> {

    List<ExecutionplanStep> findByExecutionplan(Executionplan executionplan);

    List<ExecutionplanStep> findByCostmodel(Costmodel costmodel);

    List<ExecutionplanStep> findByRpuCapabilities(RPUCapabilities rpuCapabilities);

}

