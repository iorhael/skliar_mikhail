package com.senla.service;

import com.senla.dto.publicationStatus.PublicationStatusCreateDto;
import com.senla.dto.publicationStatus.PublicationStatusGetDto;
import com.senla.dto.publicationStatus.PublicationStatusUpdateDto;

import java.util.List;
import java.util.UUID;

public interface PublicationStatusService {
    PublicationStatusGetDto createPublicationStatus(PublicationStatusCreateDto publicationStatus);

    PublicationStatusGetDto getPublicationStatusBy(UUID id);

    List<PublicationStatusGetDto> getAllPublicationStatuses();

    PublicationStatusGetDto updatePublicationStatus(PublicationStatusUpdateDto publicationStatus, UUID id);

    void deletePublicationStatus(UUID id);
}
