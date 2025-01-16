package com.senla.dto.subscription;

import com.senla.model.SubscriptionPlan;
import com.senla.model.User;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubscriptionCreateDto {

    @NotNull
    private User user;

    @NotNull
    private SubscriptionPlan subscriptionPlan;

    @NotNull
    @FutureOrPresent
    private LocalDateTime expiresDate;
}
