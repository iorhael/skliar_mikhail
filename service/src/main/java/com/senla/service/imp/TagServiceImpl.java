package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.tag.TagDto;
import com.senla.model.Tag;
import com.senla.repository.TagRepository;
import com.senla.service.TagService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.tag.TagDeleteException;
import com.senla.service.exception.tag.TagUpdateException;
import com.senla.util.ModelMapperUtil;

import java.util.List;
import java.util.UUID;

@Component
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public TagDto createTag(TagDto subscription) {
        Tag tagEntity = ModelMapperUtil.MODEL_MAPPER.map(subscription, Tag.class);
        Tag createdTag = tagRepository.create(tagEntity);

        return ModelMapperUtil.MODEL_MAPPER.map(createdTag, TagDto.class);
    }

    @Override
    public TagDto getTagById(UUID id) {
        return tagRepository.findById(id)
                .map(tag -> ModelMapperUtil.MODEL_MAPPER.map(tag, TagDto.class))
                .orElseThrow(() -> new ServiceException("Tag not found"));
    }

    @Override
    public List<TagDto> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tag -> ModelMapperUtil.MODEL_MAPPER.map(tag, TagDto.class))
                .toList();
    }

    @Override
    public TagDto updateTag(TagDto subscription, UUID id) {
        Tag tagEntity = ModelMapperUtil.MODEL_MAPPER.map(subscription, Tag.class);

        return tagRepository.update(tagEntity, id)
                .map(t -> ModelMapperUtil.MODEL_MAPPER.map(t, TagDto.class))
                .orElseThrow(() -> new TagUpdateException("Can't update tag"));
    }

    @Override
    public TagDto deleteTag(UUID id) {
        return tagRepository.deleteById(id)
                .map(subscription -> ModelMapperUtil.MODEL_MAPPER.map(subscription, TagDto.class))
                .orElseThrow(() -> new TagDeleteException("Can't delete tag"));
    }
}
