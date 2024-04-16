package fau.deviceConfiguration.Messages;


import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Generated(value="com.asyncapi.generator.template.spring", date="2024-02-07T01:20:05.568Z")
public class DevicePayload {
    
    private @Valid Integer id;
    
    private @Valid String name;
    
    private @Valid String ip;

    private @Valid String costmodel;
    
    private @Valid String rpuCapabilities;
    
    private @Valid List<PortPayload> ports;
    

    

    
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    
    @JsonProperty("ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    

    @JsonProperty("costmodel")
    public String getCostmodel() {
        return costmodel;
    }

    public void setCostmodel(String costmodel) {
        this.costmodel = costmodel;
    }
    

    
    @JsonProperty("rpuCapabilities")
    public String getRpuCapabilities() {
        return rpuCapabilities;
    }

    public void setRpuCapabilities(String rpuCapabilities) {
        this.rpuCapabilities = rpuCapabilities;
    }
    

    
    @JsonProperty("ports")
    public List<PortPayload> getPorts() {
        return ports;
    }

    public void setPorts(List<PortPayload> ports) {
        this.ports = ports;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DevicePayload devicePayload = (DevicePayload) o;
        return 
            Objects.equals(this.id, devicePayload.id) &&
            Objects.equals(this.name, devicePayload.name) &&
            Objects.equals(this.ip, devicePayload.ip) &&
            Objects.equals(this.costmodel, devicePayload.costmodel) &&
            Objects.equals(this.rpuCapabilities, devicePayload.rpuCapabilities) &&
            Objects.equals(this.ports, devicePayload.ports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ip, costmodel, rpuCapabilities, ports);
    }

    @Override
    public String toString() {
        return "class DevicePayload {\n" +
        
                "    id: " + toIndentedString(id) + "\n" +
                "    name: " + toIndentedString(name) + "\n" +
                "    ip: " + toIndentedString(ip) + "\n" +
                "    costmodel: " + toIndentedString(costmodel) + "\n" +
                "    rpuCapabilities: " + toIndentedString(rpuCapabilities) + "\n" +
                "    ports: " + toIndentedString(ports) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
           return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}