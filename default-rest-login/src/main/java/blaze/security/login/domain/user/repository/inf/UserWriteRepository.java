package blaze.security.login.domain.user.repository.inf;

import blaze.security.login.domain.user.dto.item.UserRegisterItem;

public interface UserWriteRepository {
    /**
     * <p>회원 엔티티 저장</p>
     *
     * @param item   회원 정보
     * @param tClass 반환 타입
     * @return T 타입의 회원 정보
     */
    <T> T userRegister(UserRegisterItem item, Class<T> tClass); // 유저 엔티티 저장
}
