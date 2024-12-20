package com.senla.api.dto.comment;

import com.senla.api.util.ValidationUtil;

public class CommentUpdateDto {
    private String content;

    public CommentUpdateDto() {
    }

    public CommentUpdateDto(String content) {
        this.content = ValidationUtil.validateNotNullOrEmpty(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
