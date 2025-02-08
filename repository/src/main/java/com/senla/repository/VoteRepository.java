package com.senla.repository;

import com.senla.model.Vote;
import com.senla.model.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, VoteId> {
}
