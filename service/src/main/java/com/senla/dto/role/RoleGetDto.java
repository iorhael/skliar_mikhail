package com.senla.dto.role;

import com.senla.model.RoleName;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RoleGetDto {

    private UUID id;

    private RoleName name;
}
