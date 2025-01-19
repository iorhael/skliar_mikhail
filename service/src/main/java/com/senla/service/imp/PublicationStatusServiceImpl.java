package com.senla.service.imp;

import com.senla.dto.publicationStatus.PublicationStatusCreateDto;
import com.senla.dto.publicationStatus.PublicationStatusGetDto;
import com.senla.model.PublicationStatus;
import com.senla.repository.PublicationStatusRepository;
import com.senla.service.PublicationStatusService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.publicationStatus.PublicationStatusDeleteException;
import com.senla.service.exception.publicationStatus.PublicationStatusUpdateException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PublicationStatusServiceImpl implements PublicationStatusService {

    private final PublicationStatusRepository publicationStatusRepository;

    private final ModelMapper modelMapper;

    public PublicationStatusServiceImpl(PublicationStatusRepository publicationStatusRepository, ModelMapper modelMapper) {
        this.publicationStatusRepository = publicationStatusRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public PublicationStatusGetDto createPublicationStatus(PublicationStatusCreateDto publicationStatus) {
        PublicationStatus publicationStatusEntity = modelMapper.map(publicationStatus, PublicationStatus.class);

        PublicationStatus createdPulicationStatus = publicationStatusRepository.create(publicationStatusEntity);

        return modelMapper.map(createdPulicationStatus, PublicationStatusGetDto.class);
    }

    @Transactional
    @Override
    public PublicationStatusGetDto getPublicationStatusById(UUID id) {
        return publicationStatusRepository.findById(id)
                .map(publicationStatus -> modelMapper.map(publicationStatus, PublicationStatusGetDto.class))
                .orElseThrow(() -> new ServiceException("No publication status found"));
    }

    @Transactional
    @Override
    public List<PublicationStatusGetDto> getAllPublicationStatuses() {
        return publicationStatusRepository.findAll().stream()
                .map(publicationStatus -> modelMapper.map(publicationStatus, PublicationStatusGetDto.class))
                .toList();
    }

    @Transactional
    @Override
    public PublicationStatusGetDto updatePublicationStatus(PublicationStatusGetDto publicationStatus, UUID id) {
        PublicationStatus publicationStatusEntity = modelMapper.map(publicationStatus, PublicationStatus.class);

        return publicationStatusRepository.update(publicationStatusEntity, id)
                .map(p -> modelMapper.map(p, PublicationStatusGetDto.class))
                .orElseThrow(() -> new PublicationStatusUpdateException("Can't update publication status"));
    }

    @Transactional
    @Override
    public PublicationStatusGetDto deletePublicationStatus(UUID id) {
        return publicationStatusRepository.deleteById(id)
                .map(pollOption -> modelMapper.map(pollOption, PublicationStatusGetDto.class))
                .orElseThrow(() -> new PublicationStatusDeleteException("Can't delete publication status"));
    }
}
