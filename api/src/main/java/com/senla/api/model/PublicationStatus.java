package com.senla.api.model;

import com.senla.api.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.UUID;

public class PublicationStatus {
    private UUID id;
    private UUID postId;
    private PostStatus statusName;
    private LocalDateTime scheduledDate;

    public PublicationStatus() {
    }

    public PublicationStatus(UUID postId, PostStatus statusName) {
        this.postId = ValidationUtil.validateNotNull(postId);
        this.statusName = ValidationUtil.validateNotNull(statusName);
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

    public PostStatus getStatusName() {
        return statusName;
    }

    public void setStatusName(PostStatus statusName) {
        this.statusName = statusName;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
}
