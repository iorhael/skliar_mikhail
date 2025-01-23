package com.senla.dto.role;

import com.senla.model.RoleName;
import com.senla.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleCreateDto {

    @NotNull
    private User user;

    @NotNull
    private RoleName name;
}
