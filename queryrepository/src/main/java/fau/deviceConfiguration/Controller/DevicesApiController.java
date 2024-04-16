package fau.deviceConfiguration.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-05T02:01:40.864532344+01:00[Europe/Berlin]")
@Controller
@RequestMapping("${openapi.device.base-path:/api}")
public class DevicesApiController implements DevicesApi {

    private final DevicesApiDelegate delegate;

    public DevicesApiController(@Autowired(required = false) DevicesApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new DevicesApiDelegate() {});
    }

    @Override
    public DevicesApiDelegate getDelegate() {
        return delegate;
    }

}
