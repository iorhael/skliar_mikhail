package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.role.RoleDto;
import com.senla.model.Role;
import com.senla.repository.RoleRepository;
import com.senla.repository.exception.RoleNotFoundException;
import com.senla.service.RoleService;
import com.senla.service.exception.role.RoleCreateException;
import com.senla.service.exception.role.RoleDeleteException;
import com.senla.service.exception.role.RoleUpdateException;
import com.senla.util.ModelMapperUtil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDto createRole(RoleDto role) {
        Role roleEntity = ModelMapperUtil.MODEL_MAPPER.map(role, Role.class);

        return roleRepository.create(roleEntity)
                .map(r -> ModelMapperUtil.MODEL_MAPPER.map(r, RoleDto.class))
                .orElseThrow(() -> new RoleCreateException("Can't create role"));
    }

    @Override
    public RoleDto getRoleById(UUID id) {
        return roleRepository.getById(id)
                .map(role -> ModelMapperUtil.MODEL_MAPPER.map(role, RoleDto.class))
                .orElseThrow(() -> new RoleNotFoundException("No role found"));
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository.getAll().stream()
                .map(role -> ModelMapperUtil.MODEL_MAPPER.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto updateRole(RoleDto role, UUID id) {
        Role roleEntity = ModelMapperUtil.MODEL_MAPPER.map(role, Role.class);

        return roleRepository.update(roleEntity, id)
                .map(r -> ModelMapperUtil.MODEL_MAPPER.map(r, RoleDto.class))
                .orElseThrow(() -> new RoleUpdateException("Can't update role"));
    }

    @Override
    public RoleDto deleteRole(UUID id) {
        return roleRepository.delete(id)
                .map(pollOption -> ModelMapperUtil.MODEL_MAPPER.map(pollOption, RoleDto.class))
                .orElseThrow(() -> new RoleDeleteException("Can't delete role"));
    }
}
