package com.senla.api.service.imp;

import com.senla.api.dao.RoleDao;
import com.senla.api.model.Role;
import com.senla.api.service.RoleService;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Optional<Role> createRole(Role role) {
        return roleDao.create(role);
    }

    @Override
    public Optional<Role> getRoleById(UUID id) {
        return roleDao.getById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAll();
    }

    @Override
    public Optional<Role> updateRole(Role role, UUID id) {
        return roleDao.update(role, id);
    }

    @Override
    public Optional<Role> deleteRole(UUID id) {
        return roleDao.delete(id);
    }
}
