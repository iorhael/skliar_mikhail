package com.senla.controller;

import com.senla.controller.dto.ResponseInfoDto;
import com.senla.dto.TopCommentDto;
import com.senla.dto.comment.CommentCreateDto;
import com.senla.dto.comment.CommentGetDto;
import com.senla.dto.comment.CommentUpdateDto;
import com.senla.service.CommentService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private static final String COMMENT_DELETION_MESSAGE = "Comment with id %s deleted successfully";

    private final CommentService commentService;

    @GetMapping(params = "postId")
    public List<TopCommentDto> findAllComments(@RequestParam(defaultValue = "0") int pageNo,
                                               @RequestParam(defaultValue = "15") int pageSize,
                                               @RequestParam UUID postId) {
        return commentService.getTopLevelComments(postId, pageNo, pageSize);
    }

    @GetMapping(path = "/replies", params = "parentId")
    public List<CommentGetDto> findAllReplyComments(@RequestParam(defaultValue = "0") int pageNo,
                                                    @RequestParam(defaultValue = "15") int pageSize,
                                                    @RequestParam UUID parentId) {
        return commentService.getReplyComments(parentId, pageNo, pageSize);
    }

    @GetMapping("/{id}")
    public CommentGetDto findCommentById(@PathVariable UUID id) {
        return commentService.getCommentBy(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentGetDto createComment(@Valid @RequestBody CommentCreateDto comment) {
        return commentService.createComment(comment);
    }

    @PutMapping("/{id}")
    public CommentGetDto updateComment(@Valid @RequestBody CommentUpdateDto comment,
                                       @PathVariable UUID id) {
        return commentService.updateComment(comment, id);
    }

    @DeleteMapping("/{id}")
    public ResponseInfoDto deleteComment(@PathVariable UUID id) {
        commentService.deleteComment(id);

        return ResponseInfoDto.builder()
                .message(String.format(COMMENT_DELETION_MESSAGE, id))
                .build();
    }
}
