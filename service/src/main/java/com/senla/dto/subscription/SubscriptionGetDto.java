package com.senla.dto.subscription;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class SubscriptionGetDto {

    private UUID id;

    private String username;

    private Instant startedDate;

    private Instant expiresDate;
}
