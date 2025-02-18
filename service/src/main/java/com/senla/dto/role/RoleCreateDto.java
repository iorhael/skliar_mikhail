package com.senla.dto.role;

import com.senla.model.RoleName;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RoleCreateDto {

    @NotNull
    private UUID userId;

    @NotNull
    private RoleName name;
}
