package com.senla.api.service;

import com.senla.api.model.Comment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentService {
    Optional<Comment> createComment(Comment comment);

    Optional<Comment> getCommentById(UUID id);

    List<Comment> getAllComments();

    Optional<Comment> updateComment(Comment comment, UUID id);

    Optional<Comment> deleteComment(UUID id);
}
