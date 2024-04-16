package fau.dataMart.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Query
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-31T15:45:29.164765450+01:00[Europe/Berlin]")
public class Query {

  private String statement;

  private String statementHash;

  private String executionplanHash;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate timestamp;

  public Query statement(String statement) {
    this.statement = statement;
    return this;
  }

  /**
   * Statement of an query
   * @return statement
  */
  
  @Schema(name = "statement", example = "SELECT * FROM", description = "Statement of an query", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("statement")
  public String getStatement() {
    return statement;
  }

  public void setStatement(String statement) {
    this.statement = statement;
  }

  public Query statementHash(String statementHash) {
    this.statementHash = statementHash;
    return this;
  }

  /**
   * Statementhash of an query
   * @return statementHash
  */
  
  @Schema(name = "statementHash", example = "HASH(SELECT * FROM)", description = "Statementhash of an query", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("statementHash")
  public String getStatementHash() {
    return statementHash;
  }

  public void setStatementHash(String statementHash) {
    this.statementHash = statementHash;
  }

  public Query executionplanHash(String executionplanHash) {
    this.executionplanHash = executionplanHash;
    return this;
  }

  /**
   * Hash of a Costplan
   * @return executionplanHash
  */
  
  @Schema(name = "executionplanHash", example = "HASH(costplan)", description = "Hash of a Costplan", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("executionplanHash")
  public String getExecutionplanHash() {
    return executionplanHash;
  }

  public void setExecutionplanHash(String executionplanHash) {
    this.executionplanHash = executionplanHash;
  }

  public Query timestamp(LocalDate timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Incoming Time of the Query
   * @return timestamp
  */
  @Valid 
  @Schema(name = "timestamp", example = "31536000", description = "Incoming Time of the Query", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("timestamp")
  public LocalDate getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDate timestamp) {
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
    Query query = (Query) o;
    return Objects.equals(this.statement, query.statement) &&
        Objects.equals(this.statementHash, query.statementHash) &&
        Objects.equals(this.executionplanHash, query.executionplanHash) &&
        Objects.equals(this.timestamp, query.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statement, statementHash, executionplanHash, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Query {\n");
    sb.append("    statement: ").append(toIndentedString(statement)).append("\n");
    sb.append("    statementHash: ").append(toIndentedString(statementHash)).append("\n");
    sb.append("    executionplanHash: ").append(toIndentedString(executionplanHash)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

