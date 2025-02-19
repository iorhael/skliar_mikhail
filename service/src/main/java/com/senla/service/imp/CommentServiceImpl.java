package com.senla.service.imp;

import com.senla.aspect.Benchmarked;
import com.senla.dto.TopCommentDto;
import com.senla.dto.comment.CommentCreateDto;
import com.senla.dto.comment.CommentGetDto;
import com.senla.dto.comment.CommentUpdateDto;
import com.senla.model.Comment;
import com.senla.model.Post;
import com.senla.model.User;
import com.senla.repository.CommentRepository;
import com.senla.repository.PostRepository;
import com.senla.repository.UserRepository;
import com.senla.service.CommentService;
import com.senla.service.exception.CommentReplyException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Benchmarked
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    public static final String COMMENT_NOT_FOUND = "Comment not found";
    public static final String NESTING_LEVEL_VIOLATION = "The nesting level of comments cannot be more than 1";

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CommentGetDto createComment(CommentCreateDto commentCreateDto) {
        Comment comment = modelMapper.map(commentCreateDto, Comment.class);

        User author = userRepository.getReferenceById(commentCreateDto.getAuthorId());
        Post post = postRepository.getReferenceById(commentCreateDto.getPostId());

        Optional.ofNullable(commentCreateDto.getParentId())
                .map(this::validateParentNesting)
                .ifPresent(comment::setParentComment);

        comment.setAuthor(author);
        comment.setPost(post);

        commentRepository.save(comment);

        return modelMapper.map(comment, CommentGetDto.class);
    }

    @Override
    public CommentGetDto getCommentBy(UUID id) {
        return commentRepository.findWithAuthorById(id)
                .map(comment -> modelMapper.map(comment, CommentGetDto.class))
                .orElseThrow(() -> new EntityNotFoundException(COMMENT_NOT_FOUND));
    }

    @Override
    public List<TopCommentDto> getTopLevelComments(UUID postId, int pageNo, int pageSize) {
        return commentRepository.findTopLevel(postId, PageRequest.of(pageNo, pageSize));
    }

    @Override
    public List<CommentGetDto> getReplyComments(UUID parentId, int pageNo, int pageSize) {
        return commentRepository.findRepliesByParentCommentId(parentId, PageRequest.of(pageNo, pageSize))
                .stream()
                .map(comment -> modelMapper.map(comment, CommentGetDto.class))
                .toList();
    }

    @Override
    @Transactional
    public CommentGetDto updateComment(CommentUpdateDto commentUpdateDto, UUID id) {
        Comment comment = commentRepository.findWithAuthorById(id)
                .orElseThrow(() -> new EntityNotFoundException(COMMENT_NOT_FOUND));

        comment.setContent(commentUpdateDto.getContent());

        return modelMapper.map(comment, CommentGetDto.class);
    }

    @Override
    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }

    private Comment validateParentNesting(UUID parentId) {
        Comment parentComment = commentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException(COMMENT_NOT_FOUND));

        if (Objects.nonNull(parentComment.getParentComment()))
            throw new CommentReplyException(NESTING_LEVEL_VIOLATION);

        return parentComment;
    }
}
