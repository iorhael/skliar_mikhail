package com.senla.api.service.imp;

import com.senla.api.dao.CommentDao;
import com.senla.api.dao.exception.CommentNotFoundException;
import com.senla.api.dto.comment.CommentCreateDto;
import com.senla.api.dto.comment.CommentGetDto;
import com.senla.api.dto.comment.CommentUpdateDto;
import com.senla.api.model.Comment;
import com.senla.api.service.CommentService;
import com.senla.api.service.exception.comment.CommentCreateException;
import com.senla.api.service.exception.comment.CommentDeleteException;
import com.senla.api.service.exception.comment.CommentUpdateException;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentGetDto createComment(CommentCreateDto comment) {
        Comment commentEntity = modelMapper.map(comment, Comment.class);

        return commentDao.create(commentEntity)
                .map(c -> modelMapper.map(c, CommentGetDto.class))
                .orElseThrow(() -> new CommentCreateException("Can't create comment"));
    }

    @Override
    public CommentGetDto getCommentById(UUID id) {
        return commentDao.getById(id)
                .map(post -> modelMapper.map(post, CommentGetDto.class))
                .orElseThrow(() -> new CommentNotFoundException("No post found"));
    }

    @Override
    public List<CommentGetDto> getAllComments() {
        List<Comment> comments = commentDao.getAll();
        List<CommentGetDto> commentGetDtos = new ArrayList<>();

        for (Comment comment : comments) {
            CommentGetDto commentGetDto = modelMapper.map(comment, CommentGetDto.class);
            commentGetDtos.add(commentGetDto);
        }

        return commentGetDtos;
    }

    @Override
    public CommentGetDto updateComment(CommentUpdateDto comment, UUID id) {
        Comment commentEntity = modelMapper.map(comment, Comment.class);
        commentEntity.setUpdatedDate(LocalDateTime.now());

        return commentDao.update(commentEntity, id)
                .map(p -> modelMapper.map(p, CommentGetDto.class))
                .orElseThrow(() -> new CommentUpdateException("Can't update comment"));
    }

    @Override
    public CommentGetDto deleteComment(UUID id) {
        return commentDao.delete(id)
                .map(post -> modelMapper.map(post, CommentGetDto.class))
                .orElseThrow(() -> new CommentDeleteException("Can't delete comment"));
    }
}
