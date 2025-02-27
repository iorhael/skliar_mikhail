package com.senla.service.unit;

import com.senla.dto.user.UserCreateDto;
import com.senla.dto.user.UserGetDto;
import com.senla.dto.user.UserUpdateDto;
import com.senla.dto.user.UserWithRolesDto;
import com.senla.model.Role;
import com.senla.model.RoleName;
import com.senla.model.User;
import com.senla.repository.UserRepository;
import com.senla.service.imp.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    static final String STRING_PLACEHOLDER =  "Test";

    @Mock
    UserRepository userRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl userService;

    User user;

    @BeforeEach
    void setup() {
        user = new User();
        user.setUsername(STRING_PLACEHOLDER);
        user.setEmail(STRING_PLACEHOLDER);
        user.setCreatedDate(Instant.now());
    }

    @Test
    void whenCreateUser_thenReturnUserGetDto() {
        UserCreateDto createDto = new UserCreateDto();

        given(modelMapper.map(createDto, User.class))
                .willReturn(user);
        given(passwordEncoder.encode(createDto.getPassword()))
                .willReturn(STRING_PLACEHOLDER);
        given(userRepository.save(user))
                .willReturn(user);
        given(modelMapper.map(user, UserWithRolesDto.class))
                .willReturn(new UserWithRolesDto());

        assertThat(userService.createUser(createDto))
                .isInstanceOf(UserWithRolesDto.class);
        assertEquals(Set.of(RoleName.READER),
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet()));
        assertNotNull(user.getPassword());
    }

    @Test
    void givenUser_whenGetUserById_thenReturnUserGetDto() {
        UUID id = user.getId();

        given(userRepository.findById(id))
                .willReturn(Optional.of(user));
        given(modelMapper.map(user, UserGetDto.class))
                .willReturn(new UserGetDto());

        assertThat(userService.getUserBy(id))
                .isInstanceOf(UserGetDto.class);
    }

    @Test
    void givenNoUser_whenGetUserById_thenThrowEntityNotFoundException() {
        UUID id = user.getId();

        given(userRepository.findById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> userService.getUserBy(id));
        verify(modelMapper, never()).map(any(User.class), eq(UserGetDto.class));
    }


    @Test
    void givenListOfUsers_whenGetAllUsers_thenReturnListOfUserGetDto() {
        User user1 = new User();
        List<User> users = List.of(user, user1);

        PageRequest testPage = PageRequest.of(1, 1);

        given(userRepository.findAll(testPage))
                .willReturn(new PageImpl<>(users, testPage, users.size()));
        given(modelMapper.map(any(User.class), eq(UserGetDto.class)))
                .willReturn(new UserGetDto());

        assertThat(userService.getAllUsers(1, 1))
                .hasSize(users.size());
    }

    @Test
    void givenUser_whenUpdateUser_thenReturnUserGetDto() {
        UserUpdateDto updateDto = new UserUpdateDto();
        UUID id = user.getId();

        given(userRepository.findById(id))
                .willReturn(Optional.of(user));
        doNothing().when(modelMapper).map(updateDto, user);
        given(modelMapper.map(user, UserGetDto.class))
                .willReturn(new UserGetDto());

        assertThat(userService.updateUser(updateDto, id))
                .isInstanceOf(UserGetDto.class);
    }

    @Test
    void givenNoUser_whenUpdateUser_thenThrowEntityNotFoundException() {
        UserUpdateDto updateDto = new UserUpdateDto();
        UUID id = user.getId();

        given(userRepository.findById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> userService.updateUser(updateDto, id));
    }

    @Test
    void whenUpdatePassword() {
        UUID id = user.getId();

        given(passwordEncoder.encode(STRING_PLACEHOLDER))
                .willReturn(STRING_PLACEHOLDER);

        userService.updatePassword(STRING_PLACEHOLDER, id);

        verify(userRepository, times(1))
                .updatePasswordById(id, STRING_PLACEHOLDER);
    }

    @Test
    void whenDeleteUser() {
        UUID id = user.getId();

        userService.deleteUser(id);

        verify(userRepository, times(1)).deleteById(id);
    }
}
