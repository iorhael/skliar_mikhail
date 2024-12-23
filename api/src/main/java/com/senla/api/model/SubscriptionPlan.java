package com.senla.api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public class SubscriptionPlan {
    private UUID id;

    @NotNull
    private SubscriptionType name;

    @NotNull
    @PositiveOrZero
    private BigDecimal pricePerMonth;

    public SubscriptionPlan() {
    }

    public SubscriptionPlan(SubscriptionType name, BigDecimal pricePerMonth) {
        this.name = name;
        this.pricePerMonth = pricePerMonth;
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
}
