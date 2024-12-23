package com.senla.api.dto.comment;

import com.senla.api.util.ValidationUtil;

import java.util.UUID;

public class CommentCreateDto {
    private UUID postId;
    private UUID authorId;
    private String content;
    private UUID parentId;

    public CommentCreateDto() {
    }

    public CommentCreateDto(UUID postId, UUID authorId, String content, UUID parentId) {
        this.postId = ValidationUtil.validateNotNull(postId);
        this.authorId = ValidationUtil.validateNotNull(authorId);
        this.content = ValidationUtil.validateNotNullOrEmpty(content);
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
