package com.senla.repository;

import com.senla.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByUsernameContainingIgnoreCase(String username);

    Optional<User> findByEmail(String email);
}
