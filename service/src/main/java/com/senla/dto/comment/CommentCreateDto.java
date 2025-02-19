package com.senla.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentCreateDto {

    @NotNull
    private UUID postId;

    @NotNull
    private UUID authorId;

    @NotBlank
    private String content;

    private UUID parentId;
}
