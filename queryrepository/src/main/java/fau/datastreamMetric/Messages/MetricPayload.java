package fau.datastreamMetric.Messages;


import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Generated(value="com.asyncapi.generator.template.spring", date="2024-02-07T02:20:22.421Z")
public class MetricPayload {
    
    private @Valid String name;
    
    private @Valid String unit;
    
    private @Valid String format;
    
    private @Valid double value;
    
    private @Valid Integer datastreamId;
    
    private @Valid java.time.OffsetDateTime timestamp;
    

    

    
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    
    @JsonProperty("unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    

    
    @JsonProperty("format")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    

    
    @JsonProperty("value")
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    

    
    @JsonProperty("datastreamId")
    public Integer getDatastreamId() {
        return datastreamId;
    }

    public void setDatastreamId(Integer datastreamId) {
        this.datastreamId = datastreamId;
    }
    

    
    @JsonProperty("timestamp")
    public java.time.OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(java.time.OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MetricPayload metricPayload = (MetricPayload) o;
        return 
            Objects.equals(this.name, metricPayload.name) &&
            Objects.equals(this.unit, metricPayload.unit) &&
            Objects.equals(this.format, metricPayload.format) &&
            Objects.equals(this.value, metricPayload.value) &&
            Objects.equals(this.datastreamId, metricPayload.datastreamId) &&
            Objects.equals(this.timestamp, metricPayload.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unit, format, value, datastreamId, timestamp);
    }

    @Override
    public String toString() {
        return "class MetricPayload {\n" +
        
                "    name: " + toIndentedString(name) + "\n" +
                "    unit: " + toIndentedString(unit) + "\n" +
                "    format: " + toIndentedString(format) + "\n" +
                "    value: " + toIndentedString(value) + "\n" +
                "    datastreamId: " + toIndentedString(datastreamId) + "\n" +
                "    timestamp: " + toIndentedString(timestamp) + "\n" +
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