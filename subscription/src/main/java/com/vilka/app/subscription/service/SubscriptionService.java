package com.vilka.app.subscription.service;

import com.vilka.app.subscription.client.MarketplaceClient;
import com.vilka.app.subscription.client.UserClient;
import com.vilka.app.subscription.common.exception.ApiException;
import com.vilka.app.subscription.common.exception.ErrorCode;
import com.vilka.app.subscription.dto.CreateSubscriptionRequest;
import com.vilka.app.subscription.dto.SubscriptionResponse;
import com.vilka.app.subscription.entity.Subscription;
import com.vilka.app.subscription.mapper.SubscriptionMapper;
import com.vilka.app.subscription.repository.SubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository repository;
    private final UserClient userClient;
    private final MarketplaceClient marketplaceClient;
    private static final Logger log = LoggerFactory.getLogger(SubscriptionService.class);

    public SubscriptionService(SubscriptionRepository repository,
                               UserClient userClient,
                               MarketplaceClient catalogClient) {
        this.repository = repository;
        this.userClient = userClient;
        this.marketplaceClient = catalogClient;
    }

    public SubscriptionResponse subscribe(Long userID, CreateSubscriptionRequest request) {

        log.info("🔥 Creating subscription with user id={}", request.getUserId());
        // 1. Validate user
        boolean userExists = userClient.exists(request.getUserId()).isExists();
        if (!userExists) {
            log.warn("🔥 User creation failed: user doesn't existexists: {}", request.getUserId());
            throw new ApiException(ErrorCode.USER_NOT_FOUND);
        }

        // 2. Validate service
        try {
            marketplaceClient.getOffering(request.getOfferingId());
        } catch (Exception ex) {
            throw new ApiException(ErrorCode.OFFERING_NOT_FOUND);
        }

        // 3. Prevent duplicate subscription
        repository.findByUserIdAndOfferingId(
                request.getUserId(),
                request.getOfferingId()
        ).ifPresent(s -> {
            throw new ApiException(ErrorCode.ALREADY_SUBSCRIBED);
        });

        // 4. Create
        Subscription sub = SubscriptionMapper.toEntity(request);
        Subscription saved = repository.save(sub);

        log.info("🔥 Subscrption created successfully with id={}", saved.getId());

        return SubscriptionMapper.toResponse(saved);
    }

    public List<SubscriptionResponse> getByUser(Long userId) {
        log.info("Listing all subscriptions for user with id={}", userId);
        return repository.findByUserId(userId)
                .stream()
                .map(SubscriptionMapper::toResponse)
                .toList();
    }

    public SubscriptionResponse cancel(Long id) {
        log.debug("Cancelling subscription with id={}", id);
        Subscription sub = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Subscription not found with id={}", id);
                    return new ApiException(ErrorCode.NOT_SUBSCRIBED);
                });

        sub.setStatus(Subscription.Status.CANCELLED);
        sub.setEndDate(java.time.LocalDateTime.now());

        return SubscriptionMapper.toResponse(repository.save(sub));
    }
}
