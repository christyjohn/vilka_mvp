package com.vilka.app.vendor.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;

    private SecretKey key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // =========================
    // TOKEN VALIDATION
    // =========================
    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);

            return "identity-service".equals(claims.getIssuer())
                    && claims.getExpiration().after(new Date());

        } catch (Exception ex) {
            return false;
        }
    }

    // =========================
    // CLAIM EXTRACTION
    // =========================
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build().
                parseSignedClaims(token).
                getPayload();
    }

    public String extractUserId(String token) {
        return getClaims(token).getSubject();
    }

    public String extractUsername(String token) {
        return getClaims(token).get("username", String.class);
    }

    public List<String> extractRoles(String token) {
        return getClaims(token).get("roles", List.class);
    }

    public List<String> extractPermissions(String token) {
        return getClaims(token).get("permissions", List.class);
    }

    // =========================
    // AUTHORITIES (IMPORTANT)
    // =========================
    public List<GrantedAuthority> extractAuthorities(String token) {
        Claims claims = getClaims(token);

        List<GrantedAuthority> authorities = new ArrayList<>();

        List<String> roles = claims.get("roles", List.class);
        if (roles != null) {
            roles.forEach(role ->
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role))
            );
        }

        List<String> permissions = claims.get("permissions", List.class);
        if (permissions != null) {
            permissions.forEach(p ->
                    authorities.add(new SimpleGrantedAuthority(p))
            );
        }

        return authorities;
    }
}