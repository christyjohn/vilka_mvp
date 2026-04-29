package com.vilka.app.subscription.controller;

import com.vilka.app.subscription.dto.CreateSubscriptionRequest;
import com.vilka.app.subscription.dto.SubscriptionResponse;
import com.vilka.app.subscription.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @PostMapping
    public SubscriptionResponse create(@RequestBody CreateSubscriptionRequest request) {
        return service.create(request);
    }

        @GetMapping("/user/{userId}")
    public List<SubscriptionResponse> getByUser(@PathVariable Long userId) {
        return service.getByUser(userId);
    }

    @PutMapping("/{id}/cancel")
    public SubscriptionResponse cancel(@PathVariable Long id) {
        return service.cancel(id);
    }
}
