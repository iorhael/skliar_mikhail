package com.senla.service;

import com.senla.dto.publicationStatus.PublicationStatusCreateDto;
import com.senla.dto.publicationStatus.PublicationStatusGetDto;

import java.util.List;
import java.util.UUID;

public interface PublicationStatusService {
    PublicationStatusGetDto createPublicationStatus(PublicationStatusCreateDto publicationStatus);

    PublicationStatusGetDto getPublicationStatusById(UUID id);

    List<PublicationStatusGetDto> getAllPublicationStatuses();

    PublicationStatusGetDto updatePublicationStatus(PublicationStatusGetDto publicationStatus, UUID id);

    PublicationStatusGetDto deletePublicationStatus(UUID id);
}
