package com.vilka.app.catalog.common.config;

import com.vilka.app.catalog.common.exception.ApiException;
import com.vilka.app.catalog.common.exception.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class SecurityUtils {

    private SecurityUtils() {}

    public static Long getCurrentUserId() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken jwtAuth) {

            return Long.parseLong(
                    jwtAuth.getToken().getSubject()
            );
        }

        throw new ApiException(ErrorCode.INVALID_CREDENTIALS);
    }
}
