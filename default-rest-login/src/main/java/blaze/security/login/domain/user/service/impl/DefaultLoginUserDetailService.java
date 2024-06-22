package blaze.security.login.domain.user.service.impl;

import blaze.security.login.domain.user.dto.item.DefaultUserItem;
import blaze.security.login.domain.user.dto.item.UserAuthorityItem;
import blaze.security.login.domain.user.dto.item.UserContext;
import blaze.security.login.domain.user.dto.item.UserItem;
import blaze.security.login.domain.user.repository.inf.UserAuthorityReadRepository;
import blaze.security.login.domain.user.repository.inf.UserReadRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userDetailsService")
public class DefaultLoginUserDetailService implements UserDetailsService {
    private final UserReadRepository urr;
    private final UserAuthorityReadRepository uarr;

    public DefaultLoginUserDetailService(UserReadRepository urr,
                                         UserAuthorityReadRepository uarr) {
        this.urr = urr;
        this.uarr = uarr;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserItem userItem = urr.getUser(username, UserItem.class)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("%s으로 조회 된 회원이 없습니다.", username)));

        DefaultUserItem defaultUserItem = userItem.toDefaultUserItem();
        List<UserAuthorityItem> userAuthorityList = uarr.getUserAuthorityList(
                userItem.getId(),
                UserAuthorityItem.class
        );

        return new UserContext(defaultUserItem, userAuthorityList);
    }
}
