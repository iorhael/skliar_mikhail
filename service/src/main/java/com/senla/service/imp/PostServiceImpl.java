package com.senla.service.imp;

import com.senla.dto.post.PostCreateDto;
import com.senla.dto.post.PostGetDto;
import com.senla.dto.post.PostUpdateDto;
import com.senla.model.Post;
import com.senla.repository.PostRepository;
import com.senla.service.PostService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.post.PostDeleteException;
import com.senla.service.exception.post.PostUpdateException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public PostGetDto createPost(PostCreateDto post) {
        Post postEntity = modelMapper.map(post, Post.class);

        Post createdPost = postRepository.create(postEntity);

        return modelMapper.map(createdPost, PostGetDto.class);
    }

    @Transactional
    @Override
    public PostGetDto getPostById(UUID id) {
        return postRepository.findById(id)
                .map(post -> modelMapper.map(post, PostGetDto.class))
                .orElseThrow(() -> new ServiceException("No post found"));
    }

    @Transactional
    @Override
    public List<PostGetDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> modelMapper.map(post, PostGetDto.class))
                .toList();
    }

    @Transactional
    @Override
    public PostGetDto updatePost(PostUpdateDto post, UUID id) {
        Post postEntity = modelMapper.map(post, Post.class);
        postEntity.setViewsTotal(0L); // Update when the post views functionality will be added

        return postRepository.update(postEntity, id)
                .map(p -> modelMapper.map(p, PostGetDto.class))
                .orElseThrow(() -> new PostUpdateException("Can't update post"));
    }

    @Transactional
    @Override
    public PostGetDto deletePost(UUID id) {
        return postRepository.deleteById(id)
                .map(post -> modelMapper.map(post, PostGetDto.class))
                .orElseThrow(() -> new PostDeleteException("Can't delete post"));
    }
}
