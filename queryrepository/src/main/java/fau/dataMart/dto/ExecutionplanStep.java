package fau.dataMart.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * ExecutionplanStep
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T22:08:28.313274270+01:00[Europe/Berlin]")
public class ExecutionplanStep {

  private Integer step;

  private String deviceName;

  private String executionstep;

  private String costmodel;

  private String capabilities;

  public ExecutionplanStep step(Integer step) {
    this.step = step;
    return this;
  }

  /**
   * number of the step
   * @return step
  */
  
  @Schema(name = "step", example = "1", description = "number of the step", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("step")
  public Integer getStep() {
    return step;
  }

  public void setStep(Integer step) {
    this.step = step;
  }

  public ExecutionplanStep deviceName(String deviceName) {
    this.deviceName = deviceName;
    return this;
  }

  /**
   * name of the Device which executed this step
   * @return deviceName
  */
  
  @Schema(name = "deviceName", example = "fpga1", description = "name of the Device which executed this step", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("deviceName")
  public String getDeviceName() {
    return deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public ExecutionplanStep executionstep(String executionstep) {
    this.executionstep = executionstep;
    return this;
  }

  /**
   * json executionplan step
   * @return executionstep
  */
  
  @Schema(name = "executionstep", example = "{}", description = "json executionplan step", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("executionstep")
  public String getExecutionstep() {
    return executionstep;
  }

  public void setExecutionstep(String executionstep) {
    this.executionstep = executionstep;
  }

  public ExecutionplanStep costmodel(String costmodel) {
    this.costmodel = costmodel;
    return this;
  }

  /**
   * json of the costmodel for this certain step
   * @return costmodel
  */
  
  @Schema(name = "costmodel", example = "{}", description = "json of the costmodel for this certain step", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("costmodel")
  public String getCostmodel() {
    return costmodel;
  }

  public void setCostmodel(String costmodel) {
    this.costmodel = costmodel;
  }

  public ExecutionplanStep capabilities(String capabilities) {
    this.capabilities = capabilities;
    return this;
  }

  /**
   * json of the capabilities for this certain step
   * @return capabilities
  */
  
  @Schema(name = "capabilities", example = "{}", description = "json of the capabilities for this certain step", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("capabilities")
  public String getCapabilities() {
    return capabilities;
  }

  public void setCapabilities(String capabilities) {
    this.capabilities = capabilities;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExecutionplanStep executionplanStep = (ExecutionplanStep) o;
    return Objects.equals(this.step, executionplanStep.step) &&
        Objects.equals(this.deviceName, executionplanStep.deviceName) &&
        Objects.equals(this.executionstep, executionplanStep.executionstep) &&
        Objects.equals(this.costmodel, executionplanStep.costmodel) &&
        Objects.equals(this.capabilities, executionplanStep.capabilities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(step, deviceName, executionstep, costmodel, capabilities);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExecutionplanStep {\n");
    sb.append("    step: ").append(toIndentedString(step)).append("\n");
    sb.append("    deviceName: ").append(toIndentedString(deviceName)).append("\n");
    sb.append("    executionstep: ").append(toIndentedString(executionstep)).append("\n");
    sb.append("    costmodel: ").append(toIndentedString(costmodel)).append("\n");
    sb.append("    capabilities: ").append(toIndentedString(capabilities)).append("\n");
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

