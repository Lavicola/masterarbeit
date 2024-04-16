package fau.dataMart.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryExecutionDTO{
    @JsonProperty(value = "statement")
    private String statement;

    public QueryExecutionDTO(String statement){
        this.statement=statement;
    }

}

