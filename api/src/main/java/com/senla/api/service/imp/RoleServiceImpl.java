package com.senla.api.service.imp;

import com.senla.api.dao.RoleDao;
import com.senla.api.dao.exception.RoleNotFoundException;
import com.senla.api.model.Role;
import com.senla.api.service.RoleService;
import com.senla.api.service.exception.role.RoleCreateException;
import com.senla.api.service.exception.role.RoleDeleteException;
import com.senla.api.service.exception.role.RoleUpdateException;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    public Role createRole(Role role) {
        return roleDao.create(role).orElseThrow(() -> new RoleCreateException("Can't create role"));
    }

    @Override
    public Role getRoleById(UUID id) {
        return roleDao.getById(id).orElseThrow(() -> new RoleNotFoundException("No role found"));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAll();
    }

    @Override
    public Role updateRole(Role role, UUID id) {
        return roleDao.update(role, id).orElseThrow(() -> new RoleUpdateException("Can't update role"));
    }

    @Override
    public Role deleteRole(UUID id) {
        return roleDao.delete(id).orElseThrow(() -> new RoleDeleteException("Can't delete role"));
    }
}
