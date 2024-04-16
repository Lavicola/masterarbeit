package fau.deviceConfiguration.models;

import fau.HashingStrategies.HashingStrategy;
import fau.HashingStrategies.HashingStrategyMD5;
import fau.dataMart.models.ExecutionplanStep;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity(name = "CostModel")
@Table(name = "costmodel")
public class Costmodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private String costmodel;

    private String costmodelHash;

    @Column(name = "start_date") // or any other suitable name
    private LocalDateTime start;
    @Column(name = "end_date") // or any other suitable name
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name = "device_id")
    protected Device device;

    private int version;

    @OneToMany(mappedBy = "costmodel", cascade = CascadeType.ALL)
    private List<ExecutionplanStep> steps = new ArrayList<>();


    @Transient
    HashingStrategy hashingStrategy = new HashingStrategyMD5();

    // kind of hack, but it greatly reduces needed code.
    // this way we can insert more logic into the model object since
    // we have now a way to identify the current capabilities
    @Transient
    static LocalDateTime COSTMODEL_END_DEFAULT_VALUE = LocalDateTime.of(2023, 2, 1, 0, 0, 0, 0).plusYears(50);

    public Costmodel(String costmodel) {
        this.costmodel = costmodel;
        this.costmodelHash = hashingStrategy.hash(costmodel);
        this.start = LocalDateTime.now();
        this.end = COSTMODEL_END_DEFAULT_VALUE;
    }

    public Costmodel(String costmodel,LocalDateTime start) {
        this(costmodel);
        this.start = start;

    }


    public Costmodel(String costmodel,Device device,LocalDateTime start) {
        this(costmodel);
        this.device = device;
        this.start = start;
    }


    public Costmodel() {

    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setEndDate(LocalDateTime enddate){
        this.end = enddate;
    }

    public String getCostmodel() {
        return costmodel;
    }

    public void setCostmodel(String costmodel){
        this.costmodel = costmodel;
    }

    public String getCostmodelHash() {
        return costmodelHash;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}