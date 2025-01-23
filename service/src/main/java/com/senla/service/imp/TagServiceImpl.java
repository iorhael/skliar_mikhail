package com.senla.service.imp;

import com.senla.dto.tag.TagDto;
import com.senla.model.Tag;
import com.senla.repository.TagRepository;
import com.senla.service.TagService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.tag.TagDeleteException;
import com.senla.service.exception.tag.TagUpdateException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public TagDto createTag(TagDto subscription) {
        Tag tagEntity = modelMapper.map(subscription, Tag.class);

        Tag createdTag = tagRepository.create(tagEntity);

        return modelMapper.map(createdTag, TagDto.class);
    }

    @Transactional
    @Override
    public TagDto getTagById(UUID id) {
        return tagRepository.findById(id)
                .map(tag -> modelMapper.map(tag, TagDto.class))
                .orElseThrow(() -> new ServiceException("Tag not found"));
    }

    @Transactional
    @Override
    public List<TagDto> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tag -> modelMapper.map(tag, TagDto.class))
                .toList();
    }

    @Transactional
    @Override
    public TagDto updateTag(TagDto subscription, UUID id) {
        Tag tagEntity = modelMapper.map(subscription, Tag.class);

        return tagRepository.update(tagEntity, id)
                .map(t -> modelMapper.map(t, TagDto.class))
                .orElseThrow(() -> new TagUpdateException("Can't update tag"));
    }

    @Transactional
    @Override
    public TagDto deleteTag(UUID id) {
        return tagRepository.deleteById(id)
                .map(subscription -> modelMapper.map(subscription, TagDto.class))
                .orElseThrow(() -> new TagDeleteException("Can't delete tag"));
    }
}
