package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Poll;
import com.senla.model.Post;
import com.senla.model.User;
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
    public Poll create(Poll poll) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            Post persistedPost = entityManager.getReference(Post.class, poll.getPost().getId());
            User persistedAuthor = entityManager.getReference(User.class, poll.getAuthor().getId());

            poll.setPost(persistedPost);
            poll.setAuthor(persistedAuthor);

            entityManager.persist(poll);

            entityManager.getTransaction().commit();
        }
        return poll;
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
