package com.senla.api.model;

import com.senla.api.util.ValidationUtil;

import java.util.UUID;

public class Role {
    private UUID id;
    private UUID userId;
    private RoleName name;

    public Role() {
    }

    public Role(UUID userId, RoleName name) {
        this.userId = ValidationUtil.validateNotNull(userId);
        this.name = ValidationUtil.validateNotNull(name);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
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
