package blaze.security.login.presentation.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class IndexController {

    @GetMapping(value = "/index")
    public Authentication index(Authentication authentication) {
        return authentication;
    }

    @GetMapping(value = "/write")
    public Authentication write(Authentication authentication) {
        return authentication;
    }
}
