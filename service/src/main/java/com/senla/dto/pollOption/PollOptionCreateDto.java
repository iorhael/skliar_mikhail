package com.senla.dto.pollOption;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PollOptionCreateDto {

    @NotNull
    private UUID pollId;

    @NotBlank
    private String description;
}
