package com.senla.service.imp;

import com.senla.dto.post.PostCreateDto;
import com.senla.dto.post.PostGetDto;
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
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    public static final String POST_NOT_FOUND = "Post not found";

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final SubscriptionPlanRepository subscriptionPlanRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public PostGetDto createPost(PostCreateDto postCreateDto) {
        Post post = modelMapper.map(postCreateDto, Post.class);

        User author = userRepository.getReferenceById(postCreateDto.getAuthorId());
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.getReferenceById(postCreateDto.getSubscriptionPlanId());

        post.setAuthor(author);
        post.setSubscriptionPlan(subscriptionPlan);

        log.info("Trying to create post with user id {} and subscription plan id {}",
                author.getId(),
                subscriptionPlan.getId());

        postRepository.save(post);

        log.info("Post with id {} created successfully", post.getId());

        return modelMapper.map(post, PostGetDto.class);
    }

    @Override
    public PostGetDto getPostById(UUID id) {
        return postRepository.findById(id)
                .map(post -> modelMapper.map(post, PostGetDto.class))
                .orElseThrow(() -> new EntityNotFoundException(POST_NOT_FOUND));
    }

    @Override
    public List<PostGetDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(post -> modelMapper.map(post, PostGetDto.class))
                .toList();
    }

    @Override
    @Transactional
    public PostGetDto updatePost(PostUpdateDto postUpdateDto, UUID id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(POST_NOT_FOUND));

        post.setTitle(postUpdateDto.getTitle());
        post.setContent(postUpdateDto.getContent());
        post.setPublicationDate(postUpdateDto.getPublicationDate());

        return modelMapper.map(post, PostGetDto.class);
    }

    @Override
    public void deletePost(UUID id) {
        postRepository.deleteById(id);
    }
}
