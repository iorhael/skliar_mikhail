package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.comment.CommentCreateDto;
import com.senla.dto.comment.CommentGetDto;
import com.senla.dto.comment.CommentUpdateDto;
import com.senla.model.Comment;
import com.senla.repository.CommentRepository;
import com.senla.service.CommentService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.comment.CommentDeleteException;
import com.senla.service.exception.comment.CommentUpdateException;
import com.senla.util.ModelMapperUtil;
import org.modelmapper.TypeMap;

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
        Comment createdComment = commentRepository.create(commentEntity);

        return ModelMapperUtil.MODEL_MAPPER.map(createdComment, CommentGetDto.class);
    }

    @Override
    public CommentGetDto getCommentById(UUID id) {
        return commentRepository.findById(id)
                .map(post -> ModelMapperUtil.MODEL_MAPPER.map(post, CommentGetDto.class))
                .orElseThrow(() -> new ServiceException("No post found"));
    }

    @Override
    public List<CommentGetDto> getAllComments() {
        TypeMap<Comment, CommentGetDto> getMapper = ModelMapperUtil.MODEL_MAPPER.createTypeMap(Comment.class, CommentGetDto.class);
        getMapper.addMappings(
                mapper -> mapper.map(src -> src.getAuthor().getUsername(), CommentGetDto::setAuthorName)
        );
        return commentRepository.findAll().stream()
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
        return commentRepository.deleteById(id)
                .map(comment -> ModelMapperUtil.MODEL_MAPPER.map(comment, CommentGetDto.class))
                .orElseThrow(() -> new CommentDeleteException("Can't delete comment"));
    }
}
