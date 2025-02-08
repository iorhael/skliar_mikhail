package com.senla.service.imp;

import com.senla.aspect.Benchmarked;
import com.senla.dto.role.RoleCreateDto;
import com.senla.dto.role.RoleGetDto;
import com.senla.model.Role;
import com.senla.model.User;
import com.senla.repository.RoleRepository;
import com.senla.repository.UserRepository;
import com.senla.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Benchmarked
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    public static final String ROLE_NOT_FOUND = "Role not found";

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public RoleGetDto createRole(RoleCreateDto roleCreateDto) {
        Role role = modelMapper.map(roleCreateDto, Role.class);

        User user = userRepository.getReferenceById(roleCreateDto.getUserId());
        role.setUser(user);

        roleRepository.save(role);

        return modelMapper.map(role, RoleGetDto.class);
    }

    @Override
    public RoleGetDto getRoleBy(UUID id) {
        return roleRepository.findById(id)
                .map(publicationStatus -> modelMapper.map(publicationStatus, RoleGetDto.class))
                .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND));
    }

    @Override
    public List<RoleGetDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(publicationStatus -> modelMapper.map(publicationStatus, RoleGetDto.class))
                .toList();
    }

    @Override
    @Transactional
    public RoleGetDto updateRole(RoleCreateDto roleCreateDto, UUID id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND));

        User user = userRepository.getReferenceById(roleCreateDto.getUserId());

        role.setUser(user);
        role.setName(roleCreateDto.getName());

        return modelMapper.map(role, RoleGetDto.class);
    }

    @Override
    public void deleteRole(UUID id) {
        roleRepository.deleteById(id);
    }
}
