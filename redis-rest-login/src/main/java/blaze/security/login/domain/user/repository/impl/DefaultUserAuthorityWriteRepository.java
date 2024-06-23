package blaze.security.login.domain.user.repository.impl;

import blaze.security.login.domain.user.entity.UserAuthority;
import blaze.security.login.domain.user.enums.Authority;
import blaze.security.login.domain.user.repository.impl.jpa.UserAuthorityJpaRepository;
import blaze.security.login.domain.user.repository.inf.UserAuthorityWriteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "userAuthorityWriteRepository")
public class DefaultUserAuthorityWriteRepository implements UserAuthorityWriteRepository {

    private final UserAuthorityJpaRepository userAuthorityJpaRepository;

    public DefaultUserAuthorityWriteRepository(UserAuthorityJpaRepository userAuthorityJpaRepository) {
        this.userAuthorityJpaRepository = userAuthorityJpaRepository;
    }

    /**
     * <p>회원 권한 저장</p>
     *
     * @param userId    회원 고유 아이디
     * @param authority 권한 타입
     */
    @Transactional
    @Override
    public void grantAuthority(Long userId, Authority authority) {
        userAuthorityJpaRepository.save(UserAuthority.builder()
                .userId(userId)
                .authority(authority)
                .build()
        );
    }
}
