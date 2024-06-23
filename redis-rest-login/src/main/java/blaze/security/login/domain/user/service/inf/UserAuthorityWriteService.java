package blaze.security.login.domain.user.service.inf;

import blaze.security.login.domain.user.enums.Authority;

public interface UserAuthorityWriteService {
    /**
     * <p>권한 부여</p>
     *
     * @param userId    사용자 고유 아이디
     * @param authority 권한
     */
    void grantAuthority(Long userId, Authority authority);
}
