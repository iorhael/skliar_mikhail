package com.senla.repository;

import com.senla.model.Tag;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class TagRepository extends BaseRepository<Tag, UUID> {

    public TagRepository() {
        super(Tag.class);
    }

    @Override
    public Optional<Tag> update(Tag tag, UUID id) {
        Tag existingTag = entityManager.find(Tag.class, id);

        if (existingTag != null) {
            existingTag.setName(tag.getName());
        }

        return Optional.ofNullable(existingTag);
    }
}
