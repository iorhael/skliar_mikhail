package com.senla.service.imp;

import com.senla.dto.role.RoleCreateDto;
import com.senla.dto.role.RoleGetDto;
import com.senla.model.Role;
import com.senla.repository.RoleRepository;
import com.senla.service.RoleService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.role.RoleDeleteException;
import com.senla.service.exception.role.RoleUpdateException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public RoleGetDto createRole(RoleCreateDto role) {
        Role roleEntity = modelMapper.map(role, Role.class);

        Role createdRole = roleRepository.create(roleEntity);

        return modelMapper.map(createdRole, RoleGetDto.class);
    }

    @Transactional
    @Override
    public RoleGetDto getRoleById(UUID id) {
        return roleRepository.findById(id)
                .map(role -> modelMapper.map(role, RoleGetDto.class))
                .orElseThrow(() -> new ServiceException("No role found"));
    }

    @Transactional
    @Override
    public List<RoleGetDto> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(role -> modelMapper.map(role, RoleGetDto.class))
                .toList();
    }

    @Transactional
    @Override
    public RoleGetDto updateRole(RoleGetDto role, UUID id) {
        Role roleEntity = modelMapper.map(role, Role.class);

        return roleRepository.update(roleEntity, id)
                .map(r -> modelMapper.map(r, RoleGetDto.class))
                .orElseThrow(() -> new RoleUpdateException("Can't update role"));
    }

    @Transactional
    @Override
    public RoleGetDto deleteRole(UUID id) {
        return roleRepository.deleteById(id)
                .map(pollOption -> modelMapper.map(pollOption, RoleGetDto.class))
                .orElseThrow(() -> new RoleDeleteException("Can't delete role"));
    }
}
