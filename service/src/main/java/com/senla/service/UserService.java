package com.senla.service;

import com.senla.dto.user.UserCreateDto;
import com.senla.dto.user.UserGetDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserGetDto createUser(UserCreateDto user);

    UserGetDto getUserById(UUID id);

    List<UserGetDto> getAllUsers();

    UserGetDto updateUser(UserCreateDto user, UUID id);

    void deleteUser(UUID id);
}
