package com.senla.repository;

import com.senla.model.PublicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublicationStatusRepository extends JpaRepository<PublicationStatus, UUID> {
}
