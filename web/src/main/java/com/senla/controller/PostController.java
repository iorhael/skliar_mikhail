package com.senla.controller;

import com.senla.controller.dto.ResponseInfoDto;
import com.senla.dto.post.PostCreateDto;
import com.senla.dto.post.PostDetailedDto;
import com.senla.dto.post.PostPreviewDto;
import com.senla.dto.post.PostUpdateDto;
import com.senla.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private static final String POST_DELETION_MESSAGE = "Post with id %s deleted successfully";

    private final PostService postService;

    @GetMapping
    public List<PostPreviewDto> findAllPosts(@RequestParam(defaultValue = "0") int pageNo,
                                             @RequestParam(defaultValue = "15") int pageSize) {
        return postService.getAllPosts(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    public PostDetailedDto findPostById(@PathVariable UUID id) {
        return postService.getPostBy(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('AUTHOR')")
    @ResponseStatus(HttpStatus.CREATED)
    public PostPreviewDto createPost(@Valid @RequestBody PostCreateDto post) {
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    public PostPreviewDto updatePost(@Valid @RequestBody PostUpdateDto post,
                                     @PathVariable UUID id) {
        return postService.updatePost(post, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseInfoDto deletePost(@PathVariable UUID id) {
        postService.deletePost(id);

        return ResponseInfoDto.builder()
                .message(String.format(POST_DELETION_MESSAGE, id))
                .build();
    }
}
