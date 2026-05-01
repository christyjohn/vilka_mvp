package com.vilka.app.vendor.config.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import java.util.Base64;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthConverter jwtAuthConverter) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/actuator/health",
                                "/api/v1/vendors/test"
                        ).permitAll()
                        // logged in users can apply to be vendors
                        .requestMatchers("/api/v1/vendors/apply").authenticated()
                        .requestMatchers("/api/v1/vendors/*/approve").hasRole("ADMIN")
                        .requestMatchers("/api/v1/vendors/*/reject").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthConverter)
                        )
                )
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder(@Value("${JWT_SECRET}") String secret) {
        log.info("🔥 jwtDecoder -> JWT_SECRET in VENDOR = " + secret);
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        log.info("🔥 jwtDecoder -> SecretKey key in VENDOR = " + key);
        return NimbusJwtDecoder.withSecretKey(key).build();
    }
}
