package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.publicationStatus.PublicationStatusDto;
import com.senla.model.PublicationStatus;
import com.senla.repository.PublicationStatusRepository;
import com.senla.repository.exception.PublicationStatusNotFoundException;
import com.senla.service.PublicationStatusService;
import com.senla.service.exception.publicationStatus.PublicationStatusCreateException;
import com.senla.service.exception.publicationStatus.PublicationStatusDeleteException;
import com.senla.service.exception.publicationStatus.PublicationStatusUpdateException;
import com.senla.util.ModelMapperUtil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PublicationStatusServiceImpl implements PublicationStatusService {
    @Autowired
    private PublicationStatusRepository publicationStatusRepository;

    @Override
    public PublicationStatusDto createPublicationStatus(PublicationStatusDto publicationStatus) {
        PublicationStatus publicationStatusEntity = ModelMapperUtil.MODEL_MAPPER.map(publicationStatus, PublicationStatus.class);

        return publicationStatusRepository.create(publicationStatusEntity)
                .map(p -> ModelMapperUtil.MODEL_MAPPER.map(p, PublicationStatusDto.class))
                .orElseThrow(() -> new PublicationStatusCreateException("Can't create publication status"));
    }

    @Override
    public PublicationStatusDto getPublicationStatusById(UUID id) {
        return publicationStatusRepository.getById(id)
                .map(publicationStatus -> ModelMapperUtil.MODEL_MAPPER.map(publicationStatus, PublicationStatusDto.class))
                .orElseThrow(() -> new PublicationStatusNotFoundException("No publication status found"));
    }

    @Override
    public List<PublicationStatusDto> getAllPublicationStatuses() {
        return publicationStatusRepository.getAll().stream()
                .map(publicationStatus -> ModelMapperUtil.MODEL_MAPPER.map(publicationStatus, PublicationStatusDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PublicationStatusDto updatePublicationStatus(PublicationStatusDto publicationStatus, UUID id) {
        PublicationStatus publicationStatusEntity = ModelMapperUtil.MODEL_MAPPER.map(publicationStatus, PublicationStatus.class);

        return publicationStatusRepository.update(publicationStatusEntity, id)
                .map(p -> ModelMapperUtil.MODEL_MAPPER.map(p, PublicationStatusDto.class))
                .orElseThrow(() -> new PublicationStatusUpdateException("Can't update publication status"));
    }

    @Override
    public PublicationStatusDto deletePublicationStatus(UUID id) {
        return publicationStatusRepository.delete(id)
                .map(pollOption -> ModelMapperUtil.MODEL_MAPPER.map(pollOption, PublicationStatusDto.class))
                .orElseThrow(() -> new PublicationStatusDeleteException("Can't delete publication status"));
    }
}
