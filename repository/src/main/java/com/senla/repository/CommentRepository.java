package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Comment;
import com.senla.util.SessionManager;
import org.hibernate.Session;

import java.util.Optional;
import java.util.UUID;

@Component
public class CommentRepository extends BaseRepository<Comment, UUID> {
    public CommentRepository() {
        super(Comment.class);
    }

    @Override
    public Optional<Comment> update(Comment comment, UUID id) {
        Optional<Comment> result = Optional.empty();

        try (Session session = SessionManager.openSession()) {
            session.beginTransaction();

            Comment existingComment = session.get(Comment.class, id);

            if (existingComment != null) {
                existingComment.setContent(comment.getContent());
                existingComment.setUpdatedDate(comment.getUpdatedDate());

                result = Optional.of(existingComment);
            }

            session.getTransaction().commit();
        }
        return result;
    }
}
