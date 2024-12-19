package com.senla.api.dto.user;

import com.senla.api.util.ValidationUtil;

import java.util.UUID;

public class UserGetDto {
    private UUID id;
    private String username;
    private String email;

    public UserGetDto() {
    }

    public UserGetDto(UUID id, String username, String email) {
        this.id = ValidationUtil.validateNotNull(id);
        this.username = ValidationUtil.validateNotNullOrEmpty(username);
        this.email = ValidationUtil.validateNotNullOrEmpty(email);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
