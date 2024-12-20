package com.senla.api.dto.subscription;

import com.senla.api.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.UUID;

public class SubscriptionUpdateDto {
    private UUID subscriptionPlanId;
    private LocalDateTime expiresDate;

    public SubscriptionUpdateDto() {
    }

    public SubscriptionUpdateDto(UUID subscriptionPlanId, LocalDateTime expiresDate) {
        this.subscriptionPlanId = ValidationUtil.validateNotNull(subscriptionPlanId);
        this.expiresDate = ValidationUtil.validateNotNull(expiresDate);
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
