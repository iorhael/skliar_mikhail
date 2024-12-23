package com.senla.api.service;

import com.senla.api.dto.post.PostCreateDto;
import com.senla.api.dto.post.PostGetDto;
import com.senla.api.dto.post.PostUpdateDto;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostGetDto createPost(PostCreateDto post);

    PostGetDto getPostById(UUID id);

    List<PostGetDto> getAllPosts();

    PostGetDto updatePost(PostUpdateDto post, UUID id);

    PostGetDto deletePost(UUID id);
}
