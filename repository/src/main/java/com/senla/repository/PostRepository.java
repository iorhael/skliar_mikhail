package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Post;
import com.senla.model.SubscriptionPlan;
import com.senla.model.User;
import com.senla.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.UUID;

@Component
public class PostRepository extends BaseRepository<Post, UUID> {
    public PostRepository() {
        super(Post.class);
    }

    @Override
    public Post create(Post post) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            User persistedAuthor = entityManager.getReference(User.class, post.getAuthor().getId());
            SubscriptionPlan persistedSubscriptionPlan = entityManager.getReference(SubscriptionPlan.class, post.getSubscriptionPlan().getId());

            post.setSubscriptionPlan(persistedSubscriptionPlan);
            post.setAuthor(persistedAuthor);

            entityManager.persist(post);

            entityManager.getTransaction().commit();
        }
        return post;
    }

    @Override
    public Optional<Post> update(Post post, UUID id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();

            Post existingPost = entityManager.find(Post.class, id);

            if (existingPost != null) {
                existingPost.setTitle(post.getTitle());
                existingPost.setContent(post.getContent());
                existingPost.setPublicationDate(post.getPublicationDate());
                existingPost.setUpdatedDate(post.getUpdatedDate());
                existingPost.setViewsTotal(post.getViewsTotal());
            }

            entityManager.getTransaction().commit();

            return Optional.ofNullable(existingPost);
        }
    }
}
