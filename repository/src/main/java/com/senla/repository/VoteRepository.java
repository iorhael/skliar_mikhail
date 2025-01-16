package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.PollOption;
import com.senla.model.User;
import com.senla.model.Vote;
import com.senla.model.VoteId;
import com.senla.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.Optional;

@Component
public class VoteRepository extends BaseRepository<Vote, VoteId> {
    public VoteRepository() {
        super(Vote.class);
    }

    @Override
    public Vote create(Vote vote) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            User persistedOwner = entityManager.getReference(User.class, vote.getOwner().getId());
            PollOption persistedPollOption = entityManager.getReference(PollOption.class, vote.getPollOption().getId());

            vote.setOwner(persistedOwner);
            vote.setPollOption(persistedPollOption);

            entityManager.persist(vote);

            entityManager.getTransaction().commit();
        }
        return vote;
    }

    @Override
    public Optional<Vote> update(Vote vote, VoteId id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            Vote existingVote = entityManager.find(Vote.class, id);

            if (existingVote != null) {
                existingVote.setPollOption(vote.getPollOption());
            }

            entityManager.getTransaction().commit();

            return Optional.ofNullable(existingVote);
        }
    }
}
