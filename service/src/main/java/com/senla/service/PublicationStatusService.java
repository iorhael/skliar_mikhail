package com.senla.service;

import com.senla.dto.publicationStatus.PublicationStatusDto;

import java.util.List;
import java.util.UUID;

public interface PublicationStatusService {
    PublicationStatusDto createPublicationStatus(PublicationStatusDto publicationStatus);

    PublicationStatusDto getPublicationStatusById(UUID id);

    List<PublicationStatusDto> getAllPublicationStatuses();

    PublicationStatusDto updatePublicationStatus(PublicationStatusDto publicationStatus, UUID id);

    PublicationStatusDto deletePublicationStatus(UUID id);
}
