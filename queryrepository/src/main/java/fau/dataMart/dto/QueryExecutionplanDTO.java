package fau.dataMart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Data
@Getter
public class QueryExecutionplanDTO{
    @JsonProperty("statement")
    String statement;
    @JsonProperty("executionplan")
    String executionplan;
    @JsonProperty("timestamp")
    LocalDateTime timestamp;


    public String getExecutionplan(){
        return this.executionplan;
    }

    public String getStatement(){
        return this.statement;
    }


    public LocalDateTime getTimestamp(){
        return this.timestamp;
    }

}
