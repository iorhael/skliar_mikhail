package com.senla.repository;

import com.senla.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByUsernameContainingIgnoreCase(String username, Pageable pageable);

    @EntityGraph("user-with-roles")
    Optional<User> findWithRolesByUsernameOrEmail(String username, String email);

    Optional<User> findByUsernameOrEmail(String username, String email);
}
