package com.vilka.app.identity.auth.security.filter.internal;

import com.vilka.app.identity.auth.security.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("🔥 JWT FILTER HIT");
        String header = request.getHeader("Authorization");

        try {
            if (header != null && header.startsWith("Bearer ")) {

                String token = header.substring(7);

                if (jwtUtil.validateToken(token)) {
                    System.out.println("🔥 TOKEN VALID");

                    String userId = jwtUtil.extractUserId(token);

                    List<GrantedAuthority> authorities =
                            jwtUtil.extractAuthorities(token);

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    userId,
                                    null,
                                    authorities
                            );

                    auth.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(auth);
                    System.out.println("🔥 AUTH SET SUCCESS: " + SecurityContextHolder.getContext().getAuthentication());
                } else {
                    System.out.println("❌ TOKEN INVALID");
                }
            }
        } catch (Exception e) {
            System.out.println("❌ TOKEN EXCEPTION");
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }
}
