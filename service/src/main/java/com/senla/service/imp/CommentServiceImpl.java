package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.comment.CommentCreateDto;
import com.senla.dto.comment.CommentGetDto;
import com.senla.dto.comment.CommentUpdateDto;
import com.senla.model.Comment;
import com.senla.repository.CommentRepository;
import com.senla.repository.exception.CommentNotFoundException;
import com.senla.service.CommentService;
import com.senla.service.exception.comment.CommentCreateException;
import com.senla.service.exception.comment.CommentDeleteException;
import com.senla.service.exception.comment.CommentUpdateException;
import com.senla.util.ModelMapperUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentGetDto createComment(CommentCreateDto comment) {
        Comment commentEntity = ModelMapperUtil.MODEL_MAPPER.map(comment, Comment.class);

        return commentRepository.create(commentEntity)
                .map(c -> ModelMapperUtil.MODEL_MAPPER.map(c, CommentGetDto.class))
                .orElseThrow(() -> new CommentCreateException("Can't create comment"));
    }

    @Override
    public CommentGetDto getCommentById(UUID id) {
        return commentRepository.getById(id)
                .map(post -> ModelMapperUtil.MODEL_MAPPER.map(post, CommentGetDto.class))
                .orElseThrow(() -> new CommentNotFoundException("No post found"));
    }

    @Override
    public List<CommentGetDto> getAllComments() {
        return commentRepository.getAll().stream()
                .map(comment -> ModelMapperUtil.MODEL_MAPPER.map(comment, CommentGetDto.class))
                .toList();
    }

    @Override
    public CommentGetDto updateComment(CommentUpdateDto comment, UUID id) {
        Comment commentEntity = ModelMapperUtil.MODEL_MAPPER.map(comment, Comment.class);
        commentEntity.setUpdatedDate(LocalDateTime.now());

        return commentRepository.update(commentEntity, id)
                .map(p -> ModelMapperUtil.MODEL_MAPPER.map(p, CommentGetDto.class))
                .orElseThrow(() -> new CommentUpdateException("Can't update comment"));
    }

    @Override
    public CommentGetDto deleteComment(UUID id) {
        return commentRepository.delete(id)
                .map(post -> ModelMapperUtil.MODEL_MAPPER.map(post, CommentGetDto.class))
                .orElseThrow(() -> new CommentDeleteException("Can't delete comment"));
    }
}
