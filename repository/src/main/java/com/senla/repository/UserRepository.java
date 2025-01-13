package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.User;
import com.senla.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepository extends BaseRepository<User, UUID> {
    public UserRepository() {
        super(User.class);
    }

    @Override
    public Optional<User> update(User user, UUID id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            User existingUser = entityManager.find(User.class, id);

            if (existingUser != null) {
                existingUser.setUsername(user.getUsername());
                existingUser.setEmail(user.getEmail());
                existingUser.setPassword(user.getPassword());
            }

            entityManager.getTransaction().commit();

            return Optional.ofNullable(existingUser);
        }
    }
}
