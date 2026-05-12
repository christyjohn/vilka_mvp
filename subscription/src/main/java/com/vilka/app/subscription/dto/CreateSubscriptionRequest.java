package com.vilka.app.subscription.dto;

import lombok.Getter;

@Getter
public class CreateSubscriptionRequest {
    private Long userId;
    private Long offeringId;
}
