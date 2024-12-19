package com.senla.api.model;

import com.senla.api.util.ValidationUtil;

import java.util.UUID;

public class Tag {
    private final UUID id;
    private final String name;

    Tag(Builder builder) {
        this.id = builder.id;
        this.name = ValidationUtil.validateNotNullOrEmpty(builder.name);
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static class Builder {
        private UUID id;
        private String name;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Tag build() {
            return new Tag(this);
        }
    }
}
