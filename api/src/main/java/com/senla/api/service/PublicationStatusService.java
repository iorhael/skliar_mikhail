package com.senla.api.service;

import com.senla.api.model.PublicationStatus;

import java.util.List;
import java.util.UUID;

public interface PublicationStatusService {
    PublicationStatus createPublicationStatus(PublicationStatus publicationStatus);

    PublicationStatus getPublicationStatusById(UUID id);

    List<PublicationStatus> getAllPublicationStatuses();

    PublicationStatus updatePublicationStatus(PublicationStatus publicationStatus, UUID id);

    PublicationStatus deletePublicationStatus(UUID id);
}
