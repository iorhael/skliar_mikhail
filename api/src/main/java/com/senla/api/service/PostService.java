package com.senla.api.service;

import com.senla.api.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostService {
    Optional<Post> createPost(Post post);

    Optional<Post> getPostById(UUID id);

    List<Post> getAllPosts();

    Optional<Post> updatePost(Post post, UUID id);

    Optional<Post> deletePost(UUID id);
}
