package com.vilka.app.subscription.dto;

import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class SubscriptionResponse {
    private Long id;
    private Long userId;
    private Long serviceId;
    private String status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
