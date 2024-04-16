package fau.datastreamMetric.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Datapoint
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-04T18:33:35.572386121+01:00[Europe/Berlin]")
public class Datapoint {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime time;

  private Double value;

  public Datapoint time(LocalDateTime time) {
    this.time = time;
    return this;
  }

  /**
   * LocalDateTime when the Point was inserted
   * @return time
  */
  @Valid 
  @Schema(name = "time", description = "LocalDateTime when the Point was inserted", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("time")
  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }

  public Datapoint value(Double value) {
    this.value = value;
    return this;
  }

  /**
   * Value of the Datapoint
   * @return value
  */
  
  @Schema(name = "value", description = "Value of the Datapoint", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("value")
  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Datapoint datapoint = (Datapoint) o;
    return Objects.equals(this.time, datapoint.time) &&
        Objects.equals(this.value, datapoint.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(time, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Datapoint {\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

