package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Tag;
import com.senla.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.UUID;

@Component
public class TagRepository extends BaseRepository<Tag, UUID> {
    public TagRepository() {
        super(Tag.class);
    }

    @Override
    public Optional<Tag> update(Tag tag, UUID id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            Tag existingTag = entityManager.find(Tag.class, id);

            if (existingTag != null) {
                existingTag.setName(tag.getName());
            }

            entityManager.getTransaction().commit();

            return Optional.ofNullable(existingTag);
        }
    }
}
