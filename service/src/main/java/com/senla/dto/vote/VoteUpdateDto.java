package com.senla.dto.vote;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VoteUpdateDto {

    @NotNull
    private UUID pollOptionId;
}
