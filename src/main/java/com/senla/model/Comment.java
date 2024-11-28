package com.senla.model;

import com.senla.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    private final UUID id;
    private final UUID postId;
    private final UUID authorId;
    private final String content;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;
    private final UUID parentId;

    Comment(Builder builder) {
        this.id = builder.id;
        this.postId = ValidationUtil.validateNotNull(builder.postId);
        this.authorId = ValidationUtil.validateNotNull(builder.authorId);
        this.content = ValidationUtil.validateNotNullOrEmpty(builder.content);
        this.createdDate = builder.createdDate;
        this.updatedDate = builder.updatedDate;
        this.parentId = builder.parentId;
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

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public UUID getParentId() {
        return parentId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private UUID postId;
        private UUID authorId;
        private String content;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        private UUID parentId;

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

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder updatedDate(LocalDateTime updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }

        public Builder parentId(UUID parentId) {
            this.parentId = parentId;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId=" + postId +
                ", authorId=" + authorId +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", parentId=" + parentId +
                '}';
    }
}
