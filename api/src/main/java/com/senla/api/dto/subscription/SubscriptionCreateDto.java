package com.senla.api.dto.subscription;

import com.senla.api.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.UUID;

public class SubscriptionCreateDto {
    private UUID userId;
    private UUID subscriptionPlanId;
    private LocalDateTime expiresDate;

    public SubscriptionCreateDto(UUID userId, UUID subscriptionPlanId, LocalDateTime expiresDate) {
        this.userId = ValidationUtil.validateNotNull(userId);
        this.subscriptionPlanId = ValidationUtil.validateNotNull(subscriptionPlanId);
        this.expiresDate = ValidationUtil.validateNotNull(expiresDate);
    }

    public SubscriptionCreateDto() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getSubscriptionPlanId() {
        return subscriptionPlanId;
    }

    public void setSubscriptionPlanId(UUID subscriptionPlanId) {
        this.subscriptionPlanId = subscriptionPlanId;
    }

    public LocalDateTime getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(LocalDateTime expiresDate) {
        this.expiresDate = expiresDate;
    }
}
