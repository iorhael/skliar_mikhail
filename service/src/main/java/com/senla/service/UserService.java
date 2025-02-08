package com.senla.service;

import com.senla.dto.user.UserCreateDto;
import com.senla.dto.user.UserGetDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserGetDto createUser(UserCreateDto user);

    UserGetDto getUserBy(UUID id);

    UserGetDto getUserBy(String email);

    List<UserGetDto> getAllUsers(int pageNo, int pageSize);

    List<UserGetDto> getUsersBy(String username);

    UserGetDto updateUser(UserCreateDto user, UUID id);

    void deleteUser(UUID id);
}
