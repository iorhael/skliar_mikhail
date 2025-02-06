package com.senla.dto.subscription;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
public class SubscriptionUpdateDto {

    @NotNull
    @FutureOrPresent
    private Instant expiresDate;
}
