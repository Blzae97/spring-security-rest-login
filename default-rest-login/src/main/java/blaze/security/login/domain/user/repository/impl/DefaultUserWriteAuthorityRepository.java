package blaze.security.login.domain.user.repository.impl;

import blaze.security.login.domain.user.repository.impl.jpa.UserAuthorityJpaRepository;
import blaze.security.login.domain.user.repository.inf.UserAuthorityWriteRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "userAuthorityWriteRepository")
public class DefaultUserWriteAuthorityRepository implements UserAuthorityWriteRepository {

    private final UserAuthorityJpaRepository userAuthorityJpaRepository;

    public DefaultUserWriteAuthorityRepository(UserAuthorityJpaRepository userAuthorityJpaRepository) {
        this.userAuthorityJpaRepository = userAuthorityJpaRepository;
    }

}
