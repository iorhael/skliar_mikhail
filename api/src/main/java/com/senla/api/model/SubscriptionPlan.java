package com.senla.api.model;

import com.senla.api.util.ValidationUtil;

import java.math.BigDecimal;
import java.util.UUID;

public class SubscriptionPlan {
    private UUID id;
    private SubscriptionType name;
    private BigDecimal pricePerMonth;

    public SubscriptionPlan() {
    }

    public SubscriptionPlan(SubscriptionType name, BigDecimal pricePerMonth) {
        this.name = ValidationUtil.validateNotNull(name);
        this.pricePerMonth = validatePricePerMonth(pricePerMonth);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SubscriptionType getName() {
        return name;
    }

    public void setName(SubscriptionType name) {
        this.name = name;
    }

    public BigDecimal getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(BigDecimal pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
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
