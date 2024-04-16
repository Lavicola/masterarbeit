package fau.datastreamMetric.Controller;

import fau.datastreamMetric.dto.Datapoint;
import fau.datastreamMetric.models.Datastream;
import fau.datastreamMetric.models.Metric;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link DatastreamApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-04T18:33:35.572386121+01:00[Europe/Berlin]")
public interface DatastreamApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /datastream/{datastream_name}/metric/{metric_name} : Get Datapoints of a specific metric
     *
     * @param metricName name of the Metric (required)
     * @param datastreamName name of the datastream (required)
     * @return Successful response (status code 200)
     * @see DatastreamApi#datastreamDatastreamNameMetricMetricNameGet
     */
    default ResponseEntity<List<Datapoint>> datastreamDatastreamNameMetricMetricNameGet(String metricName,
        String datastreamName) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"time\" : \"2000-01-23T04:56:07.000+00:00\", \"value\" : 0.8008281904610115 }, { \"time\" : \"2000-01-23T04:56:07.000+00:00\", \"value\" : 0.8008281904610115 } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /datastream : Get all datastreams
     *
     * @return Successful response (status code 200)
     * @see DatastreamApi#datastreamGet
     */
    default ResponseEntity<List<Datastream>> datastreamGet() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"id\" : 1, \"name\" : \"Customer\", \"stream\" : \"\" }, { \"id\" : 1, \"name\" : \"Customer\", \"stream\" : \"\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /datastream : Create a new datastream
     *
     * @param datastream  (required)
     * @return Datastream created (status code 200)
     * @see DatastreamApi#datastreamPost
     */
    default ResponseEntity<Datastream> datastreamPost(Datastream datastream) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : 1, \"name\" : \"Customer\", \"stream\" : \"\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /datastream/{datastream_name}/metric : Get all metrics of a datastream
     * Returns a list of all available metrics
     *
     * @param datastreamName Datastream name (required)
     * @return All metrics retrieved successfully (status code 200)
     * @see DatastreamApi#getAllMetrics
     */
    default ResponseEntity<List<Metric>> getAllMetrics(String datastreamName) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"id\" : 1, \"name\" : \"Selectivity\", \"unit\" : \"km/h\" }, { \"id\" : 1, \"name\" : \"Selectivity\", \"unit\" : \"km/h\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
