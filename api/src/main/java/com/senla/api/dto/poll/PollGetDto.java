package com.senla.api.dto.poll;

import java.util.UUID;

public class PollGetDto {
    private UUID id;
    private UUID userId;
    private String description;

    public PollGetDto() {
    }

    public PollGetDto(UUID id, UUID userId, String description) {
        this.id = id;
        this.userId = userId;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
