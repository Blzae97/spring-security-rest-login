package blaze.security.login.infrastructure.security.filter;

import blaze.security.login.domain.user.dto.UserSignInRequest;
import blaze.security.login.infrastructure.security.token.RestAuthenticationToken;
import blaze.security.login.infrastructure.security.util.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class RestAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * /api/login 이면서 post일 때만 해당 필터가 동작하게
     */
    public RestAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/sign-in", HttpMethod.POST.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (!request.getMethod().equals(HttpMethod.POST.name()) || !WebUtil.isAjax(request)) {
            throw new IllegalArgumentException("Authentication method is not supported");
        }

        if (!WebUtil.isContentTypeJson(request)) {
            throw new IllegalArgumentException("Authentication Content-type is not supported");
        }

        UserSignInRequest userSignInRequest = objectMapper.readValue(request.getReader(), UserSignInRequest.class);
        String username = userSignInRequest.getUsername();
        String password = userSignInRequest.getPassword();
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            throw new AuthenticationServiceException("Username or Password not provided");
        }

        return super.getAuthenticationManager().authenticate(new RestAuthenticationToken(username, password));
    }
}
