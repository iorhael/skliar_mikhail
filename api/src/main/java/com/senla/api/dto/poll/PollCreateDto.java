package com.senla.api.dto.poll;

import com.senla.api.util.ValidationUtil;

import java.util.UUID;

public class PollCreateDto {
    private UUID postId;
    private UUID authorId;
    private String description;

    public PollCreateDto() {
    }

    public PollCreateDto(UUID postId, UUID authorId, String description) {
        this.postId = ValidationUtil.validateNotNull(postId);
        this.authorId = ValidationUtil.validateNotNull(authorId);
        this.description = ValidationUtil.validateNotNullOrEmpty(description);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
