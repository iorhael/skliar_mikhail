package com.senla.api.service.imp;

import com.senla.api.dao.PublicationStatusDao;
import com.senla.api.dao.exception.PublicationStatusNotFoundException;
import com.senla.api.model.PublicationStatus;
import com.senla.api.service.PublicationStatusService;
import com.senla.api.service.exception.publicationStatus.PublicationStatusCreateException;
import com.senla.api.service.exception.publicationStatus.PublicationStatusDeleteException;
import com.senla.api.service.exception.publicationStatus.PublicationStatusUpdateException;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PublicationStatusServiceImpl implements PublicationStatusService {
    @Autowired
    private PublicationStatusDao publicationStatusDao;

    @Override
    public PublicationStatus createPublicationStatus(PublicationStatus publicationStatus) {
        return publicationStatusDao.create(publicationStatus).orElseThrow(() -> new PublicationStatusCreateException("Can't create publication status"));
    }

    @Override
    public PublicationStatus getPublicationStatusById(UUID id) {
        return publicationStatusDao.getById(id).orElseThrow(() -> new PublicationStatusNotFoundException("No publication status found"));
    }

    @Override
    public List<PublicationStatus> getAllPublicationStatuses() {
        return publicationStatusDao.getAll();
    }

    @Override
    public PublicationStatus updatePublicationStatus(PublicationStatus publicationStatus, UUID id) {
        return publicationStatusDao.update(publicationStatus, id).orElseThrow(() -> new PublicationStatusUpdateException("Can't update publication status"));
    }

    @Override
    public PublicationStatus deletePublicationStatus(UUID id) {
        return publicationStatusDao.delete(id).orElseThrow(() -> new PublicationStatusDeleteException("Can't update publication status"));
    }
}
