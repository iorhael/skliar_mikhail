package com.senla.service.unit;

import com.senla.dto.role.RoleCreateDto;
import com.senla.dto.role.RoleGetDto;
import com.senla.model.Role;
import com.senla.model.RoleName;
import com.senla.model.User;
import com.senla.repository.RoleRepository;
import com.senla.repository.UserRepository;
import com.senla.service.imp.RoleServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    RoleRepository roleRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    RoleServiceImpl roleService;

    Role role;

    @BeforeEach
    void setup() {
        role = new Role();
        role.setId(UUID.randomUUID());
        role.setName(RoleName.READER);
    }

    @Test
    void whenCreateRole_thenReturnRoleGetDto() {
        RoleCreateDto createDto = new RoleCreateDto();
        createDto.setUserId(UUID.randomUUID());

        given(modelMapper.map(createDto, Role.class))
                .willReturn(role);
        given(userRepository.getReferenceById(createDto.getUserId()))
                .willReturn(new User());
        given(modelMapper.map(role, RoleGetDto.class))
                .willReturn(new RoleGetDto());

        assertThat(roleService.createRole(createDto))
                .isInstanceOf(RoleGetDto.class);
        assertNotNull(role.getUser());
    }

    @Test
    void givenRole_whenGetRoleById_thenReturnRoleGetDto() {
        UUID id = role.getId();

        given(roleRepository.findById(id))
                .willReturn(Optional.of(role));
        given(modelMapper.map(role, RoleGetDto.class))
                .willReturn(new RoleGetDto());

        assertThat(roleService.getRoleBy(id))
                .isInstanceOf(RoleGetDto.class);
    }

    @Test
    void givenNoRole_whenGetRoleById_thenThrowEntityNotFoundException() {
        UUID id = role.getId();

        given(roleRepository.findById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> roleService.getRoleBy(id));
        verify(modelMapper, never()).map(any(Role.class), eq(RoleGetDto.class));
    }


    @Test
    void givenListOfRoles_whenGetAllRoles_thenReturnListOfRoleGetDto() {
        Role role1 = new Role();
        List<Role> roles = List.of(role, role1);

        given(roleRepository.findAll())
                .willReturn(roles);
        given(modelMapper.map(any(Role.class), eq(RoleGetDto.class)))
                .willReturn(new RoleGetDto());

        assertThat(roleService.getAllRoles())
                .hasSize(roles.size());
    }

    @Test
    void givenRole_whenUpdateRole_thenReturnRoleGetDto() {
        RoleCreateDto createDto = new RoleCreateDto();
        createDto.setUserId(UUID.randomUUID());
        createDto.setName(RoleName.ADMIN);
        UUID id = role.getId();

        given(roleRepository.findById(id))
                .willReturn(Optional.of(role));
        given(userRepository.getReferenceById(createDto.getUserId()))
                .willReturn(new User());
        given(modelMapper.map(role, RoleGetDto.class))
                .willReturn(new RoleGetDto());

        assertThat(roleService.updateRole(createDto, id))
                .isInstanceOf(RoleGetDto.class);
        assertNotNull(role.getUser());
        assertEquals(RoleName.ADMIN, role.getName());
    }

    @Test
    void givenNoRole_whenUpdateRole_thenThrowEntityNotFoundException() {
        RoleCreateDto createDto = new RoleCreateDto();
        UUID id = role.getId();

        given(roleRepository.findById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> roleService.updateRole(createDto, id));
    }

    @Test
    void whenDeleteRole() {
        UUID id = role.getId();

        roleService.deleteRole(id);

        verify(roleRepository, times(1)).deleteById(id);
    }
}
