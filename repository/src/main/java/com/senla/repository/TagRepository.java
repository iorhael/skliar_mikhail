package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Tag;
import com.senla.util.SessionManager;
import org.hibernate.Session;

import java.util.Optional;
import java.util.UUID;

@Component
public class TagRepository extends BaseRepository<Tag, UUID> {
    public TagRepository() {
        super(Tag.class);
    }

    @Override
    public Optional<Tag> update(Tag tag, UUID id) {
        Optional<Tag> result = Optional.empty();

        try (Session session = SessionManager.openSession()) {
            session.beginTransaction();

            Tag existingTag = session.get(Tag.class, id);

            if (existingTag != null) {
                existingTag.setName(tag.getName());

                result = Optional.of(existingTag);
            }

            session.getTransaction().commit();
        }
        return result;
    }
}
