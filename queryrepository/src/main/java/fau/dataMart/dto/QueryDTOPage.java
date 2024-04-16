package fau.dataMart.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * QueryDTOPage
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T22:08:28.313274270+01:00[Europe/Berlin]")
public class QueryDTOPage {

  @Valid
  private List<@Valid QueryDTO> queries;

  private Integer totalPages;

  public QueryDTOPage queries(List<@Valid QueryDTO> queries) {
    this.queries = queries;
    return this;
  }

  public QueryDTOPage addQueriesItem(QueryDTO queriesItem) {
    if (this.queries == null) {
      this.queries = new ArrayList<>();
    }
    this.queries.add(queriesItem);
    return this;
  }

  /**
   * Get queries
   * @return queries
  */
  @Valid 
  @Schema(name = "queries", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("queries")
  public List<@Valid QueryDTO> getQueries() {
    return queries;
  }

  public void setQueries(List<@Valid QueryDTO> queries) {
    this.queries = queries;
  }

  public QueryDTOPage totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * Get totalPages
   * @return totalPages
  */
  
  @Schema(name = "totalPages", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalPages")
  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryDTOPage queryDTOPage = (QueryDTOPage) o;
    return Objects.equals(this.queries, queryDTOPage.queries) &&
        Objects.equals(this.totalPages, queryDTOPage.totalPages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(queries, totalPages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueryDTOPage {\n");
    sb.append("    queries: ").append(toIndentedString(queries)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
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

