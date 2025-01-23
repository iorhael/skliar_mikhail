package com.senla.repository;

import com.senla.model.PollOption;
import com.senla.model.User;
import com.senla.model.Vote;
import com.senla.model.VoteId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class VoteRepository extends BaseRepository<Vote, VoteId> {

    public VoteRepository() {
        super(Vote.class);
    }

    @Override
    public Vote create(Vote vote) {
        User persistedOwner = entityManager.getReference(User.class, vote.getOwner().getId());
        PollOption persistedPollOption = entityManager.getReference(PollOption.class, vote.getPollOption().getId());

        vote.setOwner(persistedOwner);
        vote.setPollOption(persistedPollOption);

        entityManager.persist(vote);

        return vote;
    }

    @Override
    public Optional<Vote> update(Vote vote, VoteId id) {
        Vote existingVote = entityManager.find(Vote.class, id);

        if (existingVote != null) {
            existingVote.setPollOption(vote.getPollOption());
        }

        return Optional.ofNullable(existingVote);
    }
}
