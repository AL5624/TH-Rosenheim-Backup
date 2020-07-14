package de.thro.inf.pvs;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {
    @RequestMapping(method = RequestMethod.GET, value = "/greeting")
    public Map<String, String> greetings(Principal p) {
        return Collections.singletonMap("content", "Hello " + p.getName());
    }
}
