package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.publicationStatus.PublicationStatusCreateDto;
import com.senla.dto.publicationStatus.PublicationStatusGetDto;
import com.senla.model.PublicationStatus;
import com.senla.repository.PublicationStatusRepository;
import com.senla.service.PublicationStatusService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.publicationStatus.PublicationStatusDeleteException;
import com.senla.service.exception.publicationStatus.PublicationStatusUpdateException;
import com.senla.util.ModelMapperUtil;

import java.util.List;
import java.util.UUID;

@Component
public class PublicationStatusServiceImpl implements PublicationStatusService {

    @Autowired
    private PublicationStatusRepository publicationStatusRepository;

    @Override
    public PublicationStatusGetDto createPublicationStatus(PublicationStatusCreateDto publicationStatus) {
        PublicationStatus publicationStatusEntity = ModelMapperUtil.MODEL_MAPPER.map(publicationStatus, PublicationStatus.class);
        PublicationStatus createdPulicationStatus = publicationStatusRepository.create(publicationStatusEntity);

        return ModelMapperUtil.MODEL_MAPPER.map(createdPulicationStatus, PublicationStatusGetDto.class);
    }

    @Override
    public PublicationStatusGetDto getPublicationStatusById(UUID id) {
        return publicationStatusRepository.findById(id)
                .map(publicationStatus -> ModelMapperUtil.MODEL_MAPPER.map(publicationStatus, PublicationStatusGetDto.class))
                .orElseThrow(() -> new ServiceException("No publication status found"));
    }

    @Override
    public List<PublicationStatusGetDto> getAllPublicationStatuses() {
        return publicationStatusRepository.findAll().stream()
                .map(publicationStatus -> ModelMapperUtil.MODEL_MAPPER.map(publicationStatus, PublicationStatusGetDto.class))
                .toList();
    }

    @Override
    public PublicationStatusGetDto updatePublicationStatus(PublicationStatusGetDto publicationStatus, UUID id) {
        PublicationStatus publicationStatusEntity = ModelMapperUtil.MODEL_MAPPER.map(publicationStatus, PublicationStatus.class);

        return publicationStatusRepository.update(publicationStatusEntity, id)
                .map(p -> ModelMapperUtil.MODEL_MAPPER.map(p, PublicationStatusGetDto.class))
                .orElseThrow(() -> new PublicationStatusUpdateException("Can't update publication status"));
    }

    @Override
    public PublicationStatusGetDto deletePublicationStatus(UUID id) {
        return publicationStatusRepository.deleteById(id)
                .map(pollOption -> ModelMapperUtil.MODEL_MAPPER.map(pollOption, PublicationStatusGetDto.class))
                .orElseThrow(() -> new PublicationStatusDeleteException("Can't delete publication status"));
    }
}
