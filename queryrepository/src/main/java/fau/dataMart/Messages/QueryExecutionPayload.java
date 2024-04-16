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


@Generated(value="com.asyncapi.generator.template.spring", date="2024-02-07T01:13:06.615Z")
public class QueryExecutionPayload {
    
    private @Valid String statement;
    

    

    
    @JsonProperty("statement")
    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QueryExecutionPayload queryExecutionPayload = (QueryExecutionPayload) o;
        return 
            Objects.equals(this.statement, queryExecutionPayload.statement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statement);
    }

    @Override
    public String toString() {
        return "class QueryExecutionPayload {\n" +
        
                "    statement: " + toIndentedString(statement) + "\n" +
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