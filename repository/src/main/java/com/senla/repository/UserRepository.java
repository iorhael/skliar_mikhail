package com.senla.repository;

import com.senla.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository extends BaseRepository<User, UUID> {

    public UserRepository() {
        super(User.class);
    }

    @Override
    public Optional<User> update(User user, UUID id) {
        User existingUser = entityManager.find(User.class, id);

        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
        }

        return Optional.ofNullable(existingUser);
    }
}
