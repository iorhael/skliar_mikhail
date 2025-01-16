package com.senla.dto.subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubscriptionGetDto {

    private UUID id;

    private LocalDateTime startedDate;

    private LocalDateTime expiresDate;
}
