package com.senla.service.imp;

import com.senla.dto.comment.CommentCreateDto;
import com.senla.dto.comment.CommentGetDto;
import com.senla.dto.comment.CommentUpdateDto;
import com.senla.model.Comment;
import com.senla.repository.CommentRepository;
import com.senla.service.CommentService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.comment.CommentDeleteException;
import com.senla.service.exception.comment.CommentUpdateException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public CommentGetDto createComment(CommentCreateDto comment) {
        Comment commentEntity = modelMapper.map(comment, Comment.class);

        Comment createdComment = commentRepository.create(commentEntity);

        return modelMapper.map(createdComment, CommentGetDto.class);
    }

    @Transactional
    @Override
    public CommentGetDto getCommentById(UUID id) {
        return commentRepository.findById(id)
                .map(post -> modelMapper.map(post, CommentGetDto.class))
                .orElseThrow(() -> new ServiceException("No post found"));
    }

    @Transactional
    @Override
    public List<CommentGetDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(comment -> modelMapper.map(comment, CommentGetDto.class))
                .toList();
    }

    @Transactional
    @Override
    public CommentGetDto updateComment(CommentUpdateDto comment, UUID id) {
        Comment commentEntity = modelMapper.map(comment, Comment.class);
        commentEntity.setUpdatedDate(LocalDateTime.now());

        return commentRepository.update(commentEntity, id)
                .map(p -> modelMapper.map(p, CommentGetDto.class))
                .orElseThrow(() -> new CommentUpdateException("Can't update comment"));
    }

    @Transactional
    @Override
    public CommentGetDto deleteComment(UUID id) {
        return commentRepository.deleteById(id)
                .map(comment -> modelMapper.map(comment, CommentGetDto.class))
                .orElseThrow(() -> new CommentDeleteException("Can't delete comment"));
    }
}
