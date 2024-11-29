package com.senla.model;

import com.senla.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.UUID;

public class Vote {
    private final UUID pollOptionId;
    private final UUID userId;
    private final LocalDateTime voteDate;

    Vote(Builder builder) {
        this.pollOptionId = ValidationUtil.validateNotNull(builder.pollOptionId);
        this.userId = ValidationUtil.validateNotNull(builder.userId);
        this.voteDate = builder.voteDate;
    }

    public UUID getPollOptionId() {
        return pollOptionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public LocalDateTime getVoteDate() {
        return voteDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID pollOptionId;
        private UUID userId;
        private LocalDateTime voteDate;

        public Builder pollOptionId(UUID pollOptionId) {
            this.pollOptionId = pollOptionId;
            return this;
        }

        public Builder userId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder voteDate(LocalDateTime voteDate) {
            this.voteDate = voteDate;
            return this;
        }

        public Vote build() {
            return new Vote(this);
        }
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
