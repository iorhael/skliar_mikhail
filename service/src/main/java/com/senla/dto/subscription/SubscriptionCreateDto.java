package com.senla.dto.subscription;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubscriptionCreateDto {

    @NotNull
    private UUID userId;

    @NotNull
    private UUID subscriptionPlanId;

    @NotNull
    @FutureOrPresent
    private Instant expiresDate;
}
