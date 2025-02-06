package com.senla.repository;

import com.senla.model.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @EntityGraph("comment-with-author")
    Optional<Comment> findWithAuthorById(UUID id);

    @EntityGraph("comment-with-author-and-children")
    List<Comment> findAllByPostId(UUID postId);
}
