package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Role;
import com.senla.model.User;
import com.senla.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.UUID;

@Component
public class RoleRepository extends BaseRepository<Role, UUID> {
    public RoleRepository() {
        super(Role.class);
    }

    @Override
    public Role create(Role role) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            User persistedUser = entityManager.getReference(User.class, role.getUser().getId());

            role.setUser(persistedUser);

            entityManager.persist(role);

            entityManager.getTransaction().commit();
        }
        return role;
    }

    @Override
    public Optional<Role> update(Role role, UUID id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            Role existingRole = entityManager.find(Role.class, id);

            if (existingRole != null) {
                existingRole.setName(role.getName());
            }

            entityManager.getTransaction().commit();

            return Optional.ofNullable(existingRole);
        }
    }
}
