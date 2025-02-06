package com.senla.dto.role;

import com.senla.model.RoleName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class RoleGetDto {

    private UUID id;

    private RoleName name;
}
