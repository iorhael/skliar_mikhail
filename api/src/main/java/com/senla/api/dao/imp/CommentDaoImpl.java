package com.senla.api.dao.imp;

import com.senla.api.dao.CommentDao;
import com.senla.api.dao.exception.CommentNotFoundException;
import com.senla.api.dao.exception.NoCommentsFoundException;
import com.senla.api.dao.query.CommentQueries;
import com.senla.api.model.Comment;
import com.senla.api.util.ConnectionManager;
import com.senla.di.annotation.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CommentDaoImpl implements CommentDao {
    @Override
    public Optional<Comment> create(Comment comment) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CommentQueries.CREATE_COMMENT)) {
            preparedStatement.setObject(1, comment.getPostId());
            preparedStatement.setObject(2, comment.getAuthorId());
            preparedStatement.setString(3, comment.getContent());
            preparedStatement.setObject(4, comment.getParentId());
            return Optional.of(getComment(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Comment> getById(UUID commentId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CommentQueries.SELECT_COMMENT_BY_ID)) {
            preparedStatement.setObject(1, commentId);
            return Optional.of(getComment(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Comment> getAll() {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CommentQueries.SELECT_ALL_COMMENTS)) {
            List<Comment> comments = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UUID id = resultSet.getObject("id", UUID.class);
                UUID postId = resultSet.getObject("post_id", UUID.class);
                UUID authorId = resultSet.getObject("author_id", UUID.class);
                String content = resultSet.getString("content");
                LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();
                LocalDateTime updatedDate = resultSet.getTimestamp("updated_date").toLocalDateTime();
                UUID parentId = resultSet.getObject("parent_id", UUID.class);

                Comment comment = new Comment(postId, authorId, content);
                comment.setId(id);
                comment.setCreatedDate(createdDate);
                comment.setUpdatedDate(updatedDate);
                comment.setParentId(parentId);

                comments.add(comment);
            }
            return comments;
        } catch (SQLException e) {
            throw new NoCommentsFoundException("No comments found");
        }
    }

    @Override
    public Optional<Comment> update(Comment comment, UUID commentId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CommentQueries.UPDATE_COMMENT_BY_ID)) {
            preparedStatement.setString(1, comment.getContent());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(comment.getUpdatedDate()));
            preparedStatement.setObject(3, commentId);
            return Optional.of(getComment(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Comment> delete(UUID commentId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(CommentQueries.DELETE_COMMENT_BY_ID)) {
            preparedStatement.setObject(1, commentId);
            return Optional.of(getComment(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Comment getComment(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            UUID id = resultSet.getObject("id", UUID.class);
            UUID postId = resultSet.getObject("post_id", UUID.class);
            UUID authorId = resultSet.getObject("author_id", UUID.class);
            String content = resultSet.getString("content");
            LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();
            LocalDateTime updatedDate = resultSet.getTimestamp("updated_date").toLocalDateTime();
            UUID parentId = resultSet.getObject("parent_id", UUID.class);

            Comment comment = new Comment(postId, authorId, content);
            comment.setId(id);
            comment.setCreatedDate(createdDate);
            comment.setUpdatedDate(updatedDate);
            comment.setParentId(parentId);

            return comment;
        } else {
            throw new CommentNotFoundException("Comment not found");
        }
    }
}
