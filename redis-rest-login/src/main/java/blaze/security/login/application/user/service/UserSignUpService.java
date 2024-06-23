package blaze.security.login.application.user.service;

import blaze.security.login.domain.user.dto.item.UserItem;
import blaze.security.login.domain.user.dto.item.UserRegisterItem;
import blaze.security.login.domain.user.enums.Authority;
import blaze.security.login.domain.user.service.inf.UserAuthorityWriteService;
import blaze.security.login.domain.user.service.inf.UserWriteService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserSignUpService {
    private final UserWriteService userWriteService;
    private final UserAuthorityWriteService userAuthorityWriteService;

    private final Authority DEFAULT_AUTHORITY = Authority.ROLE_READ;

    public UserSignUpService(UserWriteService userWriteService,
                             UserAuthorityWriteService userAuthorityWriteService) {
        this.userWriteService = userWriteService;
        this.userAuthorityWriteService = userAuthorityWriteService;
    }


    public Map<String, Object> userSignUp(UserRegisterItem item) {
        UserItem userItem = userWriteService.userRegister(item, UserItem.class);
        userAuthorityWriteService.grantAuthority(userItem.getId(), DEFAULT_AUTHORITY);

        return Map.ofEntries(
                Map.entry("username", userItem.getUsername()),
                Map.entry("authority", DEFAULT_AUTHORITY)
        );
    }
}
