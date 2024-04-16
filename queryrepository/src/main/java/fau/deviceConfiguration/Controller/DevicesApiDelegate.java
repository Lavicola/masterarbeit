package fau.deviceConfiguration.Controller;

import fau.deviceConfiguration.dto.DeviceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link DevicesApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T02:01:40.864532344+01:00[Europe/Berlin]")
public interface DevicesApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /devices : Get all Devices
     *
     * @return Successful response (status code 200)
     * @see DevicesApi#devicesGet
     */
    default ResponseEntity<List<DeviceDTO>> devicesGet() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"capabilities\" : [ { \"capabilities\" : \"capabilities\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 0 }, { \"capabilities\" : \"capabilities\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 0 } ], \"ip\" : \"ip\", \"name\" : \"RPU1\", \"ports\" : [ { \"datastreamName\" : \"Flighstream\", \"number\" : 1, \"ip\" : \"ip\", \"description\" : \"description\", \"internalip\" : \"internalip\" }, { \"datastreamName\" : \"Flighstream\", \"number\" : 1, \"ip\" : \"ip\", \"description\" : \"description\", \"internalip\" : \"internalip\" } ], \"costmodels\" : [ { \"costmodel\" : \"costmodel\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 6 }, { \"costmodel\" : \"costmodel\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 6 } ] }, { \"capabilities\" : [ { \"capabilities\" : \"capabilities\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 0 }, { \"capabilities\" : \"capabilities\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 0 } ], \"ip\" : \"ip\", \"name\" : \"RPU1\", \"ports\" : [ { \"datastreamName\" : \"Flighstream\", \"number\" : 1, \"ip\" : \"ip\", \"description\" : \"description\", \"internalip\" : \"internalip\" }, { \"datastreamName\" : \"Flighstream\", \"number\" : 1, \"ip\" : \"ip\", \"description\" : \"description\", \"internalip\" : \"internalip\" } ], \"costmodels\" : [ { \"costmodel\" : \"costmodel\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 6 }, { \"costmodel\" : \"costmodel\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 6 } ] } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /devices/{name} : Get one Device with the name
     *
     * @param name name of the Device (required)
     * @return Successful response (status code 200)
     * @see DevicesApi#devicesNameGet
     */
    default ResponseEntity<DeviceDTO> devicesNameGet(String name) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"capabilities\" : [ { \"capabilities\" : \"capabilities\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 0 }, { \"capabilities\" : \"capabilities\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 0 } ], \"ip\" : \"ip\", \"name\" : \"RPU1\", \"ports\" : [ { \"datastreamName\" : \"Flighstream\", \"number\" : 1, \"ip\" : \"ip\", \"description\" : \"description\", \"internalip\" : \"internalip\" }, { \"datastreamName\" : \"Flighstream\", \"number\" : 1, \"ip\" : \"ip\", \"description\" : \"description\", \"internalip\" : \"internalip\" } ], \"costmodels\" : [ { \"costmodel\" : \"costmodel\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 6 }, { \"costmodel\" : \"costmodel\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 6 } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /devices : Create a new Device
     *
     * @param deviceDTO  (required)
     * @return Successful response (status code 200)
     * @see DevicesApi#devicesPost
     */
    default ResponseEntity<DeviceDTO> devicesPost(DeviceDTO deviceDTO) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"capabilities\" : [ { \"capabilities\" : \"capabilities\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 0 }, { \"capabilities\" : \"capabilities\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 0 } ], \"ip\" : \"ip\", \"name\" : \"RPU1\", \"ports\" : [ { \"datastreamName\" : \"Flighstream\", \"number\" : 1, \"ip\" : \"ip\", \"description\" : \"description\", \"internalip\" : \"internalip\" }, { \"datastreamName\" : \"Flighstream\", \"number\" : 1, \"ip\" : \"ip\", \"description\" : \"description\", \"internalip\" : \"internalip\" } ], \"costmodels\" : [ { \"costmodel\" : \"costmodel\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 6 }, { \"costmodel\" : \"costmodel\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 6 } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /devices : Update Device
     *
     * @param deviceDTO  (required)
     * @return Successful response (status code 200)
     * @see DevicesApi#devicesPut
     */
    default ResponseEntity<DeviceDTO> devicesPut(DeviceDTO deviceDTO) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"capabilities\" : [ { \"capabilities\" : \"capabilities\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 0 }, { \"capabilities\" : \"capabilities\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 0 } ], \"ip\" : \"ip\", \"name\" : \"RPU1\", \"ports\" : [ { \"datastreamName\" : \"Flighstream\", \"number\" : 1, \"ip\" : \"ip\", \"description\" : \"description\", \"internalip\" : \"internalip\" }, { \"datastreamName\" : \"Flighstream\", \"number\" : 1, \"ip\" : \"ip\", \"description\" : \"description\", \"internalip\" : \"internalip\" } ], \"costmodels\" : [ { \"costmodel\" : \"costmodel\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 6 }, { \"costmodel\" : \"costmodel\", \"start\" : \"2000-01-23T04:56:07.000+00:00\", \"end\" : \"2000-01-23T04:56:07.000+00:00\", \"version\" : 6 } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
