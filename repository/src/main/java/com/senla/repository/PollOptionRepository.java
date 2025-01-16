package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Poll;
import com.senla.model.PollOption;
import com.senla.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.UUID;

@Component
public class PollOptionRepository extends BaseRepository<PollOption, UUID> {
    public PollOptionRepository() {
        super(PollOption.class);
    }

    @Override
    public PollOption create(PollOption pollOption) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            Poll persistedPoll = entityManager.getReference(Poll.class, pollOption.getPoll().getId());

            pollOption.setPoll(persistedPoll);

            entityManager.persist(pollOption);

            entityManager.getTransaction().commit();
        }
        return pollOption;
    }

    @Override
    public Optional<PollOption> update(PollOption pollOption, UUID id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            PollOption existingPollOption = entityManager.find(PollOption.class, id);

            if (existingPollOption != null) {
                existingPollOption.setDescription(pollOption.getDescription());
            }

            entityManager.getTransaction().commit();

            return Optional.ofNullable(existingPollOption);
        }
    }
}
