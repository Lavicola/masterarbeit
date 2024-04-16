package fau.deviceConfiguration.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * DatastreamPort
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T02:01:40.864532344+01:00[Europe/Berlin]")
public class DatastreamPort {

  private Integer number;

  private String ip;

  private String internalip;

  private String description;

  private String datastreamName;

  public DatastreamPort number(Integer number) {
    this.number = number;
    return this;
  }

  /**
   * port number
   * @return number
  */
  
  @Schema(name = "number", example = "1", description = "port number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("number")
  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public DatastreamPort ip(String ip) {
    this.ip = ip;
    return this;
  }

  /**
   * ip
   * @return ip
  */
  
  @Schema(name = "ip", description = "ip", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ip")
  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public DatastreamPort internalip(String internalip) {
    this.internalip = internalip;
    return this;
  }

  /**
   * internalip
   * @return internalip
  */
  
  @Schema(name = "internalip", description = "internalip", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("internalip")
  public String getInternalip() {
    return internalip;
  }

  public void setInternalip(String internalip) {
    this.internalip = internalip;
  }

  public DatastreamPort description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  
  @Schema(name = "description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DatastreamPort datastreamName(String datastreamName) {
    this.datastreamName = datastreamName;
    return this;
  }

  /**
   * Name of connected Datastream to the Port
   * @return datastreamName
  */
  
  @Schema(name = "datastreamName", example = "Flighstream", description = "Name of connected Datastream to the Port", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("datastreamName")
  public String getDatastreamName() {
    return datastreamName;
  }

  public void setDatastreamName(String datastreamName) {
    this.datastreamName = datastreamName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DatastreamPort datastreamPort = (DatastreamPort) o;
    return Objects.equals(this.number, datastreamPort.number) &&
        Objects.equals(this.ip, datastreamPort.ip) &&
        Objects.equals(this.internalip, datastreamPort.internalip) &&
        Objects.equals(this.description, datastreamPort.description) &&
        Objects.equals(this.datastreamName, datastreamPort.datastreamName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, ip, internalip, description, datastreamName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DatastreamPort {\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    ip: ").append(toIndentedString(ip)).append("\n");
    sb.append("    internalip: ").append(toIndentedString(internalip)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    datastreamName: ").append(toIndentedString(datastreamName)).append("\n");
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

