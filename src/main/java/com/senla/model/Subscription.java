package com.senla.model;

import com.senla.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.UUID;

public class Subscription {
    private final UUID id;
    private final UUID userId;
    private final UUID subscriptionPlanId;
    private final LocalDateTime startedDate;
    private final LocalDateTime expiresDate;

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getSubscriptionPlanId() {
        return subscriptionPlanId;
    }

    public LocalDateTime getStartedDate() {
        return startedDate;
    }

    public LocalDateTime getExpiresDate() {
        return expiresDate;
    }

    Subscription(Builder builder) {
        this.id = builder.id;
        this.userId = ValidationUtil.validateNotNull(builder.userId);
        this.subscriptionPlanId = ValidationUtil.validateNotNull(builder.subscriptionPlanId);
        this.startedDate = builder.startedDate;
        this.expiresDate = builder.expiresDate;
    }

    public static class Builder {
        private UUID id;
        private UUID userId;
        private UUID subscriptionPlanId;
        private LocalDateTime startedDate;
        private LocalDateTime expiresDate;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder userId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder subscriptionPlanId(UUID subscriptionPlanId) {
            this.subscriptionPlanId = subscriptionPlanId;
            return this;
        }

        public Builder startedDate(LocalDateTime startedDate) {
            this.startedDate = startedDate;
            return this;
        }

        public Builder expiresDate(LocalDateTime expiresDate) {
            this.expiresDate = expiresDate;
            return this;
        }

        public Subscription build() {
            return new Subscription(this);
        }
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
