package com.vilka.app.subscription.common.config;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.RequestContextFilter;

@Configuration
public class FeignClientConfig {

    private static final Logger log = LoggerFactory.getLogger(FeignClientConfig.class);

    @Bean
    public RequestInterceptor requestInterceptor() {
        log.info("🔥 Feign Interceptor Initialized");

        return requestTemplate -> {
            log.info("🔥 Interceptor triggered");

            ServletRequestAttributes attrs =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attrs == null) {
                log.warn("🔥 No request context found");
                return;
            }

            HttpServletRequest request = attrs.getRequest();

            String authHeader = request.getHeader("Authorization");

            log.info("🔥 Incoming Authorization header: " + authHeader);

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                requestTemplate.header("Authorization", authHeader);
            }
        };
    }

    @Bean
    public FilterRegistrationBean<RequestContextFilter> requestContextFilter() {
        return new FilterRegistrationBean<>(new RequestContextFilter());
    }
}
