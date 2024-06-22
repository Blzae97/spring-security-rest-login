package blaze.security.login.domain.user.repository.impl;

import blaze.security.login.domain.user.dto.UserRegisterItem;
import blaze.security.login.domain.user.entity.User;
import blaze.security.login.domain.user.repository.impl.jpa.UserJpaRepository;
import blaze.security.login.domain.user.repository.inf.UserWriteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Repository(value = "userWriteRepository")
public class DefaultUserWriteRepository implements UserWriteRepository {
    private final UserJpaRepository userJpaRepository;

    public DefaultUserWriteRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    /**
     * User 엔티티 저장 후 인스턴스 반환
     *
     * @param item User 도메인 DTO
     */
    @Transactional
    @Override
    public <T> T userRegister(UserRegisterItem item, Class<T> tClass) {
        User userEntity = item.toEntity();
        User savedUserEntity = userJpaRepository.save(userEntity);

        return instantiateWithUser(savedUserEntity, tClass);
    }

    /**
     * User 엔티티를 T 클래스로 인스턴스화 하여 반횐
     * User 관련 DTO가 들어와도 변환할 수 있게 제너릭을 사용
     *
     * @param u      회원 엔티티
     * @param tClass 변환 하고 싶은 클래스
     * @return tClass 구현체
     */
    private <T> T instantiateWithUser(User u, Class<T> tClass) {
        try {
            Constructor<T> constructor = tClass.getDeclaredConstructor(User.class);
            return constructor.newInstance(u);

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
