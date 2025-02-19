package com.senla.service;

import com.senla.dto.TopCommentDto;
import com.senla.dto.comment.CommentCreateDto;
import com.senla.dto.comment.CommentGetDto;
import com.senla.dto.comment.CommentUpdateDto;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    CommentGetDto createComment(CommentCreateDto comment);

    CommentGetDto getCommentBy(UUID id);

    List<TopCommentDto> getTopLevelComments(UUID postId, int pageNo, int pageSize);

    List<CommentGetDto> getReplyComments(UUID parentId, int pageNo, int pageSize);

    CommentGetDto updateComment(CommentUpdateDto comment, UUID id);

    void deleteComment(UUID id);
}
