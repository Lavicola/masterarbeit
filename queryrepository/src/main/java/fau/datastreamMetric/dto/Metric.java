package fau.datastreamMetric.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Metric
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-04T18:33:35.572386121+01:00[Europe/Berlin]")
public class Metric {

  private Integer id;

  private String name;

  private String unit;

  public Metric id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * id of Metric
   * @return id
  */
  
  @Schema(name = "id", example = "1", description = "id of Metric", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Metric name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the Metric
   * @return name
  */
  
  @Schema(name = "name", example = "Selectivity", description = "Name of the Metric", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Metric unit(String unit) {
    this.unit = unit;
    return this;
  }

  /**
   * Unit of the Metric
   * @return unit
  */
  
  @Schema(name = "unit", example = "km/h", description = "Unit of the Metric", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("unit")
  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Metric metric = (Metric) o;
    return Objects.equals(this.id, metric.id) &&
        Objects.equals(this.name, metric.name) &&
        Objects.equals(this.unit, metric.unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, unit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Metric {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
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

