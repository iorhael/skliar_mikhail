package com.senla.api.model;

import com.senla.api.util.ValidationUtil;

import java.util.UUID;

public class Poll {
    private final UUID id;
    private final UUID postId;
    private final UUID authorId;
    private final String description;

    Poll(Builder builder) {
        this.id = builder.id;
        this.postId = builder.postId;
        this.authorId = ValidationUtil.validateNotNull(builder.authorId);
        this.description = ValidationUtil.validateNotNullOrEmpty(builder.description);
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
    }

    public UUID getPostId() {
        return postId;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public String getDescription() {
        return description;
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

    public static class Builder {
        private UUID id;
        private UUID postId;
        private UUID authorId;
        private String description;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder postId(UUID postId) {
            this.postId = postId;
            return this;
        }

        public Builder authorId(UUID authorId) {
            this.authorId = authorId;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Poll build() {
            return new Poll(this);
        }
    }
}
