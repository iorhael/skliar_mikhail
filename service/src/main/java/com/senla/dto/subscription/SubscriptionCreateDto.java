package com.senla.dto.subscription;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class SubscriptionCreateDto {
    @NotNull
    private UUID userId;

    @NotNull
    private UUID subscriptionPlanId;

    @NotNull
    @FutureOrPresent
    private LocalDateTime expiresDate;

    public SubscriptionCreateDto(UUID userId, UUID subscriptionPlanId, LocalDateTime expiresDate) {
        this.userId = userId;
        this.subscriptionPlanId = subscriptionPlanId;
        this.expiresDate = expiresDate;
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
