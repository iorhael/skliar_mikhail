package com.senla.service;

import com.senla.dto.role.RoleCreateDto;
import com.senla.dto.role.RoleGetDto;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    RoleGetDto createRole(RoleCreateDto role);

    RoleGetDto getRoleById(UUID id);

    List<RoleGetDto> getAllRoles();

    RoleGetDto updateRole(RoleGetDto role, UUID id);

    RoleGetDto deleteRole(UUID id);
}
