package blaze.security.login.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSignInRequest {
    private String username;
    private String password;

    @Builder
    public UserSignInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
