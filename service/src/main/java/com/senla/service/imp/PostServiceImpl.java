package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.post.PostCreateDto;
import com.senla.dto.post.PostGetDto;
import com.senla.dto.post.PostUpdateDto;
import com.senla.model.Post;
import com.senla.repository.PostRepository;
import com.senla.repository.exception.PostNotFoundException;
import com.senla.service.PostService;
import com.senla.service.exception.post.PostCreateException;
import com.senla.service.exception.post.PostDeleteException;
import com.senla.service.exception.post.PostUpdateException;
import com.senla.util.ModelMapperUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public PostGetDto createPost(PostCreateDto post) {
        Post postEntity = ModelMapperUtil.MODEL_MAPPER.map(post, Post.class);

        return postRepository.create(postEntity)
                .map(p -> ModelMapperUtil.MODEL_MAPPER.map(p, PostGetDto.class))
                .orElseThrow(() -> new PostCreateException("Can't create post"));
    }

    @Override
    public PostGetDto getPostById(UUID id) {
        return postRepository.getById(id)
                .map(post -> ModelMapperUtil.MODEL_MAPPER.map(post, PostGetDto.class))
                .orElseThrow(() -> new PostNotFoundException("No post found"));
    }

    @Override
    public List<PostGetDto> getAllPosts() {
        return postRepository.getAll().stream()
                .map(post -> ModelMapperUtil.MODEL_MAPPER.map(post, PostGetDto.class))
                .toList();
    }

    @Override
    public PostGetDto updatePost(PostUpdateDto post, UUID id) {
        Post postEntity = ModelMapperUtil.MODEL_MAPPER.map(post, Post.class);
        postEntity.setUpdatedDate(LocalDateTime.now());
        postEntity.setViewsTotal(0); // Update when the post views functionality will be added

        return postRepository.update(postEntity, id)
                .map(p -> ModelMapperUtil.MODEL_MAPPER.map(p, PostGetDto.class))
                .orElseThrow(() -> new PostUpdateException("Can't update post"));
    }

    @Override
    public PostGetDto deletePost(UUID id) {
        return postRepository.delete(id)
                .map(post -> ModelMapperUtil.MODEL_MAPPER.map(post, PostGetDto.class))
                .orElseThrow(() -> new PostDeleteException("Can't delete post"));
    }
}
