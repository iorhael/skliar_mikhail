package com.senla.api.service;

import com.senla.api.model.PublicationStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublicationStatusService {
    Optional<PublicationStatus> createPublicationStatus(PublicationStatus publicationStatus);

    Optional<PublicationStatus> getPublicationStatusById(UUID id);

    List<PublicationStatus> getAllPublicationStatuses();

    Optional<PublicationStatus> updatePublicationStatus(PublicationStatus publicationStatus, UUID id);

    Optional<PublicationStatus> deletePublicationStatus(UUID id);
}
