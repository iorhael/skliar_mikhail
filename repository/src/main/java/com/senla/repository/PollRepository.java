package com.senla.repository;

import com.senla.model.Poll;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PollRepository extends JpaRepository<Poll, UUID> {

    @EntityGraph("poll-with-post-and-author")
    Optional<Poll> findWithPostAndAuthorById(UUID id);

    @EntityGraph("poll-with-post-and-author")
    List<Poll> findWithPostAndAuthorBy();

    @EntityGraph("poll-with-post-and-author")
    List<Poll> findByPostId(UUID postId);
}
