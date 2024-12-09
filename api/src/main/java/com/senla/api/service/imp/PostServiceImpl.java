package com.senla.api.service.imp;

import com.senla.api.dao.PostDao;
import com.senla.api.model.Post;
import com.senla.api.service.PostService;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;

    @Override
    public Optional<Post> createPost(Post post) {
        return postDao.create(post);
    }

    @Override
    public Optional<Post> getPostById(UUID id) {
        return postDao.getById(id);
    }

    @Override
    public List<Post> getAllPosts() {
        return postDao.getAll();
    }

    @Override
    public Optional<Post> updatePost(Post post, UUID id) {
        Post updatedPost = Post.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .updatedDate(LocalDateTime.now())
                .publicationDate(post.getPublicationDate())
                .subscriptionPlanId(post.getSubscriptionPlanId())
                .viewsTotal(post.getViewsTotal())
                .build();

        return postDao.update(updatedPost, id);
    }

    @Override
    public Optional<Post> deletePost(UUID id) {
        return postDao.delete(id);
    }
}
