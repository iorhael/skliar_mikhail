package com.senla.dto.subscription;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class SubscriptionUpdateDto {

    @NotNull
    @FutureOrPresent
    private Instant expiresDate;
}
