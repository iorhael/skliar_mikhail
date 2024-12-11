package com.senla.api.service.imp;

import com.senla.api.dao.PublicationStatusDao;
import com.senla.api.model.PublicationStatus;
import com.senla.api.service.PublicationStatusService;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PublicationStatusServiceImpl implements PublicationStatusService {
    @Autowired
    private PublicationStatusDao publicationStatusDao;

    @Override
    public Optional<PublicationStatus> createPublicationStatus(PublicationStatus publicationStatus) {
        return publicationStatusDao.create(publicationStatus);
    }

    @Override
    public Optional<PublicationStatus> getPublicationStatusById(UUID id) {
        return publicationStatusDao.getById(id);
    }

    @Override
    public List<PublicationStatus> getAllPublicationStatuses() {
        return publicationStatusDao.getAll();
    }

    @Override
    public Optional<PublicationStatus> updatePublicationStatus(PublicationStatus publicationStatus, UUID id) {
        return publicationStatusDao.update(publicationStatus, id);
    }

    @Override
    public Optional<PublicationStatus> deletePublicationStatus(UUID id) {
        return publicationStatusDao.delete(id);
    }
}
