package com.senla.dto.vote;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class VoteDto {
    @NotNull
    private UUID pollOptionId;

    @NotNull
    private UUID userId;

    private LocalDateTime voteDate;

    public VoteDto() {
    }

    public VoteDto(UUID pollOptionId, UUID userId) {
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
}
