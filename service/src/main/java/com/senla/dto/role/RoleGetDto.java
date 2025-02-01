package com.senla.dto.role;

import com.senla.model.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleGetDto {

    private UUID id;

    private RoleName name;
}
