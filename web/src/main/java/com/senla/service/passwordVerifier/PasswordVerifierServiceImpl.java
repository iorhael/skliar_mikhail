package com.senla.service.passwordVerifier;

import com.senla.model.User;
import com.senla.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordVerifierServiceImpl implements PasswordVerifierService {
    private static final String USER_NOT_FOUND = "User not found";
    private static final String VERIFICATION_FAILED = "Password verification failed: password does not match";

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void verifyPassword(String password) {
        UUID userId = getUserIdFromAuthentication();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new IllegalStateException(VERIFICATION_FAILED);
    }

    private UUID getUserIdFromAuthentication() {
        String id = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return UUID.fromString(id);
    }
}
