package blaze.security.login.domain.user.dto;

import blaze.security.login.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class UserItem {
    private Long id;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public UserItem(Long id,
                    String username,
                    String password,
                    LocalDateTime createdAt,
                    LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserItem(User u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.password = u.getPassword();
        this.createdAt = u.getCreatedAt();
        this.updatedAt = u.getUpdatedAt();
    }

    public DefaultUserItem toDefaultUserItem() {
        return DefaultUserItem.builder()
                .userId(this.id)
                .username(this.username)
                .password(this.password)
                .build();
    }
}
