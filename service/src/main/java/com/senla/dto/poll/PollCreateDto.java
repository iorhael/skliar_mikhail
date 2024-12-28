package com.senla.dto.poll;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class PollCreateDto {
    @NotNull
    private UUID postId;

    @NotNull
    private UUID authorId;

    @NotBlank
    private String description;

    public PollCreateDto() {
    }

    public PollCreateDto(UUID postId, UUID authorId, String description) {
        this.postId = postId;
        this.authorId = authorId;
        this.description = description;
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
