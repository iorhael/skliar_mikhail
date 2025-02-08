package com.senla.service.imp;

import com.senla.aspect.Benchmarked;
import com.senla.dto.publicationStatus.PublicationStatusCreateDto;
import com.senla.dto.publicationStatus.PublicationStatusGetDto;
import com.senla.dto.publicationStatus.PublicationStatusUpdateDto;
import com.senla.model.Post;
import com.senla.model.PublicationStatus;
import com.senla.repository.PostRepository;
import com.senla.repository.PublicationStatusRepository;
import com.senla.service.PublicationStatusService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Benchmarked
@RequiredArgsConstructor
public class PublicationStatusServiceImpl implements PublicationStatusService {
    public static final String PUBLICATION_STATUS_NOT_FOUND = "PublicationStatus not found";

    private final PublicationStatusRepository publicationStatusRepository;

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public PublicationStatusGetDto createPublicationStatus(PublicationStatusCreateDto publicationStatusCreateDto) {
        PublicationStatus publicationStatus = modelMapper.map(publicationStatusCreateDto, PublicationStatus.class);

        Post post = postRepository.getReferenceById(publicationStatusCreateDto.getPostId());
        publicationStatus.setPost(post);

        publicationStatusRepository.save(publicationStatus);

        return modelMapper.map(publicationStatus, PublicationStatusGetDto.class);
    }

    @Override
    public PublicationStatusGetDto getPublicationStatusBy(UUID id) {
        return publicationStatusRepository.findWithPostById(id)
                .map(publicationStatus -> modelMapper.map(publicationStatus, PublicationStatusGetDto.class))
                .orElseThrow(() -> new EntityNotFoundException(PUBLICATION_STATUS_NOT_FOUND));
    }

    @Override
    public List<PublicationStatusGetDto> getAllPublicationStatuses() {
        return publicationStatusRepository.findWithPostBy()
                .stream()
                .map(publicationStatus -> modelMapper.map(publicationStatus, PublicationStatusGetDto.class))
                .toList();
    }

    @Override
    @Transactional
    public PublicationStatusGetDto updatePublicationStatus(PublicationStatusUpdateDto publicationStatusUpdateDto, UUID id) {
        PublicationStatus publicationStatus = publicationStatusRepository.findWithPostById(id)
                .orElseThrow(() -> new EntityNotFoundException(PUBLICATION_STATUS_NOT_FOUND));

        modelMapper.map(publicationStatusUpdateDto, publicationStatus);

        return modelMapper.map(publicationStatus, PublicationStatusGetDto.class);
    }

    @Override
    public void deletePublicationStatus(UUID id) {
        publicationStatusRepository.deleteById(id);
    }
}
