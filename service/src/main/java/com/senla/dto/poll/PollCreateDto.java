package com.senla.dto.poll;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class PollCreateDto {

    @NotNull
    private UUID postId;

    @NotNull
    private UUID authorId;

    @NotBlank
    private String description;
}
