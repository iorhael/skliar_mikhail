package com.senla.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateDto {

    @NotBlank
    private String content;
}
