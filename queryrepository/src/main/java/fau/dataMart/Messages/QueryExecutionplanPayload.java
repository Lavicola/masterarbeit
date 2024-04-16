package fau.dataMart.Messages;


import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Generated(value="com.asyncapi.generator.template.spring", date="2024-02-07T01:13:06.627Z")
public class QueryExecutionplanPayload {
    
    private @Valid String statement;
    
    private @Valid String executionplan;
    
    private @Valid java.time.OffsetDateTime timestamp;
    

    

    
    @JsonProperty("statement")
    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
    

    
    @JsonProperty("executionplan")
    public String getExecutionplan() {
        return executionplan;
    }

    public void setExecutionplan(String executionplan) {
        this.executionplan = executionplan;
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
        QueryExecutionplanPayload queryExecutionplanPayload = (QueryExecutionplanPayload) o;
        return 
            Objects.equals(this.statement, queryExecutionplanPayload.statement) &&
            Objects.equals(this.executionplan, queryExecutionplanPayload.executionplan) &&
            Objects.equals(this.timestamp, queryExecutionplanPayload.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statement, executionplan, timestamp);
    }

    @Override
    public String toString() {
        return "class QueryExecutionplanPayload {\n" +
        
                "    statement: " + toIndentedString(statement) + "\n" +
                "    executionplan: " + toIndentedString(executionplan) + "\n" +
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