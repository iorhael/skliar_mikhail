package com.senla.api.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CommentCreateDto {
    @NotNull
    private UUID postId;

    @NotNull
    private UUID authorId;

    @NotBlank
    private String content;

    private UUID parentId;

    public CommentCreateDto() {
    }

    public CommentCreateDto(UUID postId, UUID authorId, String content, UUID parentId) {
        this.postId = postId;
        this.authorId = authorId;
        this.content = content;
        this.parentId = parentId;
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }
}
