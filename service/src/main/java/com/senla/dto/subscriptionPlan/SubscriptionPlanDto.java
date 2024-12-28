package com.senla.dto.subscriptionPlan;

import com.senla.model.SubscriptionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public class SubscriptionPlanDto {
    private UUID id;

    @NotNull
    private SubscriptionType name;

    @NotNull
    @PositiveOrZero
    private BigDecimal pricePerMonth;

    public SubscriptionPlanDto() {
    }

    public SubscriptionPlanDto(SubscriptionType name, BigDecimal pricePerMonth) {
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
}
