package com.senla.service;

import com.senla.dto.post.PostCreateDto;
import com.senla.dto.post.PostGetDto;
import com.senla.dto.post.PostUpdateDto;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostGetDto createPost(PostCreateDto post);

    PostGetDto getPostById(UUID id);

    List<PostGetDto> getAllPosts();

    PostGetDto updatePost(PostUpdateDto post, UUID id);

    void deletePost(UUID id);
}
