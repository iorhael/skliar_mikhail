package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Poll;
import com.senla.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.UUID;

@Component
public class PollRepository extends BaseRepository<Poll, UUID> {
    public PollRepository() {
        super(Poll.class);
    }

    @Override
    public Optional<Poll> update(Poll poll, UUID id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            Poll existingPoll = entityManager.find(Poll.class, id);

            if (existingPoll != null) {
                existingPoll.setDescription(poll.getDescription());
            }

            entityManager.getTransaction().commit();

            return Optional.ofNullable(existingPoll);
        }
    }
}
