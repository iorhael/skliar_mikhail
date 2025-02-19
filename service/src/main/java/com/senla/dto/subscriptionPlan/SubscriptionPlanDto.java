package com.senla.dto.subscriptionPlan;

import com.senla.model.SubscriptionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class SubscriptionPlanDto {

    private UUID id;

    @NotNull
    private SubscriptionType name;

    @NotNull
    @PositiveOrZero
    private BigDecimal pricePerMonth;
}
