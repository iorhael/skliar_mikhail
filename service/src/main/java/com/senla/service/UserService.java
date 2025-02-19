package com.senla.service;

import com.senla.dto.user.UserCreateDto;
import com.senla.dto.user.UserGetDto;
import com.senla.dto.user.UserUpdateDto;
import com.senla.dto.user.UserWithRolesDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserWithRolesDto createUser(UserCreateDto user);

    UserGetDto getUserBy(UUID id);

    List<UserGetDto> getAllUsers(int pageNo, int pageSize);

    List<UserGetDto> getUsersBy(String username, int pageNo, int pageSize);

    UserGetDto updateUser(UserUpdateDto user, UUID id);

    void updatePassword(String newPassword, UUID id);

    void deleteUser(UUID id);
}
