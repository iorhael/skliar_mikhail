package com.senla.api.model;

import com.senla.api.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.UUID;

public class Post {
    private final UUID id;
    private final UUID authorId;
    private final UUID subscriptionPlanId;
    private final String title;
    private final String content;
    private final long viewsTotal;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;
    private final LocalDateTime publicationDate;

    Post(Builder builder) {
        this.id = builder.id;
        this.authorId = ValidationUtil.validateNotNull(builder.authorId);
        this.subscriptionPlanId = ValidationUtil.validateNotNull(builder.subscriptionPlanId);
        this.title = ValidationUtil.validateNotNullOrEmpty(builder.title);
        this.content = ValidationUtil.validateNotNullOrEmpty(builder.content);
        this.viewsTotal = builder.viewsTotal;
        this.createdDate = builder.createdDate;
        this.updatedDate = builder.updatedDate;
        this.publicationDate = builder.publicationDate;
    }

    public UUID getId() {
        return id;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public UUID getSubscriptionPlanId() {
        return subscriptionPlanId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getViewsTotal() {
        return viewsTotal;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private UUID authorId;
        private UUID subscriptionPlanId;
        private String title;
        private String content;
        private long viewsTotal;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        private LocalDateTime publicationDate;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder authorId(UUID authorId) {
            this.authorId = authorId;
            return this;
        }

        public Builder subscriptionPlanId(UUID subscriptionPlanId) {
            this.subscriptionPlanId = subscriptionPlanId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder viewsTotal(long viewsTotal) {
            this.viewsTotal = viewsTotal;
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

        public Builder publicationDate(LocalDateTime publicationDate) {
            this.publicationDate = publicationDate;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", subscriptionPlanId=" + subscriptionPlanId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", viewsTotal=" + viewsTotal +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
