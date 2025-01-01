package com.senla.dto.role;

import com.senla.model.RoleName;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class RoleDto {
    private UUID id;

    @NotNull
    private UUID userId;

    @NotNull
    private RoleName name;

    public RoleDto() {
    }

    public RoleDto(UUID userId, RoleName name) {
        this.userId = userId;
        this.name = name;
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
}
