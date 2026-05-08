package com.vilka.app.identity.auth.security.jwt;

import com.vilka.app.identity.user.entity.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
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
    // TOKEN GENERATION
    // =========================
    public String generateToken(Long userId,
                                String username,
                                List<Role> roles,
                                List<String> permissions) {

        Date now = new Date();
        //Date expiry = new Date(now.getTime() + 1000 * 60 * 60);
        Date expiry = new Date(System.currentTimeMillis() + 3600000);

        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("username", username)
                .claim("roles", roles.stream()
                        .map(Role::name)
                        .toList())
                .claim("permissions", permissions)
                .issuer("identity-service")
                .issuedAt(now)
                .expiration(expiry)
                .signWith(key)
                .compact();
    }
}