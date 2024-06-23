package blaze.security.login.domain.user.repository.impl.jpa;

import blaze.security.login.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    @Query(value =
            "SELECT u " +
                    "FROM User u " +
                    "WHERE u.username = :username")
    <T> T findByUserName(String username, Class<T> tClass);
}
