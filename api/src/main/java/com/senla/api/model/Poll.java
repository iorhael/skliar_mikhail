package com.senla.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class Poll {
    private UUID id;

    private UUID postId;

    @NotNull
    private UUID authorId;

    @NotBlank
    private String description;

    public Poll() {
    }

    public Poll(UUID authorId, String description) {
        this.authorId = authorId;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", postId=" + postId +
                ", authorId=" + authorId +
                ", description='" + description + '\'' +
                '}';
    }
}
