package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Comment;
import com.senla.model.Post;
import com.senla.model.User;
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
    public Comment create(Comment comment) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            Post persistedPost = entityManager.getReference(Post.class, comment.getPost().getId());
            User persistedAuthor = entityManager.getReference(User.class, comment.getAuthor().getId());

            UUID parentCommentId = comment.getParentComment().getId();
            Comment persistedParentComment = (parentCommentId != null) ? entityManager.getReference(Comment.class, parentCommentId) : null;

            comment.setPost(persistedPost);
            comment.setAuthor(persistedAuthor);
            comment.setParentComment(persistedParentComment);

            entityManager.persist(comment);

            entityManager.getTransaction().commit();
        }
        return comment;
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
