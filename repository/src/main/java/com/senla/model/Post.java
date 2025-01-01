package com.senla.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class Post {
    private UUID id;

    @NotNull
    private UUID authorId;

    @NotNull
    private UUID subscriptionPlanId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Long viewsTotal;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private LocalDateTime publicationDate;

    public Post() {
    }

    public Post(UUID authorId, UUID subscriptionPlanId, String title, String content) {
        this.authorId = authorId;
        this.subscriptionPlanId = subscriptionPlanId;
        this.title = title;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public UUID getSubscriptionPlanId() {
        return subscriptionPlanId;
    }

    public void setSubscriptionPlanId(UUID subscriptionPlanId) {
        this.subscriptionPlanId = subscriptionPlanId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getViewsTotal() {
        return viewsTotal;
    }

    public void setViewsTotal(long viewsTotal) {
        this.viewsTotal = viewsTotal;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
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
