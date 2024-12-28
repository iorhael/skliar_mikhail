package com.senla.service;

import com.senla.dto.role.RoleDto;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    RoleDto createRole(RoleDto role);

    RoleDto getRoleById(UUID id);

    List<RoleDto> getAllRoles();

    RoleDto updateRole(RoleDto role, UUID id);

    RoleDto deleteRole(UUID id);
}
