package com.senla.api.dto.post;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostGetDto {
    private UUID id;
    private UUID authorId;
    private String title;
    private String content;
    private LocalDateTime publicationDate;

    public PostGetDto() {
    }

    public PostGetDto(UUID id, UUID authorId, String title, String content, LocalDateTime publicationDate) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.publicationDate = publicationDate;
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
}

