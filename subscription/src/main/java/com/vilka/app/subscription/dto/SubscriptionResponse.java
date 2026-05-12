package com.vilka.app.subscription.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionResponse {
    private Long id;
    private Long userId;
    private Long offeringId;
    private String status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
