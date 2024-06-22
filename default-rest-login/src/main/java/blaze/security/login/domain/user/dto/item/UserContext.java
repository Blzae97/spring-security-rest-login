package blaze.security.login.domain.user.dto.item;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserContext implements UserDetails {
    private final DefaultUserItem userItem;
    private final List<GrantedAuthority> authorityList;

    @Builder
    public UserContext(DefaultUserItem userItem,
                       List<UserAuthorityItem> authorityList) {
        this.userItem = userItem;
        this.authorityList = authorityList.stream()
                .map(a -> new SimpleGrantedAuthority(a.getAuthority().name()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    public DefaultUserItem getUserItem() {
        this.userItem.hidePassword();
        return userItem;
    }

    @Override
    public String getPassword() {
        return userItem.getPassword();
    }

    @Override
    public String getUsername() {
        return userItem.getUsername();
    }

    /**
     * <p>회원 계정이 만료되지 않았는지 확인합니다. 계정이 만료된 경우 시스템에 로그인할 수 없습니다.</p>
     * <p>계정의 유효 기간을 확인하여 결과를 반환합니다.</p>
     *
     * @return true: 계정이 만료되지 않음. false: 계정이 만료됨.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * <p>회원의 계정이 잠겨있는지 확인합니다. 계정이 잠겨 있다면 회원은 시스템에 로그인할 수 없습니다.</p>
     * <p>계정이 관리자에 의해 잠겨 있거나, 여러 번의 실패한 로그인 시도로 인해 잠긴 경우에 사용합니다.</p>
     *
     * @return true: 계정이 잠기지 않음. false: 계정이 잠김.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * <p>회원의 자격 증명이 만료되지 않았는지 확인합니다. 자격 증명이 만료된 경우 회원은 로그인할 수 없습니다.</p>
     * <p>자격 증명 유효 기간을 확인하여, 만료된 자격 증명을 가진 회원이 로그인하지 못하게 합니다.</p>
     *
     * @return true: 자격 증명이 만료되지 않음. false: 자격 증명이 만료됨
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * <p>회원의 계정이 활성화되어 있는지 확인합니다. 비활성화된 계정은 로그인할 수 없습니다.</p>
     * <p>회원의 계정 활성화 상태를 획인하여, 비활성화된 계정에서 로그인하지 못하게 합니다.</p>
     *
     * @return true: 계정이 활성화됨. false: 계정이 비활성화됨.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
