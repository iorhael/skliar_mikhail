package com.senla.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class Subscription {
    private UUID id;

    @NotNull
    private UUID userId;

    @NotNull
    private UUID subscriptionPlanId;

    private LocalDateTime startedDate;

    @FutureOrPresent
    private LocalDateTime expiresDate;

    public Subscription() {
    }

    public Subscription(UUID userId, UUID subscriptionPlanId) {
        this.userId = userId;
        this.subscriptionPlanId = subscriptionPlanId;
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
