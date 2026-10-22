package be.iccbxl.pid.reservationsspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * Configuration de Spring Security : chiffrement des mots de passe et règles d'accès.
 *
 * @author Julio Ramos do Carmo
 */
@Configuration
public class SpringSecurityConfig {

    // Les mots de passe sont chiffrés et vérifiés avec BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCryptVersion.$2Y, 12);
    }

    // Définit quelles adresses sont accessibles et à qui, et utilise notre propre page de connexion
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> { auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/login", "/login**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/user").hasRole("MEMBER")
                        .anyRequest().authenticated();
                })
                .formLogin((form) -> form
                        .loginPage("/login")
                        .usernameParameter("login")
                        .failureUrl("/login?loginError=true")
                        .permitAll())
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login?logoutSuccess=true")
                        .permitAll())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(
                                new LoginUrlAuthenticationEntryPoint("/login?loginRequired=true")))
                .build();
    }
}