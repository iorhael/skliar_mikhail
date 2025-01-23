package com.senla.repository;

import com.senla.model.Poll;
import com.senla.model.Post;
import com.senla.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PollRepository extends BaseRepository<Poll, UUID> {

    public PollRepository() {
        super(Poll.class);
    }

    @Override
    public Poll create(Poll poll) {
        Post persistedPost = entityManager.getReference(Post.class, poll.getPost().getId());
        User persistedAuthor = entityManager.getReference(User.class, poll.getAuthor().getId());

        poll.setPost(persistedPost);
        poll.setAuthor(persistedAuthor);

        entityManager.persist(poll);

        return poll;
    }

    @Override
    public Optional<Poll> update(Poll poll, UUID id) {
        Poll existingPoll = entityManager.find(Poll.class, id);

        if (existingPoll != null) {
            existingPoll.setDescription(poll.getDescription());
        }

        return Optional.ofNullable(existingPoll);
    }
}
