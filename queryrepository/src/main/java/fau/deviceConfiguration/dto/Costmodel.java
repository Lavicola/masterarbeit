package fau.deviceConfiguration.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Costmodel
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T02:01:40.864532344+01:00[Europe/Berlin]")
public class Costmodel {

  private String costmodel;

  private Integer version;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime start;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime end;

  public Costmodel costmodel(String costmodel) {
    this.costmodel = costmodel;
    return this;
  }

  /**
   * json of the costmodel
   * @return costmodel
  */
  
  @Schema(name = "costmodel", description = "json of the costmodel", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("costmodel")
  public String getCostmodel() {
    return costmodel;
  }

  public void setCostmodel(String costmodel) {
    this.costmodel = costmodel;
  }

  public Costmodel version(Integer version) {
    this.version = version;
    return this;
  }

  /**
   * version of the costmodel
   * @return version
  */
  
  @Schema(name = "version", description = "version of the costmodel", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("version")
  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Costmodel start(LocalDateTime start) {
    this.start = start;
    return this;
  }

  /**
   * when the costmodel started to get used
   * @return start
  */
  @Valid 
  @Schema(name = "start", description = "when the costmodel started to get used", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("start")
  public LocalDateTime getStart() {
    return start;
  }

  public void setStart(LocalDateTime start) {
    this.start = start;
  }

  public Costmodel end(LocalDateTime end) {
    this.end = end;
    return this;
  }

  /**
   * when the costmodel ended to get used
   * @return end
  */
  @Valid 
  @Schema(name = "end", description = "when the costmodel ended to get used", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("end")
  public LocalDateTime getEnd() {
    return end;
  }

  public void setEnd(LocalDateTime end) {
    this.end = end;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Costmodel costmodel = (Costmodel) o;
    return Objects.equals(this.costmodel, costmodel.costmodel) &&
        Objects.equals(this.version, costmodel.version) &&
        Objects.equals(this.start, costmodel.start) &&
        Objects.equals(this.end, costmodel.end);
  }

  @Override
  public int hashCode() {
    return Objects.hash(costmodel, version, start, end);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Costmodel {\n");
    sb.append("    costmodel: ").append(toIndentedString(costmodel)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    end: ").append(toIndentedString(end)).append("\n");
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

