package com.liceu.sistem_evidenta_elevi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

/**
 * Clasa de configurare pentru securitatea aplicatiei.
 * Aceasta configureaza setarile pentru autentificare, permisiuni de acces si CORS.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configureaza lantul de filtre de securitate.
     *
     * @param http obiectul {@link HttpSecurity} pentru configurarea securitatii.
     * @return instanta configurata de {@link SecurityFilterChain}.
     * @throws Exception in cazul unei erori de configurare.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->  auth.anyRequest().permitAll()
//                        .requestMatchers("/liceu/autentificare/**").permitAll() // endpoint public
//                        .anyRequest().authenticated()  // necesita autentificare
                )
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(formLogin -> formLogin.disable());

        return http.build();
    }

    /**
     * Configureaza CORS (Cross-Origin Resource Sharing) pentru accesul frontend.
     *
     * @return instanta configurata de {@link CorsConfigurationSource}.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:5173"); // permite accesul de la frontend
        configuration.setAllowedOriginPatterns(Collections.singletonList("http://localhost:*")); //acces pemis de la toate porturile localhost
        configuration.addAllowedMethod("*");// permite toate metodele HTTP
        configuration.addAllowedHeader("*");// permite toate antetele HTTP

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);// aplica configuratia pentru toate rutele.
        return source;
    }
    
    /**
     * Defineste un bean pentru criptarea parolelor folosind BCrypt.
     *
     * @return instanta de {@link PasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Configureaza managerul de autentificare pentru aplicatie.
     *
     * @param authConfig configuratia de autentificare {@link AuthenticationConfiguration}.
     * @return instanta de {@link AuthenticationManager}.
     * @throws Exception in cazul unei erori de configurare.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}

