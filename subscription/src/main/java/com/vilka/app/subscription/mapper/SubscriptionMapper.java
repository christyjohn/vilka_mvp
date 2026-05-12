package com.vilka.app.subscription.mapper;

import com.vilka.app.subscription.dto.CreateSubscriptionRequest;
import com.vilka.app.subscription.dto.SubscriptionResponse;
import com.vilka.app.subscription.entity.Subscription;

public class SubscriptionMapper {
    public static Subscription toEntity(CreateSubscriptionRequest req) {
        Subscription s = new Subscription();
        s.setUserId(req.getUserId());
        s.setOfferingId(req.getOfferingId());
        return s;
    }

    public static SubscriptionResponse toResponse(Subscription s) {
        SubscriptionResponse res = new SubscriptionResponse();
        res.setId(s.getId());
        res.setUserId(s.getUserId());
        res.setOfferingId(s.getOfferingId());
        res.setStatus(s.getStatus().name());
        res.setStartDate(s.getStartDate());
        res.setEndDate(s.getEndDate());
        return res;
    }
}
