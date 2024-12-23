package com.senla.api.dto.post;

import com.senla.api.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostCreateDto {
    private UUID authorId;
    private String title;
    private String content;
    private LocalDateTime publicationDate;
    private UUID subscriptionPlanId;

    public PostCreateDto() {
    }

    public PostCreateDto(UUID authorId, String title, String content, LocalDateTime publicationDate, UUID subscriptionPlanId) {
        this.authorId = ValidationUtil.validateNotNull(authorId);
        this.title = ValidationUtil.validateNotNullOrEmpty(title);
        this.content = ValidationUtil.validateNotNullOrEmpty(content);
        this.publicationDate = ValidationUtil.validateNotNull(publicationDate);
        this.subscriptionPlanId = ValidationUtil.validateNotNull(subscriptionPlanId);
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
