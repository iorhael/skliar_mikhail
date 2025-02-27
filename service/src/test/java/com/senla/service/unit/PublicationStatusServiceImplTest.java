package com.senla.service.unit;

import com.senla.dto.publicationStatus.PublicationStatusCreateDto;
import com.senla.dto.publicationStatus.PublicationStatusGetDto;
import com.senla.dto.publicationStatus.PublicationStatusUpdateDto;
import com.senla.model.Post;
import com.senla.model.PublicationStatus;
import com.senla.repository.PostRepository;
import com.senla.repository.PublicationStatusRepository;
import com.senla.service.imp.PublicationStatusServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublicationStatusServiceImplTest {

    @Mock
    PublicationStatusRepository publicationStatusRepository;

    @Mock
    PostRepository postRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    PublicationStatusServiceImpl publicationStatusService;

    PublicationStatus publicationStatus;

    @BeforeEach
    void setup() {
        publicationStatus = new PublicationStatus();
        publicationStatus.setId(UUID.randomUUID());
    }

    @Test
    void whenCreatePublicationStatus_thenReturnPublicationStatusGetDto() {
        PublicationStatusCreateDto createDto = new PublicationStatusCreateDto();
        createDto.setPostId(UUID.randomUUID());

        given(modelMapper.map(createDto, com.senla.model.PublicationStatus.class))
                .willReturn(publicationStatus);
        given(postRepository.getReferenceById(createDto.getPostId()))
                .willReturn(new Post());
        given(modelMapper.map(publicationStatus, PublicationStatusGetDto.class))
                .willReturn(new PublicationStatusGetDto());

        assertThat(publicationStatusService.createPublicationStatus(createDto))
                .isInstanceOf(PublicationStatusGetDto.class);
        assertNotNull(publicationStatus.getPost());
    }

    @Test
    void givenPublicationStatus_whenGetPublicationStatusById_thenReturnPublicationStatusGetDto() {
        UUID id = publicationStatus.getId();

        given(publicationStatusRepository.findWithPostById(id))
                .willReturn(Optional.of(publicationStatus));
        given(modelMapper.map(publicationStatus, PublicationStatusGetDto.class))
                .willReturn(new PublicationStatusGetDto());

        assertThat(publicationStatusService.getPublicationStatusBy(id))
                .isInstanceOf(PublicationStatusGetDto.class);
    }

    @Test
    void givenNoPublicationStatus_whenGetPublicationStatusById_thenThrowEntityNotFoundException() {
        UUID id = publicationStatus.getId();

        given(publicationStatusRepository.findWithPostById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> publicationStatusService.getPublicationStatusBy(id));
        verify(modelMapper, never()).map(any(com.senla.model.PublicationStatus.class), eq(PublicationStatusGetDto.class));
    }

    @Test
    void givenListOfPublicationStatuses_whenGetAllPublicationStatuss_thenReturnListOfPublicationStatusGetDto() {
        PublicationStatus publicationStatus1 = new PublicationStatus();
        List<PublicationStatus> pollOptions = List.of(publicationStatus, publicationStatus1);

        given(publicationStatusRepository.findWithPostBy())
                .willReturn(pollOptions);
        given(modelMapper.map(any(PublicationStatus.class), eq(PublicationStatusGetDto.class)))
                .willReturn(new PublicationStatusGetDto());

        assertThat(publicationStatusService.getAllPublicationStatuses())
                .hasSize(pollOptions.size());
    }

    @Test
    void givenPublicationStatus_whenUpdatePublicationStatus_thenReturnPublicationStatusGetDto() {
        PublicationStatusUpdateDto updateDto = new PublicationStatusUpdateDto();
        UUID id = publicationStatus.getId();

        given(publicationStatusRepository.findWithPostById(id))
                .willReturn(Optional.of(publicationStatus));
        doNothing().when(modelMapper).map(updateDto, publicationStatus);
        given(modelMapper.map(publicationStatus, PublicationStatusGetDto.class))
                .willReturn(new PublicationStatusGetDto());

        assertThat(publicationStatusService.updatePublicationStatus(updateDto, id))
                .isInstanceOf(PublicationStatusGetDto.class);
    }

    @Test
    void givenNoPublicationStatus_whenUpdatePublicationStatus_thenThrowEntityNotFoundException() {
        PublicationStatusUpdateDto updateDto = new PublicationStatusUpdateDto();
        UUID id = publicationStatus.getId();

        given(publicationStatusRepository.findWithPostById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> publicationStatusService.updatePublicationStatus(updateDto, id));
    }

    @Test
    void whenDeletePublicationStatus() {
        UUID id = publicationStatus.getId();

        publicationStatusService.deletePublicationStatus(id);

        verify(publicationStatusRepository, times(1)).deleteById(id);
    }
}
