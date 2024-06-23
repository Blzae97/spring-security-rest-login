package blaze.security.login.domain.user.repository.inf;

import java.util.Optional;

public interface UserReadRepository {

    /**
     * <p>회원은 username으로 회원 정보 조회</p>
     *
     * @param username 회원 이메일
     * @param tClass   반환 타입
     * @return T 타입의 회원 정보
     */
    <T> Optional<T> getUser(String username, Class<T> tClass); // username으로 회원조회
}
