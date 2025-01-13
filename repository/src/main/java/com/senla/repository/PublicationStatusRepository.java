package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.PublicationStatus;
import com.senla.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.UUID;

@Component
public class PublicationStatusRepository extends BaseRepository<PublicationStatus, UUID> {
    public PublicationStatusRepository() {
        super(PublicationStatus.class);
    }

    @Override
    public Optional<PublicationStatus> update(PublicationStatus publicationStatus, UUID id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            PublicationStatus existingPublicationStatus = entityManager.find(PublicationStatus.class, id);

            if (existingPublicationStatus != null) {
                existingPublicationStatus.setStatusName(publicationStatus.getStatusName());
                existingPublicationStatus.setScheduledDate(publicationStatus.getScheduledDate());
            }

            entityManager.getTransaction().commit();

            return Optional.ofNullable(existingPublicationStatus);
        }
    }
}
