package blaze.security.login.infrastructure.security.provider;

import blaze.security.login.domain.user.dto.item.UserContext;
import blaze.security.login.infrastructure.security.token.RestAuthenticationToken;
import lombok.Builder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component(value = "restAuthenticationProvider")
public class RestAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Builder
    public RestAuthenticationProvider(UserDetailsService userDetailsService,
                                      PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserContext userContext = (UserContext) userDetailsService.loadUserByUsername(username);

        if (!(passwordEncoder.matches(password, userContext.getPassword()))) {
            throw new BadCredentialsException("Username or Password is not invalid");
        }

        return new RestAuthenticationToken(userContext.getUserItem(), null, userContext.getAuthorities());
    }

    /**
     * RestAuthenticationToken일때 provider 실행
     *
     * @param authentication 인증 객체
     * @return true: RestAuthenticationToken이 맞음, false: RestAuthenticationToken이 아님
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(RestAuthenticationToken.class);
    }
}
