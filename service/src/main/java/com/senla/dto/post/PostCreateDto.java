package com.senla.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class PostCreateDto {

    @NotNull
    private UUID authorId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Instant publicationDate;

    @NotNull
    private UUID subscriptionPlanId;
}
