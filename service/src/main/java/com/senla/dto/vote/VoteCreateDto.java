package com.senla.dto.vote;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class VoteCreateDto {

    @NotNull
    private UUID pollOptionId;

    @NotNull
    private UUID ownerId;

    private Instant voteDate;
}
