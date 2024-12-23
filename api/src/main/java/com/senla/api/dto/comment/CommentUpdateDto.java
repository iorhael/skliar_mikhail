package com.senla.api.dto.comment;

import jakarta.validation.constraints.NotBlank;

public class CommentUpdateDto {
    @NotBlank
    private String content;

    public CommentUpdateDto() {
    }

    public CommentUpdateDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
