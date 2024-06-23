package blaze.security.login.domain.user.repository.inf;

import blaze.security.login.domain.user.enums.Authority;

public interface UserAuthorityWriteRepository {
    /**
     * <p>권한 부여</p>
     *
     * @param userId    사용자 고유 아이디
     * @param authority 권한
     */
    void grantAuthority(Long userId, Authority authority);
}
