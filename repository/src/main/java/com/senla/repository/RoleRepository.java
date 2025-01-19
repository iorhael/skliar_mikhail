package com.senla.repository;

import com.senla.model.Role;
import com.senla.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class RoleRepository extends BaseRepository<Role, UUID> {

    public RoleRepository() {
        super(Role.class);
    }

    @Override
    public Role create(Role role) {
        User persistedUser = entityManager.getReference(User.class, role.getUser().getId());

        role.setUser(persistedUser);

        entityManager.persist(role);

        return role;
    }

    @Override
    public Optional<Role> update(Role role, UUID id) {
        Role existingRole = entityManager.find(Role.class, id);

        if (existingRole != null) {
            existingRole.setName(role.getName());
        }

        return Optional.ofNullable(existingRole);
    }
}
