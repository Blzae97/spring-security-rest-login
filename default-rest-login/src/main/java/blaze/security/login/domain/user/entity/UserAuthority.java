package blaze.security.login.domain.user.entity;

import blaze.security.login.domain.user.converter.AuthorityConverter;
import blaze.security.login.domain.user.enums.Authority;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity(name = "user_authority")
@EntityListeners(value = AuditingEntityListener.class)
public class UserAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11)")
    @Comment(value = "고유 아이디")
    private Long id;

    @Column(name = "user_id", columnDefinition = "int(11)")
    @Comment(value = "회원 고유 아이디")
    private Long userId;

    @Convert(converter = AuthorityConverter.class)
    @Column(name = "authority", columnDefinition = "int(3)")
    @Comment(value = "회원 권한")
    private Authority authority;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "timestamp(3)")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "timestamp(3)")
    private LocalDateTime updatedAt;

    @Builder
    public UserAuthority(Long id,
                         Long userId,
                         Authority authority,
                         LocalDateTime createdAt,
                         LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.authority = authority;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
