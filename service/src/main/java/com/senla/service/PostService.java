package com.senla.service;

import com.senla.dto.post.PostCreateDto;
import com.senla.dto.post.PostDetailedDto;
import com.senla.dto.post.PostPreviewDto;
import com.senla.dto.post.PostUpdateDto;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostPreviewDto createPost(PostCreateDto post);

    PostDetailedDto getPostBy(UUID id);

    List<PostPreviewDto> getAllPosts();

    PostPreviewDto updatePost(PostUpdateDto post, UUID id);

    void deletePost(UUID id);
}
