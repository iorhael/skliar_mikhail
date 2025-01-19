package com.senla.repository;

import com.senla.model.Comment;
import com.senla.model.Post;
import com.senla.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CommentRepository extends BaseRepository<Comment, UUID> {

    public CommentRepository() {
        super(Comment.class);
    }

    @Override
    public Comment create(Comment comment) {
        Post persistedPost = entityManager.getReference(Post.class, comment.getPost().getId());
        User persistedAuthor = entityManager.getReference(User.class, comment.getAuthor().getId());

        comment.setPost(persistedPost);
        comment.setAuthor(persistedAuthor);

        Optional.ofNullable(comment.getParentComment())
                .map(Comment::getId)
                .map(parentCommentId -> entityManager.getReference(Comment.class, parentCommentId))
                .ifPresent(comment::setParentComment);

        entityManager.persist(comment);

        return comment;
    }

    @Override
    public Optional<Comment> update(Comment comment, UUID id) {
        Comment existingComment = entityManager.find(Comment.class, id);

        if (existingComment != null) {
            existingComment.setContent(comment.getContent());
        }

        return Optional.ofNullable(existingComment);
    }
}
