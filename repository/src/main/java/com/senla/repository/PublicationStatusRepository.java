package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.PublicationStatus;
import com.senla.util.SessionManager;
import org.hibernate.Session;

import java.util.Optional;
import java.util.UUID;

@Component
public class PublicationStatusRepository extends BaseRepository<PublicationStatus, UUID> {
    public PublicationStatusRepository() {
        super(PublicationStatus.class);
    }

    @Override
    public Optional<PublicationStatus> update(PublicationStatus publicationStatus, UUID id) {
        Optional<PublicationStatus> result = Optional.empty();

        try (Session session = SessionManager.openSession()) {
            session.beginTransaction();

            PublicationStatus existingPublicationStatus = session.get(PublicationStatus.class, id);

            if (existingPublicationStatus != null) {
                existingPublicationStatus.setStatusName(publicationStatus.getStatusName());
                existingPublicationStatus.setScheduledDate(publicationStatus.getScheduledDate());

                result = Optional.of(existingPublicationStatus);
            }

            session.getTransaction().commit();
        }
        return result;
    }
}
