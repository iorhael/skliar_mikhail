package com.senla.model;

import com.senla.util.ValidationUtil;

import java.util.UUID;

public class PollOption {
    private final UUID id;
    private final UUID pollId;
    private final String description;

    PollOption(Builder builder){
        this.id = builder.id;
        this.pollId = ValidationUtil.validateNotNull(builder.pollId);
        this.description = ValidationUtil.validateNotNullOrEmpty(builder.description);
    }

    public UUID getId() {
        return id;
    }

    public UUID getPollId() {
        return pollId;
    }

    public String getDescription() {
        return description;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder{
        private UUID id;
        private UUID pollId;
        private String description;

        public Builder id(UUID id){
            this.id = id;
            return this;
        }

        public Builder pollId(UUID pollId){
            this.pollId = pollId;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public PollOption build(){
            return new PollOption(this);
        }
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
