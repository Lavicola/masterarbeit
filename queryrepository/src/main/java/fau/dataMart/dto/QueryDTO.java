package fau.dataMart.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import fau.dataMart.models.Executionplan;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * QueryDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T22:08:28.313274270+01:00[Europe/Berlin]")
public class QueryDTO {

  private String statement;

  private String statementHash;

  private String executionplanHash;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate timestamp;

  @Valid
  private List<@Valid Executionplan> executionplans;

  public QueryDTO statement(String statement) {
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

  public QueryDTO statementHash(String statementHash) {
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

  public QueryDTO executionplanHash(String executionplanHash) {
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

  public QueryDTO timestamp(LocalDate timestamp) {
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

  public QueryDTO executionplans(List<@Valid Executionplan> executionplans) {
    this.executionplans = executionplans;
    return this;
  }

  public QueryDTO addExecutionplansItem(Executionplan executionplansItem) {
    if (this.executionplans == null) {
      this.executionplans = new ArrayList<>();
    }
    this.executionplans.add(executionplansItem);
    return this;
  }

  /**
   * Get executionplans
   * @return executionplans
  */
  @Valid 
  @Schema(name = "executionplans", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("executionplans")
  public List<@Valid Executionplan> getExecutionplans() {
    return executionplans;
  }

  public void setExecutionplans(List<@Valid Executionplan> executionplans) {
    this.executionplans = executionplans;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryDTO queryDTO = (QueryDTO) o;
    return Objects.equals(this.statement, queryDTO.statement) &&
        Objects.equals(this.statementHash, queryDTO.statementHash) &&
        Objects.equals(this.executionplanHash, queryDTO.executionplanHash) &&
        Objects.equals(this.timestamp, queryDTO.timestamp) &&
        Objects.equals(this.executionplans, queryDTO.executionplans);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statement, statementHash, executionplanHash, timestamp, executionplans);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueryDTO {\n");
    sb.append("    statement: ").append(toIndentedString(statement)).append("\n");
    sb.append("    statementHash: ").append(toIndentedString(statementHash)).append("\n");
    sb.append("    executionplanHash: ").append(toIndentedString(executionplanHash)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    executionplans: ").append(toIndentedString(executionplans)).append("\n");
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

