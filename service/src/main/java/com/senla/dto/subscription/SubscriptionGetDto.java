package com.senla.dto.subscription;

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
public class SubscriptionGetDto {

    private UUID id;

    private Instant startedDate;

    private Instant expiresDate;
}
