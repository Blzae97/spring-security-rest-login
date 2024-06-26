package blaze.security.login.domain.user.repository.impl;

import blaze.security.login.domain.user.repository.impl.jpa.UserAuthorityJpaRepository;
import blaze.security.login.domain.user.repository.inf.UserAuthorityReadRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository(value = "userReadAuthorityRepository")
public class DefaultUserAuthorityReadRepository implements UserAuthorityReadRepository {

    private final UserAuthorityJpaRepository userAuthorityJpaRepository;

    public DefaultUserAuthorityReadRepository(UserAuthorityJpaRepository userAuthorityJpaRepository) {
        this.userAuthorityJpaRepository = userAuthorityJpaRepository;
    }


    /**
     * <p>회원 권한 조회</p>
     *
     * @param userId 회원 고유 아이디
     * @param tClass 반환 타입
     * @return 회원 권한 리스트
     */
    @Transactional(readOnly = true)
    @Override
    public <T> List<T> getUserAuthorityList(Long userId, Class<T> tClass) {
        return userAuthorityJpaRepository.findByUserId(userId, tClass);
    }


}
