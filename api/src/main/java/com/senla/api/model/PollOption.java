package com.senla.api.model;

import com.senla.api.util.ValidationUtil;

import java.util.UUID;

public class PollOption {
    private UUID id;
    private UUID pollId;
    private String description;

    public PollOption() {
    }

    public PollOption(UUID pollId, String description) {
        this.pollId = ValidationUtil.validateNotNull(pollId);
        this.description = ValidationUtil.validateNotNullOrEmpty(description);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPollId() {
        return pollId;
    }

    public void setPollId(UUID pollId) {
        this.pollId = pollId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PollOption{" +
                "id=" + id +
                ", pollId=" + pollId +
                ", description='" + description + '\'' +
                '}';
    }
}
