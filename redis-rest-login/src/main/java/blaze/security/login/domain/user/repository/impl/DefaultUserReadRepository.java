package blaze.security.login.domain.user.repository.impl;

import blaze.security.login.domain.user.repository.impl.jpa.UserJpaRepository;
import blaze.security.login.domain.user.repository.inf.UserReadRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository(value = "userReadRepository")
public class DefaultUserReadRepository implements UserReadRepository {
    private final UserJpaRepository userJpaRepository;

    public DefaultUserReadRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    /**
     * username으로 회원 정보 조회
     *
     * @param username 회원 아이디
     * @param tClass   반환 타입
     * @return 회원정보
     */
    @Transactional(readOnly = true)
    @Override
    public <T> Optional<T> getUser(String username, Class<T> tClass) {
        return Optional.ofNullable(userJpaRepository.findByUserName(username, tClass));
    }

}
