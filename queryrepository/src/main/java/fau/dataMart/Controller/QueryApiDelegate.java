package fau.dataMart.Controller;

import java.time.LocalDateTime;
import fau.dataMart.dto.QueryDTO;
import fau.dataMart.dto.QueryDTOPage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link QueryApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T22:08:28.313274270+01:00[Europe/Berlin]")
public interface QueryApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /query : Get queries
     *
     * @param startDate The start Date of the Queries to search for (optional, default to 2020-11-01T00:04:12.462465539)
     * @param endDate The end Date of the Queries to search for (optional, default to #{T(java.time.LocalDateTime).now()})
     * @param page The page number (zero-based index) for paginated results. (optional, default to 0)
     * @param size The number of items per page. (optional, default to 40)
     * @param sort Sorting criteria for the results. (optional, default to desc)
     * @param tablenames List of Tablenames a Query shall own (optional)
     * @return Successful response (status code 200)
     * @see QueryApi#queryGet
     */
    default ResponseEntity<QueryDTOPage> queryGet(LocalDateTime startDate,
        LocalDateTime endDate,
        Integer page,
        Integer size,
        String sort,
        List<String> tablenames) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"totalPages\" : 0, \"queries\" : [ { \"statementHash\" : \"HASH(SELECT * FROM)\", \"executionplanHash\" : \"HASH(costplan)\", \"statement\" : \"SELECT * FROM\", \"timestamp\" : 31536000 }, { \"statementHash\" : \"HASH(SELECT * FROM)\", \"executionplanHash\" : \"HASH(costplan)\", \"statement\" : \"SELECT * FROM\", \"timestamp\" : 31536000 } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /query : redirect a new resource to  AMQP
     *
     * @param queryDTO  (required)
     * @return Successful Creation (status code 200)
     * @see QueryApi#queryPost
     */
    default ResponseEntity<QueryDTO> queryPost(QueryDTO queryDTO) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"statementHash\" : \"HASH(SELECT * FROM)\", \"executionplanHash\" : \"HASH(costplan)\", \"statement\" : \"SELECT * FROM\", \"timestamp\" : 31536000 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
