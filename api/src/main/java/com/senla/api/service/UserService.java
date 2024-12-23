package com.senla.api.service;

import com.senla.api.dto.user.UserCreateDto;
import com.senla.api.dto.user.UserGetDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserGetDto createUser(UserCreateDto user);

    UserGetDto getUserById(UUID id);

    List<UserGetDto> getAllUsers();

    UserGetDto updateUser(UserCreateDto user, UUID id);

    UserGetDto deleteUser(UUID id);
}
