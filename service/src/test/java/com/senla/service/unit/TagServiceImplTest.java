package com.senla.service.unit;

import com.senla.dto.tag.TagDto;
import com.senla.model.Tag;
import com.senla.repository.TagRepository;
import com.senla.service.imp.TagServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {
    static final String STRING_PLACEHOLDER = "Test";

    @Mock
    TagRepository tagRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    TagServiceImpl tagService;

    Tag tag;

    @BeforeEach
    void setup() {
        tag = new Tag();
        tag.setId(UUID.randomUUID());
    }

    @Test
    void whenCreateTag_thenReturnTagGetDto() {
        TagDto createDto = new TagDto();

        given(modelMapper.map(createDto, Tag.class))
                .willReturn(tag);
        given(modelMapper.map(tag, TagDto.class))
                .willReturn(new TagDto());

        assertThat(tagService.createTag(createDto))
                .isInstanceOf(TagDto.class);
    }

    @Test
    void givenTag_whenGetTagById_thenReturnTagGetDto() {
        UUID id = tag.getId();

        given(tagRepository.findById(id))
                .willReturn(Optional.of(tag));
        given(modelMapper.map(tag, TagDto.class))
                .willReturn(new TagDto());

        assertThat(tagService.getTagBy(id))
                .isInstanceOf(TagDto.class);
    }

    @Test
    void givenNoTag_whenGetTagById_thenThrowEntityNotFoundException() {
        UUID id = tag.getId();

        given(tagRepository.findById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> tagService.getTagBy(id));
        verify(modelMapper, never()).map(any(Tag.class), eq(TagDto.class));
    }

    @Test
    void givenListOfTags_whenGetAllTags_thenReturnListOfTagGetDto() {
        Tag tag1 = new Tag();
        List<Tag> tags = List.of(tag, tag1);

        given(tagRepository.findAll())
                .willReturn(tags);
        given(modelMapper.map(any(Tag.class), eq(TagDto.class)))
                .willReturn(new TagDto());

        assertThat(tagService.getAllTags())
                .hasSize(tags.size());
    }

    @Test
    void givenTag_whenUpdateTag_thenReturnTagGetDto() {
        TagDto updateDto = new TagDto();
        updateDto.setName(STRING_PLACEHOLDER);
        UUID id = tag.getId();

        given(tagRepository.findById(id))
                .willReturn(Optional.of(tag));
        given(modelMapper.map(tag, TagDto.class))
                .willReturn(new TagDto());

        assertThat(tagService.updateTag(updateDto, id))
                .isInstanceOf(TagDto.class);
        assertNotNull(tag.getName());
    }

    @Test
    void givenNoTag_whenUpdateTag_thenThrowEntityNotFoundException() {
        TagDto updateDto = new TagDto();
        UUID id = tag.getId();

        given(tagRepository.findById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> tagService.updateTag(updateDto, id));
    }

    @Test
    void whenDeleteTag() {
        UUID id = tag.getId();

        tagService.deleteTag(id);

        verify(tagRepository, times(1)).deleteById(id);

    }
}
