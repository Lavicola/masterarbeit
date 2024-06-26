/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.0.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package fau.datastreamMetric.Controller;

import fau.datastreamMetric.dto.Datapoint;
import fau.datastreamMetric.models.Datastream;
import fau.datastreamMetric.models.Metric;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-04T18:33:35.572386121+01:00[Europe/Berlin]")
@Validated
@Tag(name = "datastream", description = "the datastream API")
public interface DatastreamApi {

    default DatastreamApiDelegate getDelegate() {
        return new DatastreamApiDelegate() {};
    }

    /**
     * GET /datastream/{datastream_name}/metric/{metric_name} : Get Datapoints of a specific metric
     *
     * @param metricName name of the Metric (required)
     * @param datastreamName name of the datastream (required)
     * @return Successful response (status code 200)
     */
    @Operation(
        operationId = "datastreamDatastreamNameMetricMetricNameGet",
        summary = "Get Datapoints of a specific metric",
        tags = { "datastream" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Datapoint.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/datastream/{datastream_name}/metric/{metric_name}",
        produces = { "application/json" }
    )
    default ResponseEntity<List<Datapoint>> datastreamDatastreamNameMetricMetricNameGet(
        @Parameter(name = "metric_name", description = "name of the Metric", required = true, in = ParameterIn.PATH) @PathVariable("metric_name") String metricName,
        @Parameter(name = "datastream_name", description = "name of the datastream", required = true, in = ParameterIn.PATH) @PathVariable("datastream_name") String datastreamName
    ) {
        return getDelegate().datastreamDatastreamNameMetricMetricNameGet(metricName, datastreamName);
    }


    /**
     * GET /datastream : Get all datastreams
     *
     * @return Successful response (status code 200)
     */
    @Operation(
        operationId = "datastreamGet",
        summary = "Get all datastreams",
        tags = { "datastream" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Datastream.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/datastream",
        produces = { "application/json" }
    )
    default ResponseEntity<List<Datastream>> datastreamGet(
        
    ) {
        return getDelegate().datastreamGet();
    }


    /**
     * POST /datastream : Create a new datastream
     *
     * @param datastream  (required)
     * @return Datastream created (status code 200)
     */
    @Operation(
        operationId = "datastreamPost",
        summary = "Create a new datastream",
        tags = { "datastream" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Datastream created", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Datastream.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/datastream",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Datastream> datastreamPost(
        @Parameter(name = "Datastream", description = "", required = true) @Valid @RequestBody Datastream datastream
    ) {
        return getDelegate().datastreamPost(datastream);
    }


    /**
     * GET /datastream/{datastream_name}/metric : Get all metrics of a datastream
     * Returns a list of all available metrics
     *
     * @param datastreamName Datastream name (required)
     * @return All metrics retrieved successfully (status code 200)
     */
    @Operation(
        operationId = "getAllMetrics",
        summary = "Get all metrics of a datastream",
        description = "Returns a list of all available metrics",
        tags = { "datastream" },
        responses = {
            @ApiResponse(responseCode = "200", description = "All metrics retrieved successfully", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Metric.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/datastream/{datastream_name}/metric",
        produces = { "application/json" }
    )
    default ResponseEntity<List<Metric>> getAllMetrics(
        @Parameter(name = "datastream_name", description = "Datastream name", required = true, in = ParameterIn.PATH) @PathVariable("datastream_name") String datastreamName
    ) {
        return getDelegate().getAllMetrics(datastreamName);
    }

}
