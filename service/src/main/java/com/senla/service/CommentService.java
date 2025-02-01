package com.senla.service;

import com.senla.dto.comment.CommentCreateDto;
import com.senla.dto.comment.CommentGetDto;
import com.senla.dto.comment.CommentUpdateDto;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    CommentGetDto createComment(CommentCreateDto comment);

    CommentGetDto getCommentById(UUID id);

    List<CommentGetDto> getAllComments();

    CommentGetDto updateComment(CommentUpdateDto comment, UUID id);

    void deleteComment(UUID id);
}
