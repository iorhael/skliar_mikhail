package com.senla.dto.vote;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class VoteCreateDto {

    @NotNull
    private UUID pollOptionId;

    @NotNull
    private UUID ownerId;

    private Instant voteDate;
}
