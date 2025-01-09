package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Post;
import com.senla.util.SessionManager;
import org.hibernate.Session;

import java.util.Optional;
import java.util.UUID;

@Component
public class PostRepository extends BaseRepository<Post, UUID> {
    public PostRepository() {
        super(Post.class);
    }

    @Override
    public Optional<Post> update(Post post, UUID id) {
        Optional<Post> result = Optional.empty();

        try (Session session = SessionManager.openSession()) {
            session.beginTransaction();

            Post existingPost = session.get(Post.class, id);

            if (existingPost != null) {
                existingPost.setTitle(post.getTitle());
                existingPost.setContent(post.getContent());
                existingPost.setPublicationDate(post.getPublicationDate());
                existingPost.setViewsTotal(post.getViewsTotal());

                result = Optional.of(existingPost);
            }

            session.getTransaction().commit();
        }
        return result;
    }
}
