package com.vilka.app.subscription.controller;

import com.vilka.app.subscription.common.config.security.SecurityUtils;
import com.vilka.app.subscription.dto.CreateSubscriptionRequest;
import com.vilka.app.subscription.dto.SubscriptionResponse;
import com.vilka.app.subscription.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService service;
    private static final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    // logged in user subscribing to an offering
    @PostMapping
    public SubscriptionResponse subscribe(@RequestBody CreateSubscriptionRequest request,
                                          JwtAuthenticationToken authentication) {
        Long userId = SecurityUtils.getCurrentUserId();
        log.info("🔥 Subscription CREATE HIT by User Id: " + userId);
        return service.subscribe(userId, request);
    }

    @GetMapping("/user/{userId}")
    public List<SubscriptionResponse> getByUser(@PathVariable Long userId) {
        log.info("🔥 GET Subscription by user id HIT by User Id: " + userId);
        return service.getByUser(userId);
    }

    @PutMapping("/{subId}/cancel")
    public SubscriptionResponse cancel(@PathVariable Long subId) {
        log.info("🔥 Subscription CANCEL HIT by Subscription Id: " + subId);
        return service.cancel(subId);
    }
}
