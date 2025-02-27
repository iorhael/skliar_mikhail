package com.senla.service.unit;

import com.senla.dto.post.PostCreateDto;
import com.senla.dto.post.PostDetailedDto;
import com.senla.dto.post.PostPreviewDto;
import com.senla.dto.post.PostUpdateDto;
import com.senla.model.SubscriptionPlan;
import com.senla.model.User;
import com.senla.model.Post;
import com.senla.repository.SubscriptionPlanRepository;
import com.senla.repository.UserRepository;
import com.senla.repository.PostRepository;
import com.senla.service.imp.PostServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {
    static final String STRING_PLACEHOLDER =  "Test";

    @Mock
    PostRepository postRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    SubscriptionPlanRepository subscriptionPlanRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    PostServiceImpl postService;

    Post post;

    @BeforeEach
    void setup() {
        post = new Post();
        post.setTitle(STRING_PLACEHOLDER);
        post.setContent(STRING_PLACEHOLDER);
        post.setCreatedDate(Instant.now());
    }

    @Test
    void whenCreatePost_thenReturnPostGetDto() {
        PostCreateDto createDto = new PostCreateDto();
        createDto.setAuthorId(UUID.randomUUID());
        createDto.setSubscriptionPlanId(UUID.randomUUID());

        given(modelMapper.map(createDto, Post.class))
                .willReturn(post);
        given(userRepository.getReferenceById(createDto.getAuthorId()))
                .willReturn(new User());
        given(subscriptionPlanRepository.getReferenceById(createDto.getSubscriptionPlanId()))
                .willReturn(new SubscriptionPlan());
        given(modelMapper.map(post, PostPreviewDto.class))
                .willReturn(new PostPreviewDto());

        assertThat(postService.createPost(createDto))
                .isInstanceOf(PostPreviewDto.class);
        assertNotNull(post.getAuthor());
        assertNotNull(post.getSubscriptionPlan());
    }

    @Test
    void givenPostWithTagsAndSimpleView_whenGetPostById_thenReturnPostGetDto() {
        UUID id = post.getId();

        given(postRepository.findWithTagsById(id))
                .willReturn(Optional.of(post));
        given(postRepository.findWithSimpleViewById(id))
                .willReturn(Optional.of(post));
        given(modelMapper.map(post, PostDetailedDto.class))
                .willReturn(new PostDetailedDto());

        assertThat(postService.getPostBy(id))
                .isInstanceOf(PostDetailedDto.class);
    }

    @Test
    void givenPostWithTagsWithoutSimpleView_whenGetPostById_thenThrowEntityNotFoundException() {
        UUID id = post.getId();

        given(postRepository.findWithTagsById(id))
                .willReturn(Optional.of(post));
        given(postRepository.findWithSimpleViewById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> postService.getPostBy(id));
        verify(modelMapper, never()).map(any(Post.class), eq(PostDetailedDto.class));
    }

    @Test
    void givenNoPost_whenGetPostById_thenThrowEntityNotFoundException() {
        UUID id = post.getId();

        given(postRepository.findWithTagsById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> postService.getPostBy(id));
        verify(modelMapper, never()).map(any(Post.class), eq(PostDetailedDto.class));
    }


    @Test
    void givenListOfPosts_whenGetAllPosts_thenReturnListOfPostGetDto() {
        Post post1 = new Post();
        List<Post> posts = List.of(post, post1);

        PageRequest testPage = PageRequest.of(1, 1);

        given(postRepository.findWithSimpleViewBy(testPage))
                .willReturn(posts);
        given(modelMapper.map(any(Post.class), eq(PostPreviewDto.class)))
                .willReturn(new PostPreviewDto());

        assertThat(postService.getAllPosts(1, 1))
                .hasSize(posts.size());
    }

    @Test
    void givenPost_whenUpdatePost_thenReturnPostGetDto() {
        PostUpdateDto updateDto = new PostUpdateDto();
        UUID id = post.getId();

        given(postRepository.findWithSimpleViewById(id))
                .willReturn(Optional.of(post));
        doNothing().when(modelMapper).map(updateDto, post);
        given(modelMapper.map(post, PostPreviewDto.class))
                .willReturn(new PostPreviewDto());

        assertThat(postService.updatePost(updateDto, id))
                .isInstanceOf(PostPreviewDto.class);
    }

    @Test
    void givenNoPost_whenUpdatePost_thenThrowEntityNotFoundException() {
        PostUpdateDto updateDto = new PostUpdateDto();
        UUID id = post.getId();

        given(postRepository.findWithSimpleViewById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> postService.updatePost(updateDto, id));
    }

    @Test
    void whenDeletePost() {
        UUID id = post.getId();

        postService.deletePost(id);

        verify(postRepository, times(1)).deleteById(id);
    }
}
