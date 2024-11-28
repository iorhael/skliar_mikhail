package com.senla.model;

import com.senla.util.ValidationUtil;

import java.math.BigDecimal;
import java.util.UUID;

public class SubscriptionPlan {
    private final UUID id;
    private final SubscriptionType name;
    private final BigDecimal pricePerMonth;

    SubscriptionPlan(Builder builder) {
        this.id = builder.id;
        this.name = ValidationUtil.validateNotNull(builder.name);
        this.pricePerMonth = validatePricePerMonth(builder.pricePerMonth);
    }

    public UUID getId() {
        return id;
    }

    public SubscriptionType getName() {
        return name;
    }

    public BigDecimal getPricePerMonth() {
        return pricePerMonth;
    }

    public static class Builder {
        private UUID id;
        private SubscriptionType name;
        private BigDecimal pricePerMonth;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(SubscriptionType name) {
            this.name = name;
            return this;
        }

        public Builder pricePerMonth(BigDecimal pricePerMonth) {
            this.pricePerMonth = pricePerMonth;
            return this;
        }

        public SubscriptionPlan build() {
            return new SubscriptionPlan(this);
        }
    }

    @Override
    public String toString() {
        return "SubscriptionPlan{" +
                "id=" + id +
                ", name=" + name +
                ", pricePerMonth=" + pricePerMonth +
                '}';
    }

   private BigDecimal validatePricePerMonth(BigDecimal pricePerMonth) {
        if (pricePerMonth == null || pricePerMonth.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price per month must be equal or greater than zero");
        }
        return pricePerMonth;
   }
}
