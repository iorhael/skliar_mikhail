package com.senla.repository;

import com.senla.model.Poll;
import com.senla.model.PollOption;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PollOptionRepository extends BaseRepository<PollOption, UUID> {

    public PollOptionRepository() {
        super(PollOption.class);
    }

    @Override
    public PollOption create(PollOption pollOption) {
        Poll persistedPoll = entityManager.getReference(Poll.class, pollOption.getPoll().getId());

        pollOption.setPoll(persistedPoll);

        entityManager.persist(pollOption);

        return pollOption;
    }

    @Override
    public Optional<PollOption> update(PollOption pollOption, UUID id) {
        PollOption existingPollOption = entityManager.find(PollOption.class, id);

        if (existingPollOption != null) {
            existingPollOption.setDescription(pollOption.getDescription());
        }

        return Optional.ofNullable(existingPollOption);
    }
}
