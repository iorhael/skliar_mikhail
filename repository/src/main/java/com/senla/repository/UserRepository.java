package com.senla.repository;

import com.senla.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByUsernameContainingIgnoreCase(String username, Pageable pageable);

    @EntityGraph("user-with-roles")
    Optional<User> findByUsernameOrEmail(String username, String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET password = :newPassword WHERE id = :id", nativeQuery = true)
    void updatePasswordById(
            @Param("id") UUID id,
            @Param("newPassword") String newPassword
    );
}
