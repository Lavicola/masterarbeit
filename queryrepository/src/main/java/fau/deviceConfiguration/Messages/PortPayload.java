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


@Generated(value="com.asyncapi.generator.template.spring", date="2024-02-07T01:20:05.579Z")
public class PortPayload {
    
    private @Valid Integer number;
    
    private @Valid String ip;
    
    private @Valid String internalIp;
    
    private @Valid Integer datastream;
    

    

    
    @JsonProperty("number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    

    
    @JsonProperty("ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    

    
    @JsonProperty("internalIp")
    public String getInternalIp() {
        return internalIp;
    }

    public void setInternalIp(String internalIp) {
        this.internalIp = internalIp;
    }
    

    
    @JsonProperty("datastream")
    public Integer getDatastream() {
        return datastream;
    }

    public void setDatastream(Integer datastream) {
        this.datastream = datastream;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PortPayload portPayload = (PortPayload) o;
        return 
            Objects.equals(this.number, portPayload.number) &&
            Objects.equals(this.ip, portPayload.ip) &&
            Objects.equals(this.internalIp, portPayload.internalIp) &&
            Objects.equals(this.datastream, portPayload.datastream);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, ip, internalIp, datastream);
    }

    @Override
    public String toString() {
        return "class PortPayload {\n" +
        
                "    number: " + toIndentedString(number) + "\n" +
                "    ip: " + toIndentedString(ip) + "\n" +
                "    internalIp: " + toIndentedString(internalIp) + "\n" +
                "    datastream: " + toIndentedString(datastream) + "\n" +
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