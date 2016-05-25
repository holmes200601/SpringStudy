package restaurant.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/{boType}/*")
public class JsonController extends RestControllerBase {

    @Override
    public Object getInstance() {
        // TODO Auto-generated method stub
        return null;
    }

}
