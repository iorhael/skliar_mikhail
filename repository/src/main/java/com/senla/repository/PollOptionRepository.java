package com.senla.repository;

import com.senla.model.PollOption;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PollOptionRepository extends JpaRepository<PollOption, UUID> {

    @EntityGraph("pollOption-with-votes")
    List<PollOption> findByPollId(UUID pollId);

    @EntityGraph("pollOption-with-votes")
    Optional<PollOption> findWithVotesById(UUID id);
}
