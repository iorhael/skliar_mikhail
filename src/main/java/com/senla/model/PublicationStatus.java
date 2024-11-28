package com.senla.model;

import com.senla.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.UUID;

public class PublicationStatus {
    private final UUID id;
    private final UUID postId;
    private final PostStatus statusName;
    private final LocalDateTime scheduledDate;

    PublicationStatus(Builder builder){
        this.id = builder.id;
        this.postId = ValidationUtil.validateNotNull(builder.postId);
        this.statusName = ValidationUtil.validateNotNull(builder.statusName);
        this.scheduledDate = builder.scheduledDate;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPostId() {
        return postId;
    }

    public PostStatus getStatusName() {
        return statusName;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public static class Builder {
        private UUID id;
        private UUID postId;
        private PostStatus statusName;
        private LocalDateTime scheduledDate;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder postId(UUID postId) {
            this.postId = postId;
            return this;
        }

        public Builder statusName(PostStatus statusName) {
            this.statusName = statusName;
            return this;
        }

        public Builder scheduledDate(LocalDateTime scheduledDate) {
            this.scheduledDate = scheduledDate;
            return this;
        }

        public PublicationStatus build() {
            return new PublicationStatus(this);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "id=" + id +
                    ", postId=" + postId +
                    ", statusName=" + statusName +
                    ", scheduledDate=" + scheduledDate +
                    '}';
        }
    }
}
