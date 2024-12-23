package com.senla.api.service.imp;

import com.senla.api.dao.PostDao;
import com.senla.api.dao.exception.PostNotFoundException;
import com.senla.api.dto.post.PostCreateDto;
import com.senla.api.dto.post.PostGetDto;
import com.senla.api.dto.post.PostUpdateDto;
import com.senla.api.model.Post;
import com.senla.api.service.PostService;
import com.senla.api.service.exception.post.PostCreateException;
import com.senla.api.service.exception.post.PostDeleteException;
import com.senla.api.service.exception.post.PostUpdateException;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostGetDto createPost(PostCreateDto post) {
        Post postEntity = modelMapper.map(post, Post.class);

        return postDao.create(postEntity)
                .map(p -> modelMapper.map(p, PostGetDto.class))
                .orElseThrow(() -> new PostCreateException("Can't create post"));
    }

    @Override
    public PostGetDto getPostById(UUID id) {
        return postDao.getById(id)
                .map(post -> modelMapper.map(post, PostGetDto.class))
                .orElseThrow(() -> new PostNotFoundException("No post found"));
    }

    @Override
    public List<PostGetDto> getAllPosts() {
        List<Post> posts = postDao.getAll();
        List<PostGetDto> postGetDtos = new ArrayList<>();

        for (Post post : posts) {
            PostGetDto postGetDto = modelMapper.map(post, PostGetDto.class);
            postGetDtos.add(postGetDto);
        }

        return postGetDtos;
    }

    @Override
    public PostGetDto updatePost(PostUpdateDto post, UUID id) {
        Post postEntity = modelMapper.map(post, Post.class);
        postEntity.setUpdatedDate(LocalDateTime.now());
        postEntity.setViewsTotal(0); // Update when the post views functionality will be added

        return postDao.update(postEntity, id)
                .map(p -> modelMapper.map(p, PostGetDto.class))
                .orElseThrow(() -> new PostUpdateException("Can't update post"));
    }

    @Override
    public PostGetDto deletePost(UUID id) {
        return postDao.delete(id)
                .map(post -> modelMapper.map(post, PostGetDto.class))
                .orElseThrow(() -> new PostDeleteException("Can't delete post"));
    }
}
