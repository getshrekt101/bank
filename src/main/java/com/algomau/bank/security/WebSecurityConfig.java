package com.algomau.bank.security;

import com.algomau.bank.domain.UserAccount;
import com.algomau.bank.service.UserAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserAccountService userAccountService;

    public WebSecurityConfig(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions().disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public access
                        .requestMatchers("/h2-console/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/**").permitAll()

                        // Admin-only endpoints
                        .requestMatchers("/useraccounts/**").hasRole("ADMIN")

                        // DELETE - only Admins can delete
                        .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")

                        // TELLER access
                        .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("TELLER", "ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/users/**").hasAnyRole("TELLER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/users/**").hasAnyRole("TELLER", "ADMIN", "USER")

                        .requestMatchers(HttpMethod.GET, "/accounts/**", "/transactions/**").hasAnyRole("TELLER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/accounts/**", "/transactions/**").hasAnyRole("TELLER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/accounts/**", "/transactions/**").hasAnyRole("TELLER", "ADMIN")

                        // USER permissions
                        .requestMatchers(HttpMethod.GET, "/users/**", "/accounts/**", "/transactions/**").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/users/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/transactions/**").hasRole("USER")

                        // All others must be authenticated
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserAccount account = userAccountService.findByUserName(username);

            if (account == null) {
                throw new UsernameNotFoundException("User not found: " + username);
            }

            return User.builder()
                    .username(account.getUserName())
                    .password(account.getPassword())
                    .authorities(account.getRole().name())
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
