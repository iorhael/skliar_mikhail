package com.senla.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class Vote {
    @NotNull
    private UUID pollOptionId;

    @NotNull
    private UUID userId;

    private LocalDateTime voteDate;

    public Vote() {
    }

    public Vote(UUID pollOptionId, UUID userId) {
        this.pollOptionId = pollOptionId;
        this.userId = userId;
    }

    public UUID getPollOptionId() {
        return pollOptionId;
    }

    public void setPollOptionId(UUID pollOptionId) {
        this.pollOptionId = pollOptionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDateTime getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDateTime voteDate) {
        this.voteDate = voteDate;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "pollOptionId=" + pollOptionId +
                ", userId=" + userId +
                ", voteDate=" + voteDate +
                '}';
    }

}
