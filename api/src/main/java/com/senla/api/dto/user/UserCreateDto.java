package com.senla.api.dto.user;

import com.senla.api.util.ValidationUtil;

public class UserCreateDto {
    private String username;
    private String email;
    private String password;

    public UserCreateDto() {
    }

    public UserCreateDto(String username, String email, String password) {
        this.username = ValidationUtil.validateNotNullOrEmpty(username);
        this.email = ValidationUtil.validateNotNullOrEmpty(email);
        this.password = ValidationUtil.validateNotNull(password);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
