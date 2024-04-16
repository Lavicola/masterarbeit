package fau.dataMart.Controller;

import fau.dataMart.dto.ExecutionplanStep;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link ExecutionplanApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T22:08:28.313274270+01:00[Europe/Berlin]")
public interface ExecutionplanApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /executionplan/{hash}/steps : Get Executionplan and Steps
     *
     * @param hash Get steps of Executionplan (required)
     * @return Successful response (status code 200)
     * @see ExecutionplanApi#executionplanHashStepsGet
     */
    default ResponseEntity<List<ExecutionplanStep>> executionplanHashStepsGet(String hash) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"costmodel\" : \"{}\", \"capabilities\" : \"{}\", \"executionstep\" : \"{}\", \"step\" : 1, \"deviceName\" : \"fpga1\" }, { \"costmodel\" : \"{}\", \"capabilities\" : \"{}\", \"executionstep\" : \"{}\", \"step\" : 1, \"deviceName\" : \"fpga1\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
