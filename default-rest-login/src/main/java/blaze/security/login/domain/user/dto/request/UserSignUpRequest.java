package blaze.security.login.domain.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSignUpRequest {
    private String username;
    private String password;

    @Builder
    public UserSignUpRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
