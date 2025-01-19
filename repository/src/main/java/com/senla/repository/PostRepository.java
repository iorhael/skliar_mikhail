package com.senla.repository;

import com.senla.model.Post;
import com.senla.model.SubscriptionPlan;
import com.senla.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PostRepository extends BaseRepository<Post, UUID> {

    public PostRepository() {
        super(Post.class);
    }

    @Override
    public Post create(Post post) {
        User persistedAuthor = entityManager.getReference(User.class, post.getAuthor().getId());
        SubscriptionPlan persistedSubscriptionPlan = entityManager.getReference(SubscriptionPlan.class, post.getSubscriptionPlan().getId());

        post.setSubscriptionPlan(persistedSubscriptionPlan);
        post.setAuthor(persistedAuthor);

        entityManager.persist(post);

        return post;
    }

    @Override
    public Optional<Post> update(Post post, UUID id) {
        Post existingPost = entityManager.find(Post.class, id);

        if (existingPost != null) {
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());
            existingPost.setPublicationDate(post.getPublicationDate());
            existingPost.setViewsTotal(post.getViewsTotal());
        }

        return Optional.ofNullable(existingPost);
    }
}
