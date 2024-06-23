package blaze.security.login.presentation.user.controller;

import blaze.security.login.application.user.service.UserSignUpService;
import blaze.security.login.domain.user.dto.item.UserRegisterItem;
import blaze.security.login.domain.user.dto.request.UserSignUpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class UserSignUpController {
    private final UserSignUpService userSignUpService;

    public UserSignUpController(UserSignUpService userSignUpService) {
        this.userSignUpService = userSignUpService;
    }

    @PostMapping(value = "/sign-up")
    public Map<String, Object> userSignUp(@RequestBody UserSignUpRequest userSignUpRequest) {
        String username = userSignUpRequest.getUsername();
        String password = userSignUpRequest.getPassword();

        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            throw new IllegalArgumentException("Username or Password is invalid");
        }

        return userSignUpService.userSignUp(new UserRegisterItem(username, password));
    }
}
