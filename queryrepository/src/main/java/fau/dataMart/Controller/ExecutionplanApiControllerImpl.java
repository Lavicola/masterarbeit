package fau.dataMart.Controller;
import fau.dataMart.models.ExecutionplanStep;
import fau.dataMart.Service.QueryFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ExecutionplanApiControllerImpl implements ExecutionplanApiDelegate {

    @Autowired
    QueryFeatureService queryFeatureService;




@Override
    public ResponseEntity<List<fau.dataMart.dto.ExecutionplanStep>> executionplanHashStepsGet(String hash) {
        List<fau.dataMart.models.ExecutionplanStep> executionplanStepList = this.queryFeatureService.getExecutionplanSteps(hash);
        // convert to DTO Array
        fau.dataMart.dto.ExecutionplanStep dtostep;
        List<fau.dataMart.dto.ExecutionplanStep> executionplanSteps = new ArrayList<>();
        for(ExecutionplanStep executionplanStep: executionplanStepList){
            dtostep = new fau.dataMart.dto.ExecutionplanStep();
            dtostep.setCapabilities(executionplanStep.getRpuCapabilities().getCapabilities());
            dtostep.setCostmodel(executionplanStep.getCostmodel().getCostmodel());
            dtostep.setStep(executionplanStep.getStep());
            dtostep.setDeviceName(executionplanStep.getCostmodel().getDevice().getName());
            executionplanSteps.add(dtostep);
        }

        return ResponseEntity.status(HttpStatus.OK).body(executionplanSteps);
    }



}
