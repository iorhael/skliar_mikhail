package com.senla.dto.comment;

import java.time.LocalDateTime;
import java.util.UUID;

public class CommentGetDto {
    private UUID id;
    private UUID authorId;
    private String content;
    private LocalDateTime createdDate;

    public CommentGetDto() {
    }

    public CommentGetDto(UUID id, UUID authorId, String content, LocalDateTime createdDate) {
        this.id = id;
        this.authorId = authorId;
        this.content = content;
        this.createdDate = createdDate;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
