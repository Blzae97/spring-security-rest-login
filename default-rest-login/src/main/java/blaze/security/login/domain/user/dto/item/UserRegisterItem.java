package blaze.security.login.domain.user.dto.item;

import blaze.security.login.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRegisterItem {
    private String username;
    private String password;

    @Builder
    public UserRegisterItem(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void encryptPassword(String pwd){
        this.password = pwd;
    }

    /**
     * 유저 엔티티 객체 생성
     *
     * @return User 엔티티 객체
     */
    public User toEntity(){
        return User.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
