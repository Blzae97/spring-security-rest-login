package blaze.security.login.domain.user.dto;

import blaze.security.login.domain.user.entity.UserAuthority;
import blaze.security.login.domain.user.enums.Authority;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class UserAuthorityItem {
    private Long id;
    private Long userId;
    private Authority authority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public UserAuthorityItem(Long id, Long userId, Authority authority, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.authority = authority;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserAuthorityItem(UserAuthority ua) {
        this.id = ua.getId();
        this.userId = ua.getUserId();
        this.authority = ua.getAuthority();
        this.createdAt = ua.getCreatedAt();
        this.updatedAt = ua.getUpdatedAt();
    }

}
