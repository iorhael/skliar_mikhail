package com.senla.model;

import com.senla.util.ValidationUtil;

import java.util.UUID;

public class Role {
    private final UUID id;
    private final UUID userId;
    private final RoleName name;

    Role(Builder builder) {
        this.id = builder.id;
        this.userId = ValidationUtil.validateNotNull(builder.userId);
        this.name = ValidationUtil.validateNotNull(builder.name);
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public RoleName getName() {
        return name;
    }

    public static class Builder {
        private UUID id;
        private UUID userId;
        private RoleName name;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder userId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder name(RoleName name) {
            this.name = name;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", userId=" + userId +
                ", name=" + name +
                '}';
    }
}
