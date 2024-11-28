package com.senla.dao.imp;

import com.senla.dao.PostDao;
import com.senla.dao.exception.NoPostsFoundException;
import com.senla.dao.exception.PostNotFoundException;
import com.senla.dao.query.PostQueries;
import com.senla.model.Post;
import com.senla.util.ConnectionManager;

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

public class PostDaoImpl implements PostDao {
    @Override
    public Optional<Post> create(Post post) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PostQueries.CREATE_POST)) {
            preparedStatement.setObject(1, post.getAuthorId());
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getContent());
            preparedStatement.setString(4, post.getContent());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(post.getPublicationDate()));
            preparedStatement.setObject(6, post.getSubscriptionPlanId());
            return Optional.ofNullable(getPost(preparedStatement));
        } catch (SQLException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Post> getById(UUID postId) {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PostQueries.SELECT_POST_BY_ID)){
            preparedStatement.setObject(1, postId);
            return Optional.ofNullable(getPost(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Post> getAll() {
        try(Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PostQueries.SELECT_ALL_POSTS)){
            List<Post> posts = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UUID id = resultSet.getObject("id", UUID.class);
                UUID authorId = resultSet.getObject("author_id", UUID.class);
                UUID subscriptionPlanId = resultSet.getObject("subscription_plan_id", UUID.class);
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                long viewsTotal = resultSet.getLong("views_total");
                LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();
                LocalDateTime updatedDate = resultSet.getTimestamp("updated_date").toLocalDateTime();
                LocalDateTime publicationDate = resultSet.getTimestamp("publication_date").toLocalDateTime();

                posts.add(Post.builder()
                        .id(id)
                        .authorId(authorId)
                        .subscriptionPlanId(subscriptionPlanId)
                        .title(title)
                        .content(content)
                        .viewsTotal(viewsTotal)
                        .createdDate(createdDate)
                        .updatedDate(updatedDate)
                        .publicationDate(publicationDate)
                        .build()
                );
            }
            return posts;
        } catch (SQLException e) {
            throw new NoPostsFoundException("No posts found");
        }
    }

    @Override
    public Optional<Post> update(Post post, UUID postId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PostQueries.UPDATE_POST_BY_ID)) {
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(post.getUpdatedDate()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(post.getPublicationDate()));
            preparedStatement.setObject(5, post.getSubscriptionPlanId());
            preparedStatement.setLong(6, post.getViewsTotal());
            preparedStatement.setObject(7, postId);
            return Optional.ofNullable(getPost(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Post> delete(UUID postId) {
        try (Connection connection = ConnectionManager.open(); PreparedStatement preparedStatement = connection.prepareStatement(PostQueries.DELETE_POST_BY_ID)) {
            preparedStatement.setObject(1, postId);
            return Optional.ofNullable(getPost(preparedStatement));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Post getPost(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            UUID id = resultSet.getObject("id", UUID.class);
            UUID authorId = resultSet.getObject("author_id", UUID.class);
            UUID subscriptionPlanId = resultSet.getObject("subscription_plan_id", UUID.class);
            String title = resultSet.getString("title");
            String content = resultSet.getString("content");
            long viewsTotal = resultSet.getLong("views_total");
            LocalDateTime createdDate = resultSet.getTimestamp("created_date").toLocalDateTime();
            LocalDateTime updatedDate = resultSet.getTimestamp("updated_date").toLocalDateTime();
            LocalDateTime publicationDate = resultSet.getTimestamp("publication_date").toLocalDateTime();

            return Post.builder()
                    .id(id)
                    .authorId(authorId)
                    .subscriptionPlanId(subscriptionPlanId)
                    .title(title)
                    .content(content)
                    .viewsTotal(viewsTotal)
                    .createdDate(createdDate)
                    .updatedDate(updatedDate)
                    .publicationDate(publicationDate)
                    .build();
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }
}
