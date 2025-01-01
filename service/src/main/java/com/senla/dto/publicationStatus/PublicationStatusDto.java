package com.senla.dto.publicationStatus;

import com.senla.model.PostStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class PublicationStatusDto {
    private UUID id;

    @NotNull
    private UUID postId;

    @NotNull
    private PostStatus statusName;

    @FutureOrPresent
    private LocalDateTime scheduledDate;

    public PublicationStatusDto() {
    }

    public PublicationStatusDto(UUID postId, PostStatus statusName) {
        this.postId = postId;
        this.statusName = statusName;
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
