package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.User;
import com.senla.util.SessionManager;
import org.hibernate.Session;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepository extends BaseRepository<User, UUID> {
    public UserRepository() {
        super(User.class);
    }

    @Override
    public Optional<User> update(User user, UUID id) {
        Optional<User> result = Optional.empty();

        try (Session session = SessionManager.openSession()) {
            session.beginTransaction();

            User existingUser = session.get(User.class, id);

            if (existingUser != null) {
                existingUser.setUsername(user.getUsername());
                existingUser.setEmail(user.getEmail());
                existingUser.setPassword(user.getPassword());

                result = Optional.of(existingUser);
            }

            session.getTransaction().commit();
        }
        return result;
    }
}
