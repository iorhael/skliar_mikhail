package com.senla.api.service;

import com.senla.api.model.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    Role createRole(Role role);

    Role getRoleById(UUID id);

    List<Role> getAllRoles();

    Role updateRole(Role role, UUID id);

    Role deleteRole(UUID id);
}
