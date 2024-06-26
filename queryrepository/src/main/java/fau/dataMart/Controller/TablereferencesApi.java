/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.0.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package fau.dataMart.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T22:08:28.313274270+01:00[Europe/Berlin]")
@Validated
@Tag(name = "query", description = "the query API")
public interface TablereferencesApi {

    default TablereferencesApiDelegate getDelegate() {
        return new TablereferencesApiDelegate() {};
    }

    /**
     * GET /tablereferences : Retrieve all table references
     * Retrieve all table references
     *
     * @return Successful response (status code 200)
     */
    @Operation(
        operationId = "tablereferencesGet",
        summary = "Retrieve all table references",
        description = "Retrieve all table references",
        tags = { "query" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/tablereferences",
        produces = { "application/json" }
    )
    default ResponseEntity<List<String>> tablereferencesGet(
        
    ) {
        return getDelegate().tablereferencesGet();
    }

}
