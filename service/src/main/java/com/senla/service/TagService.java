package com.senla.service;

import com.senla.dto.tag.TagDto;

import java.util.List;
import java.util.UUID;

public interface TagService {
    TagDto createTag(TagDto tag);

    TagDto getTagBy(UUID id);

    List<TagDto> getAllTags();

    TagDto updateTag(TagDto tag, UUID id);

    void deleteTag(UUID id);
}
