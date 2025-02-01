package com.senla.service.imp;

import com.senla.dto.tag.TagDto;
import com.senla.model.Tag;
import com.senla.repository.TagRepository;
import com.senla.service.TagService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    public static final String TAG_NOT_FOUND = "Tag not found";

    private final TagRepository tagRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public TagDto createTag(TagDto tagDto) {
        Tag tag = modelMapper.map(tagDto, Tag.class);

        tagRepository.save(tag);

        return modelMapper.map(tag, TagDto.class);
    }

    @Override
    public TagDto getTagById(UUID id) {
        return tagRepository.findById(id)
                .map(tag -> modelMapper.map(tag, TagDto.class))
                .orElseThrow(() -> new EntityNotFoundException(TAG_NOT_FOUND));
    }

    @Override
    public List<TagDto> getAllTags() {
        return tagRepository.findAll()
                .stream()
                .map(subscription -> modelMapper.map(subscription, TagDto.class))
                .toList();
    }

    @Override
    @Transactional
    public TagDto updateTag(TagDto tagDto, UUID id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(TAG_NOT_FOUND));

        tag.setName(tagDto.getName());

        return modelMapper.map(tag, TagDto.class);
    }

    @Override
    public void deleteTag(UUID id) {
        tagRepository.deleteById(id);
    }
}
