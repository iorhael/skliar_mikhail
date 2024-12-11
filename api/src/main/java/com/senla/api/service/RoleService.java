package com.senla.api.service;

import com.senla.api.model.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService {
    Optional<Role> createRole(Role role);

    Optional<Role> getRoleById(UUID id);

    List<Role> getAllRoles();

    Optional<Role> updateRole(Role role, UUID id);

    Optional<Role> deleteRole(UUID id);
}
