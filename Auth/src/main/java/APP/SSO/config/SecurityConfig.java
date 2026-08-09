package APP.SSO.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Describes the line of guards every request passes through before it reaches
 * a controller.
 *
 * No formLogin here on purpose: /api/auth/login does the authenticating and
 * creates the session itself (see AuthController).
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/acmeConsole/",
                        "/acmeConsole/signIn",
                        "/acmeConsole/createAccount",
                        "/api/auth/**",
                        "/actuator/health",
                        "/h2-console/**")
                    .permitAll()
                .anyRequest().authenticated()
            ).csrf(csrf -> csrf.disable())
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            )

            .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}
