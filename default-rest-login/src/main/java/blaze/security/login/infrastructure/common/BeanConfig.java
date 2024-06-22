package blaze.security.login.infrastructure.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeanConfig {

    /**
     * SCrypt 암호화 방식 적용
     *
     * @return PasswordEncoder 객체
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoderMap = new HashMap<>();
        String encodingId = "scrypt@SpringSecurity_v5_8";
        encoderMap.put(encodingId, SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8()); // SCrypt 암호화 방식 선택
        return new DelegatingPasswordEncoder(encodingId, encoderMap);
    }
}
