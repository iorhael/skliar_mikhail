package com.senla.api.service;

import com.senla.api.model.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagService {
    Optional<Tag> createTag(Tag tag);

    Optional<Tag> getTagById(UUID id);

    List<Tag> getAllTags();

    Optional<Tag> updateTag(Tag tag, UUID id);

    Optional<Tag> deleteTag(UUID id);
}
