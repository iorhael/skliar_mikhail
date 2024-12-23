package com.senla.api.service;

import com.senla.api.dto.comment.CommentCreateDto;
import com.senla.api.dto.comment.CommentGetDto;
import com.senla.api.dto.comment.CommentUpdateDto;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    CommentGetDto createComment(CommentCreateDto comment);

    CommentGetDto getCommentById(UUID id);

    List<CommentGetDto> getAllComments();

    CommentGetDto updateComment(CommentUpdateDto comment, UUID id);

    CommentGetDto deleteComment(UUID id);
}
