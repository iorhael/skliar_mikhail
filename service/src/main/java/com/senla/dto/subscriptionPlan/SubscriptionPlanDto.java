package com.senla.dto.subscriptionPlan;

import com.senla.model.SubscriptionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
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
