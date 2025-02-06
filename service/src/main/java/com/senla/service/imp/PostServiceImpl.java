package com.senla.service.imp;

import com.senla.aspect.Benchmarked;
import com.senla.dto.post.PostCreateDto;
import com.senla.dto.post.PostDetailedDto;
import com.senla.dto.post.PostPreviewDto;
import com.senla.dto.post.PostUpdateDto;
import com.senla.model.Post;
import com.senla.model.SubscriptionPlan;
import com.senla.model.User;
import com.senla.repository.PostRepository;
import com.senla.repository.SubscriptionPlanRepository;
import com.senla.repository.UserRepository;
import com.senla.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Benchmarked
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    public static final String POST_NOT_FOUND = "Post not found";

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final SubscriptionPlanRepository subscriptionPlanRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public PostPreviewDto createPost(PostCreateDto postCreateDto) {
        Post post = modelMapper.map(postCreateDto, Post.class);

        User author = userRepository.getReferenceById(postCreateDto.getAuthorId());
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository
                .getReferenceById(postCreateDto.getSubscriptionPlanId());

        post.setAuthor(author);
        post.setSubscriptionPlan(subscriptionPlan);

        log.info("Trying to create post with user id {} and subscription plan id {}",
                author.getId(),
                subscriptionPlan.getId()
        );

        postRepository.save(post);

        log.info("Post with id {} created successfully", post.getId());

        return modelMapper.map(post, PostPreviewDto.class);
    }

    @Override
    @Transactional
    public PostDetailedDto getPostBy(UUID id) {
        postRepository.findWithTagsById(id)
                .orElseThrow(() -> new EntityNotFoundException(POST_NOT_FOUND));

        return postRepository.findWithSimpleViewById(id)
                .map(post -> modelMapper.map(post, PostDetailedDto.class))
                .orElseThrow(() -> new EntityNotFoundException(POST_NOT_FOUND));
    }

    @Override
    public List<PostPreviewDto> getAllPosts() {
        return postRepository.findWithSimpleViewBy()
                .stream()
                .map(post -> modelMapper.map(post, PostPreviewDto.class))
                .toList();
    }

    @Override
    @Transactional
    public PostPreviewDto updatePost(PostUpdateDto postUpdateDto, UUID id) {
        Post post = postRepository.findWithSimpleViewById(id)
                .orElseThrow(() -> new EntityNotFoundException(POST_NOT_FOUND));

        modelMapper.map(postUpdateDto, post);

        return modelMapper.map(post, PostPreviewDto.class);
    }

    @Override
    public void deletePost(UUID id) {
        postRepository.deleteById(id);
    }
}
