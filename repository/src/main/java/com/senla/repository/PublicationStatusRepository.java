package com.senla.repository;

import com.senla.model.Post;
import com.senla.model.PublicationStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PublicationStatusRepository extends BaseRepository<PublicationStatus, UUID> {

    public PublicationStatusRepository() {
        super(PublicationStatus.class);
    }

    @Override
    public PublicationStatus create(PublicationStatus publicationStatus) {
        Post persistedPost = entityManager.getReference(Post.class, publicationStatus.getPost().getId());

        publicationStatus.setPost(persistedPost);

        entityManager.persist(publicationStatus);

        return publicationStatus;
    }

    @Override
    public Optional<PublicationStatus> update(PublicationStatus publicationStatus, UUID id) {
        PublicationStatus existingPublicationStatus = entityManager.find(PublicationStatus.class, id);

        if (existingPublicationStatus != null) {
            existingPublicationStatus.setStatusName(publicationStatus.getStatusName());
            existingPublicationStatus.setScheduledDate(publicationStatus.getScheduledDate());
        }

        return Optional.ofNullable(existingPublicationStatus);
    }
}
