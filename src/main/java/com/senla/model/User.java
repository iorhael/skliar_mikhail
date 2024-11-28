package com.senla.model;

import com.senla.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private final UUID id;
    private final String username;
    private final String email;
    private final String password;
    private final LocalDateTime createdDate;

    User(Builder builder) {
        this.id = builder.id;
        this.username = ValidationUtil.validateNotNullOrEmpty(builder.username);
        this.email = ValidationUtil.validateNotNullOrEmpty(builder.email);
        this.password = ValidationUtil.validateNotNullOrEmpty(builder.password);
        this.createdDate = builder.createdDate;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private String username;
        private String email;
        private String password;
        private LocalDateTime createdDate;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
