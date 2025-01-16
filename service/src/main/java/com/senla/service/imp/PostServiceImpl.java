package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.post.PostCreateDto;
import com.senla.dto.post.PostGetDto;
import com.senla.dto.post.PostUpdateDto;
import com.senla.model.Post;
import com.senla.repository.PostRepository;
import com.senla.service.PostService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.post.PostDeleteException;
import com.senla.service.exception.post.PostUpdateException;
import com.senla.util.ModelMapperUtil;
import org.modelmapper.TypeMap;

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
        Post createdPost = postRepository.create(postEntity);

        return ModelMapperUtil.MODEL_MAPPER.map(createdPost, PostGetDto.class);
    }

    @Override
    public PostGetDto getPostById(UUID id) {
        TypeMap<Post, PostGetDto> getMapper = ModelMapperUtil.MODEL_MAPPER.createTypeMap(Post.class, PostGetDto.class);
        getMapper.addMappings(
                mapper -> mapper.map(src -> src.getAuthor().getUsername(), PostGetDto::setAuthorName)
        );

        return postRepository.findById(id)
                .map(post -> ModelMapperUtil.MODEL_MAPPER.map(post, PostGetDto.class))
                .orElseThrow(() -> new ServiceException("No post found"));
    }

    @Override
    public List<PostGetDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> ModelMapperUtil.MODEL_MAPPER.map(post, PostGetDto.class))
                .toList();
    }

    @Override
    public PostGetDto updatePost(PostUpdateDto post, UUID id) {
        Post postEntity = ModelMapperUtil.MODEL_MAPPER.map(post, Post.class);
        postEntity.setUpdatedDate(LocalDateTime.now());
        postEntity.setViewsTotal(0L); // Update when the post views functionality will be added

        return postRepository.update(postEntity, id)
                .map(p -> ModelMapperUtil.MODEL_MAPPER.map(p, PostGetDto.class))
                .orElseThrow(() -> new PostUpdateException("Can't update post"));
    }

    @Override
    public PostGetDto deletePost(UUID id) {
        return postRepository.deleteById(id)
                .map(post -> ModelMapperUtil.MODEL_MAPPER.map(post, PostGetDto.class))
                .orElseThrow(() -> new PostDeleteException("Can't delete post"));
    }
}
