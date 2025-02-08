package com.senla.dto.role;

import com.senla.model.RoleName;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class RoleCreateDto {

    @NotNull
    private UUID userId;

    @NotNull
    private RoleName name;
}
