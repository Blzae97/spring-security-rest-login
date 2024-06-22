package blaze.security.login.domain.user.service.impl;

import blaze.security.login.domain.user.enums.Authority;
import blaze.security.login.domain.user.repository.inf.UserAuthorityWriteRepository;
import blaze.security.login.domain.user.service.inf.UserAuthorityWriteService;
import org.springframework.stereotype.Service;

@Service(value = "userAuthorityWriteService")
public class DefaultUserAuthorityWriteService implements UserAuthorityWriteService {
    private final UserAuthorityWriteRepository userAuthorityWriteRepository;

    public DefaultUserAuthorityWriteService(UserAuthorityWriteRepository userAuthorityWriteRepository) {
        this.userAuthorityWriteRepository = userAuthorityWriteRepository;
    }

    /**
     * <p>권한 부여</p>
     *
     * @param userId    사용자 고유 아이디
     * @param authority 권한
     */
    @Override
    public void grantAuthority(Long userId, Authority authority) {
        userAuthorityWriteRepository.grantAuthority(userId, authority);
    }
}
