package fau.dataMart.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T22:08:28.313274270+01:00[Europe/Berlin]")
@Controller
@RequestMapping("${openapi.query.base-path:/api}")
public class ExecutionplanApiController implements ExecutionplanApi {

    private final ExecutionplanApiDelegate delegate;

    public ExecutionplanApiController(@Autowired(required = false) ExecutionplanApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new ExecutionplanApiDelegate() {});
    }

    @Override
    public ExecutionplanApiDelegate getDelegate() {
        return delegate;
    }

}
