package fau.dataMart.models;

import fau.deviceConfiguration.models.Costmodel;
import fau.deviceConfiguration.models.RPUCapabilities;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;


@Entity(name = "ExecutionplanStep")
@Table(name = "ExecutionplanStep")
public class ExecutionplanStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int step;

    private String operator;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private String executionplanstep;


    @ManyToOne
    @JoinColumn(name = "costmodel_Id", nullable = false)
    private Executionplan executionplan;

    @ManyToOne
    @JoinColumn(name = "capabilities_id", nullable = false)
    private RPUCapabilities rpuCapabilities;


    @ManyToOne
    @JoinColumn(name = "executionplan_hash", nullable = false)
    private Costmodel costmodel;



    public ExecutionplanStep(String operator,int step, String executionplanstep,Executionplan executionplan,Costmodel costmodel,RPUCapabilities rpuCapabilities) {
        this.operator = operator;
        this.step = step;
        this.executionplanstep = executionplanstep;
        this.executionplan = executionplan;
        this.costmodel = costmodel;
        this.rpuCapabilities = rpuCapabilities;
    }


    public ExecutionplanStep() {

    }

    public RPUCapabilities getRpuCapabilities() {
        return rpuCapabilities;
    }

    public Costmodel getCostmodel() {
        return costmodel;
    }

    public Executionplan getExecutionplan() {
        return executionplan;
    }

    public String getExecutionplanstep() {
        return executionplanstep;
    }

    public String getOperator() {
        return operator;
    }

    public int getStep() {
        return step;
    }
}

