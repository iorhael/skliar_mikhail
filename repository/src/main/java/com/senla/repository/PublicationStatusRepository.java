package com.senla.repository;

import com.senla.model.PublicationStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublicationStatusRepository extends JpaRepository<PublicationStatus, UUID> {

    @EntityGraph("publicationStatus-with-post")
    Optional<PublicationStatus> findWithPostById(UUID id);

    @EntityGraph("publicationStatus-with-post")
    List<PublicationStatus> findWithPostBy();
}
