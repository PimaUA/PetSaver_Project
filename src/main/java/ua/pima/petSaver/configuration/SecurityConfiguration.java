package ua.pima.petSaver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE)
                                .hasRole("OWNER")
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/register").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin((form) -> form.loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true").permitAll())

                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}
