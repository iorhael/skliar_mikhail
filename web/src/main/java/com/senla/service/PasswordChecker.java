package com.senla.service;

import com.senla.model.User;
import com.senla.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordChecker {
    private static final String USER_NOT_FOUND = "User not found";

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public boolean isPasswordConfirmed(String password, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));

        return passwordEncoder.matches(password, user.getPassword());
    }
}
