package blaze.security.login.infrastructure.security.config;

import blaze.security.login.infrastructure.security.entrypoint.RestAuthenticationEntryPoint;
import blaze.security.login.infrastructure.security.filter.RestAuthenticationFilter;
import blaze.security.login.infrastructure.security.handler.RestAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final AuthenticationProvider restAuthenticationProvider;
    private final AuthenticationSuccessHandler restSuccessHandler;
    private final AuthenticationFailureHandler restFailureHandler;

    public SecurityConfig(AuthenticationProvider restAuthenticationProvider,
                          AuthenticationSuccessHandler restSuccessHandler,
                          AuthenticationFailureHandler restFailureHandler) {
        this.restAuthenticationProvider = restAuthenticationProvider;
        this.restSuccessHandler = restSuccessHandler;
        this.restFailureHandler = restFailureHandler;
    }


    @Bean
    public SecurityFilterChain formSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Order(1)
    @Bean
    public SecurityFilterChain restSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/sign-in").permitAll()
                        .requestMatchers("/api/sign-up").permitAll()
                        .requestMatchers("/api/index").authenticated()
                        .requestMatchers("/api/write").hasRole("WRITE")
                        .anyRequest().authenticated()
                );

        AuthenticationManager authenticationManager = authenticationManager(http);

        http
                .addFilterBefore(restAuthenticationFilter(http, authenticationManager), UsernamePasswordAuthenticationFilter.class);

        http
                .authenticationManager(authenticationManager);

        http
                .exceptionHandling(exec -> exec
                        .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                        .accessDeniedHandler(new RestAccessDeniedHandler())
                );

        http
                .csrf(AbstractHttpConfigurer::disable);


        return http.build();
    }

    private AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder managerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        managerBuilder.authenticationProvider(restAuthenticationProvider);

        return managerBuilder.build();
    }

    private RestAuthenticationFilter restAuthenticationFilter(HttpSecurity http,
                                                              AuthenticationManager authenticationManager) {
        RestAuthenticationFilter restAuthenticationFilter = new RestAuthenticationFilter(http);
        restAuthenticationFilter.setAuthenticationManager(authenticationManager);
        restAuthenticationFilter.setAuthenticationSuccessHandler(restSuccessHandler);
        restAuthenticationFilter.setAuthenticationFailureHandler(restFailureHandler);

        return restAuthenticationFilter;
    }

}