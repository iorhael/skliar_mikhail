package com.senla.api.model;

import com.senla.api.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.UUID;

public class Subscription {
    private UUID id;
    private UUID userId;
    private UUID subscriptionPlanId;
    private LocalDateTime startedDate;
    private LocalDateTime expiresDate;

    public Subscription() {
    }

    public Subscription(UUID userId, UUID subscriptionPlanId) {
        this.userId = ValidationUtil.validateNotNull(userId);
        this.subscriptionPlanId = ValidationUtil.validateNotNull(subscriptionPlanId);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDateTime getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(LocalDateTime startedDate) {
        this.startedDate = startedDate;
    }

    public LocalDateTime getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(LocalDateTime expiresDate) {
        this.expiresDate = expiresDate;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", userId=" + userId +
                ", subscriptionPlanId=" + subscriptionPlanId +
                ", startedDate=" + startedDate +
                ", expiresDate=" + expiresDate +
                '}';
    }
}
