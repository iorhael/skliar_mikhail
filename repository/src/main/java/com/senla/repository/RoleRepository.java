package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Role;
import com.senla.util.SessionManager;
import org.hibernate.Session;

import java.util.Optional;
import java.util.UUID;

@Component
public class RoleRepository extends BaseRepository<Role, UUID> {
    public RoleRepository() {
        super(Role.class);
    }

    @Override
    public Optional<Role> update(Role role, UUID id) {
        Optional<Role> result = Optional.empty();

        try (Session session = SessionManager.openSession()) {
            session.beginTransaction();

            Role existingRole = session.get(Role.class, id);

            if (existingRole != null) {
                existingRole.setName(role.getName());

                result = Optional.of(existingRole);
            }

            session.getTransaction().commit();
        }
        return result;
    }
}
