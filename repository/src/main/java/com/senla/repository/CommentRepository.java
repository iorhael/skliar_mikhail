package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Comment;
import com.senla.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.UUID;

@Component
public class CommentRepository extends BaseRepository<Comment, UUID> {
    public CommentRepository() {
        super(Comment.class);
    }

    @Override
    public Optional<Comment> update(Comment comment, UUID id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            Comment existingComment = entityManager.find(Comment.class, id);

            if (existingComment != null) {
                existingComment.setContent(comment.getContent());
                existingComment.setUpdatedDate(comment.getUpdatedDate());
            }

            entityManager.getTransaction().commit();

            return Optional.ofNullable(existingComment);
        }
    }
}
