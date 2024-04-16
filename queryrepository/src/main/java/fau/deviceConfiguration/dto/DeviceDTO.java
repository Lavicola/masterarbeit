package fau.deviceConfiguration.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import fau.deviceConfiguration.models.Costmodel;
import fau.deviceConfiguration.models.RPUCapabilities;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * DeviceDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T02:01:40.864532344+01:00[Europe/Berlin]")
public class DeviceDTO {

  private String name;

  private String ip;

  @Valid
  private List<@Valid DatastreamPort> ports;

  @Valid
  private List<@Valid RPUCapabilities> capabilities;

  @Valid
  private List<@Valid Costmodel> costmodels;

  public DeviceDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Device name
   * @return name
  */
  
  @Schema(name = "name", example = "RPU1", description = "Device name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DeviceDTO ip(String ip) {
    this.ip = ip;
    return this;
  }

  /**
   * Get ip
   * @return ip
  */
  
  @Schema(name = "ip", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ip")
  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public DeviceDTO ports(List<@Valid DatastreamPort> ports) {
    this.ports = ports;
    return this;
  }

  public DeviceDTO addPortsItem(DatastreamPort portsItem) {
    if (this.ports == null) {
      this.ports = new ArrayList<>();
    }
    this.ports.add(portsItem);
    return this;
  }

  /**
   * List of ports connected to the device
   * @return ports
  */
  @Valid 
  @Schema(name = "ports", description = "List of ports connected to the device", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ports")
  public List<@Valid DatastreamPort> getPorts() {
    return ports;
  }

  public void setPorts(List<@Valid DatastreamPort> ports) {
    this.ports = ports;
  }

  public DeviceDTO capabilities(List<@Valid RPUCapabilities> capabilities) {
    this.capabilities = capabilities;
    return this;
  }

  public DeviceDTO addCapabilitiesItem(RPUCapabilities capabilitiesItem) {
    if (this.capabilities == null) {
      this.capabilities = new ArrayList<>();
    }
    this.capabilities.add(capabilitiesItem);
    return this;
  }

  /**
   * Capabilities of the device
   * @return capabilities
  */
  @Valid 
  @Schema(name = "capabilities", description = "Capabilities of the device", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("capabilities")
  public List<@Valid RPUCapabilities> getCapabilities() {
    return capabilities;
  }

  public void setCapabilities(List<@Valid RPUCapabilities> capabilities) {
    this.capabilities = capabilities;
  }

  public DeviceDTO costmodels(List<@Valid Costmodel> costmodels) {
    this.costmodels = costmodels;
    return this;
  }

  public DeviceDTO addCostmodelsItem(Costmodel costmodelsItem) {
    if (this.costmodels == null) {
      this.costmodels = new ArrayList<>();
    }
    this.costmodels.add(costmodelsItem);
    return this;
  }

  /**
   * Costmodells of the device
   * @return costmodels
  */
  @Valid 
  @Schema(name = "costmodels", description = "Costmodells of the device", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("costmodels")
  public List<@Valid Costmodel> getCostmodels() {
    return costmodels;
  }

  public void setCostmodels(List<@Valid Costmodel> costmodels) {
    this.costmodels = costmodels;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeviceDTO deviceDTO = (DeviceDTO) o;
    return Objects.equals(this.name, deviceDTO.name) &&
        Objects.equals(this.ip, deviceDTO.ip) &&
        Objects.equals(this.ports, deviceDTO.ports) &&
        Objects.equals(this.capabilities, deviceDTO.capabilities) &&
        Objects.equals(this.costmodels, deviceDTO.costmodels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, ip, ports, capabilities, costmodels);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeviceDTO {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    ip: ").append(toIndentedString(ip)).append("\n");
    sb.append("    ports: ").append(toIndentedString(ports)).append("\n");
    sb.append("    capabilities: ").append(toIndentedString(capabilities)).append("\n");
    sb.append("    costmodels: ").append(toIndentedString(costmodels)).append("\n");
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

