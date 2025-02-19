package com.senla.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PostUpdateDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Instant publicationDate;
}
