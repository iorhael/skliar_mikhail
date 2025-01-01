package com.senla.dto.subscription;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class SubscriptionUpdateDto {
    @NotNull
    private UUID subscriptionPlanId;

    @NotNull
    @FutureOrPresent
    private LocalDateTime expiresDate;

    public SubscriptionUpdateDto() {
    }

    public SubscriptionUpdateDto(UUID subscriptionPlanId, LocalDateTime expiresDate) {
        this.subscriptionPlanId = subscriptionPlanId;
        this.expiresDate = expiresDate;
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
