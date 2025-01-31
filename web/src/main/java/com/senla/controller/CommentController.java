package com.senla.controller;

import com.senla.controller.dto.ResponseInfoDto;
import com.senla.dto.comment.CommentCreateDto;
import com.senla.dto.comment.CommentGetDto;
import com.senla.dto.comment.CommentUpdateDto;
import com.senla.service.imp.CommentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private static final String COMMENT_DELETION_MESSAGE = "Comment deleted successfully";

    private final CommentServiceImpl commentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentGetDto> findAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentGetDto findCommentById(@PathVariable UUID id) {
        return commentService.getCommentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentGetDto createComment(@Valid @RequestBody CommentCreateDto comment) {
        return commentService.createComment(comment);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentGetDto updateComment(@Valid @RequestBody CommentUpdateDto comment,
                                       @PathVariable UUID id) {
        return commentService.updateComment(comment, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseInfoDto deleteComment(@PathVariable UUID id) {
        commentService.deleteComment(id);

        return ResponseInfoDto.builder()
                .message(COMMENT_DELETION_MESSAGE)
                .build();
    }
}
