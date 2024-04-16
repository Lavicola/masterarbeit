package fau.deviceConfiguration.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * RPUCapabilities
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T02:01:40.864532344+01:00[Europe/Berlin]")
public class RPUCapabilities {

  private String capabilities;

  private Integer version;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime start;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime end;

  public RPUCapabilities capabilities(String capabilities) {
    this.capabilities = capabilities;
    return this;
  }

  /**
   * json of the plan
   * @return capabilities
  */
  
  @Schema(name = "capabilities", description = "json of the plan", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("capabilities")
  public String getCapabilities() {
    return capabilities;
  }

  public void setCapabilities(String capabilities) {
    this.capabilities = capabilities;
  }

  public RPUCapabilities version(Integer version) {
    this.version = version;
    return this;
  }

  /**
   * version of the plan
   * @return version
  */
  
  @Schema(name = "version", description = "version of the plan", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("version")
  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public RPUCapabilities start(LocalDateTime start) {
    this.start = start;
    return this;
  }

  /**
   * when the capability started to get used
   * @return start
  */
  @Valid 
  @Schema(name = "start", description = "when the capability started to get used", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("start")
  public LocalDateTime getStart() {
    return start;
  }

  public void setStart(LocalDateTime start) {
    this.start = start;
  }

  public RPUCapabilities end(LocalDateTime end) {
    this.end = end;
    return this;
  }

  /**
   * when the capability ended to get used
   * @return end
  */
  @Valid 
  @Schema(name = "end", description = "when the capability ended to get used", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    RPUCapabilities rpUCapabilities = (RPUCapabilities) o;
    return Objects.equals(this.capabilities, rpUCapabilities.capabilities) &&
        Objects.equals(this.version, rpUCapabilities.version) &&
        Objects.equals(this.start, rpUCapabilities.start) &&
        Objects.equals(this.end, rpUCapabilities.end);
  }

  @Override
  public int hashCode() {
    return Objects.hash(capabilities, version, start, end);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RPUCapabilities {\n");
    sb.append("    capabilities: ").append(toIndentedString(capabilities)).append("\n");
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

