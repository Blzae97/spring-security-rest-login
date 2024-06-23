package blaze.security.login.domain.user.dto.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter
public class DefaultUserItem implements Serializable {
    private Long userId;
    private String username;
    private String password;

    @Builder
    public DefaultUserItem(Long userId,
                           String username,
                           String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public void hidePassword(){
        this.password = null;
    }
}
