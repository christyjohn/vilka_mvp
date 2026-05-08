package com.vilka.app.identity.auth.security.filter.internal;

public class InternalApiKeyFilter{

}
/*
@Component
@RequiredArgsConstructor
public class InternalApiKeyFilter extends OncePerRequestFilter {

    private final InternalProperties properties;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {


        String path = request.getRequestURI();

        // Only protect internal endpoints
        if (!path.startsWith("/api/v1/internal/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String apiKey = request.getHeader("X-Internal-Key");

        if (!apiKey.equals(properties.getVendorApiKey())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        filterChain.doFilter(request, response);
    }
}
*/
