package fau.datastreamMetric.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-04T18:33:35.572386121+01:00[Europe/Berlin]")
@Controller
@RequestMapping("${openapi.datastreamFeatureAPIModule.base-path:/api}")
public class DatastreamApiController implements DatastreamApi {

    private final DatastreamApiDelegate delegate;

    public DatastreamApiController(@Autowired(required = false) DatastreamApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new DatastreamApiDelegate() {});
    }

    @Override
    public DatastreamApiDelegate getDelegate() {
        return delegate;
    }

}
