package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.role.RoleCreateDto;
import com.senla.dto.role.RoleGetDto;
import com.senla.model.Role;
import com.senla.repository.RoleRepository;
import com.senla.service.RoleService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.role.RoleDeleteException;
import com.senla.service.exception.role.RoleUpdateException;
import com.senla.util.ModelMapperUtil;

import java.util.List;
import java.util.UUID;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleGetDto createRole(RoleCreateDto role) {
        Role roleEntity = ModelMapperUtil.MODEL_MAPPER.map(role, Role.class);
        Role createdRole = roleRepository.create(roleEntity);

        return ModelMapperUtil.MODEL_MAPPER.map(createdRole, RoleGetDto.class);
    }

    @Override
    public RoleGetDto getRoleById(UUID id) {
        return roleRepository.findById(id)
                .map(role -> ModelMapperUtil.MODEL_MAPPER.map(role, RoleGetDto.class))
                .orElseThrow(() -> new ServiceException("No role found"));
    }

    @Override
    public List<RoleGetDto> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(role -> ModelMapperUtil.MODEL_MAPPER.map(role, RoleGetDto.class))
                .toList();
    }

    @Override
    public RoleGetDto updateRole(RoleGetDto role, UUID id) {
        Role roleEntity = ModelMapperUtil.MODEL_MAPPER.map(role, Role.class);

        return roleRepository.update(roleEntity, id)
                .map(r -> ModelMapperUtil.MODEL_MAPPER.map(r, RoleGetDto.class))
                .orElseThrow(() -> new RoleUpdateException("Can't update role"));
    }

    @Override
    public RoleGetDto deleteRole(UUID id) {
        return roleRepository.deleteById(id)
                .map(pollOption -> ModelMapperUtil.MODEL_MAPPER.map(pollOption, RoleGetDto.class))
                .orElseThrow(() -> new RoleDeleteException("Can't delete role"));
    }
}
