package com.senla.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostCreateDto {
    @NotNull
    private UUID authorId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private LocalDateTime publicationDate;

    @NotNull
    private UUID subscriptionPlanId;

    public PostCreateDto() {
    }

    public PostCreateDto(UUID authorId, String title, String content, LocalDateTime publicationDate, UUID subscriptionPlanId) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.publicationDate = publicationDate;
        this.subscriptionPlanId = subscriptionPlanId;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
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

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public UUID getSubscriptionPlanId() {
        return subscriptionPlanId;
    }

    public void setSubscriptionPlanId(UUID subscriptionPlanId) {
        this.subscriptionPlanId = subscriptionPlanId;
    }
}
