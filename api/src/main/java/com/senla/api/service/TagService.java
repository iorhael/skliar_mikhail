package com.senla.api.service;

import com.senla.api.model.Tag;

import java.util.List;
import java.util.UUID;

public interface TagService {
    Tag createTag(Tag tag);

    Tag getTagById(UUID id);

    List<Tag> getAllTags();

    Tag updateTag(Tag tag, UUID id);

    Tag deleteTag(UUID id);
}
