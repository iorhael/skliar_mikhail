package com.senla.service.imp;

import com.senla.aspect.Benchmarked;
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
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Benchmarked
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    public static final String COMMENT_NOT_FOUND = "Comment not found";

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
                .map(commentRepository::getReferenceById)
                .ifPresent(comment::setParentComment);

        comment.setAuthor(author);
        comment.setPost(post);

        log.info("Trying to create comment with user id {} and post id {}",
                author.getId(),
                post.getId()
        );

        commentRepository.save(comment);

        log.info("Comment with id {} created successfully", comment.getId());

        return modelMapper.map(comment, CommentGetDto.class);
    }

    @Override
    public CommentGetDto getCommentBy(UUID id) {
        return commentRepository.findWithAuthorById(id)
                .map(comment -> modelMapper.map(comment, CommentGetDto.class))
                .orElseThrow(() -> new EntityNotFoundException(COMMENT_NOT_FOUND));
    }

    @Override
    public List<CommentGetDto> getAllComments(UUID postId) {
        return commentRepository.findAllByPostId(postId)
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
}
