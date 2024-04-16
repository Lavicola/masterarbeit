package fau.dataMart.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Executionplan
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T22:08:28.313274270+01:00[Europe/Berlin]")
public class Executionplan {

  private String executionplan;

  private String executionplanHash;

  @Valid
  private List<@Valid ExecutionplanStep> steps;

  public Executionplan executionplan(String executionplan) {
    this.executionplan = executionplan;
    return this;
  }

  /**
   * execution plan as JSON
   * @return executionplan
  */
  
  @Schema(name = "executionplan", description = "execution plan as JSON", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("executionplan")
  public String getExecutionplan() {
    return executionplan;
  }

  public void setExecutionplan(String executionplan) {
    this.executionplan = executionplan;
  }

  public Executionplan executionplanHash(String executionplanHash) {
    this.executionplanHash = executionplanHash;
    return this;
  }

  /**
   * hashed execution plan
   * @return executionplanHash
  */
  
  @Schema(name = "executionplanHash", example = "HASH({...})", description = "hashed execution plan", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("executionplanHash")
  public String getExecutionplanHash() {
    return executionplanHash;
  }

  public void setExecutionplanHash(String executionplanHash) {
    this.executionplanHash = executionplanHash;
  }

  public Executionplan steps(List<@Valid ExecutionplanStep> steps) {
    this.steps = steps;
    return this;
  }

  public Executionplan addStepsItem(ExecutionplanStep stepsItem) {
    if (this.steps == null) {
      this.steps = new ArrayList<>();
    }
    this.steps.add(stepsItem);
    return this;
  }

  /**
   * Get steps
   * @return steps
  */
  @Valid 
  @Schema(name = "steps", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("steps")
  public List<@Valid ExecutionplanStep> getSteps() {
    return steps;
  }

  public void setSteps(List<@Valid ExecutionplanStep> steps) {
    this.steps = steps;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Executionplan executionplan = (Executionplan) o;
    return Objects.equals(this.executionplan, executionplan.executionplan) &&
        Objects.equals(this.executionplanHash, executionplan.executionplanHash) &&
        Objects.equals(this.steps, executionplan.steps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(executionplan, executionplanHash, steps);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Executionplan {\n");
    sb.append("    executionplan: ").append(toIndentedString(executionplan)).append("\n");
    sb.append("    executionplanHash: ").append(toIndentedString(executionplanHash)).append("\n");
    sb.append("    steps: ").append(toIndentedString(steps)).append("\n");
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

