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
@Entity(name = "Capabilities")
@Table(name = "capabilities")
public class RPUCapabilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private String capabilities;

    private String capabilitiesHash;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(name = "start_date") // or any other suitable name
    private LocalDateTime start;
    @Column(name = "end_date") // or any other suitable name
    private LocalDateTime end;

    private int version;

    @OneToMany()
    private List<ExecutionplanStep> steps = new ArrayList<>();

    @Transient
    HashingStrategy hashingStrategy = new HashingStrategyMD5();

    // kind of hack, but it greatly reduces needed code.
    // this way we can insert more logic into the model object since
    // we have now a way to identify the current capabilities
    @Transient
    static LocalDateTime RPUCAPABILITIES_END_DEFAULT_VALUE = LocalDateTime.of(2023, 2, 1, 0, 0, 0, 0).plusYears(50);
    ;

    public RPUCapabilities(String capabilities) {
        this.capabilities = capabilities;
        this.capabilitiesHash = this.hashingStrategy.hash(capabilities);
        this.start = LocalDateTime.now();
        this.end = RPUCAPABILITIES_END_DEFAULT_VALUE;
    }

    public RPUCapabilities(String capabilities, LocalDateTime start) {
        this(capabilities);
        this.start = start;
    }


    public RPUCapabilities(String capabilities, Device device, LocalDateTime start) {
        this(capabilities);
        this.device = device;
        this.start = start;
    }


    public RPUCapabilities() {

    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.end = endDate;
    }

    public String getCapabilitiesHash() {
        return capabilitiesHash;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}