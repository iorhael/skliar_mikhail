package com.senla.api.service;

import com.senla.api.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> createUser(User user);

    Optional<User> getUserById(UUID id);

    List<User> getAllUsers();

    Optional<User> updateUser(User user, UUID id);

    Optional<User> deleteUser(UUID id);
}
