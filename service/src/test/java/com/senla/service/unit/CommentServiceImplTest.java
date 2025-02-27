package com.senla.service.unit;

import com.senla.dto.comment.CommentCreateDto;
import com.senla.dto.comment.CommentGetDto;
import com.senla.dto.comment.CommentUpdateDto;
import com.senla.model.Comment;
import com.senla.model.Post;
import com.senla.model.User;
import com.senla.repository.CommentRepository;
import com.senla.repository.PostRepository;
import com.senla.repository.UserRepository;
import com.senla.service.exception.CommentReplyException;
import com.senla.service.imp.CommentServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    static final String STRING_PLACEHOLDER =  "Test";

    @Mock
    CommentRepository commentRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    PostRepository postRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    CommentServiceImpl commentService;

    Comment comment;

    @BeforeEach
    void setup() {
        comment = new Comment();
        comment.setId(UUID.randomUUID());
        comment.setContent(STRING_PLACEHOLDER);
    }

    @Test
    void givenNotNestedParent_whenCreateComment_thenReturnCommentGetDto() {
        CommentCreateDto createDto = new CommentCreateDto();
        createDto.setAuthorId(UUID.randomUUID());
        createDto.setPostId(UUID.randomUUID());
        createDto.setParentId(UUID.randomUUID());

        Comment parentComment = new Comment();

        given(modelMapper.map(createDto, Comment.class))
                .willReturn(comment);
        given(userRepository.getReferenceById(createDto.getAuthorId()))
                .willReturn(new User());
        given(postRepository.getReferenceById(createDto.getPostId()))
                .willReturn(new Post());
        given(commentRepository.findById(createDto.getParentId()))
                .willReturn(Optional.of(parentComment));
        given(commentRepository.save(comment))
                .willReturn(comment);
        given(modelMapper.map(comment, CommentGetDto.class))
                .willReturn(new CommentGetDto());

        assertThat(commentService.createComment(createDto))
                .isInstanceOf(CommentGetDto.class);
        assertNotNull(comment.getParentComment());
        assertNotNull(comment.getAuthor());
        assertNotNull(comment.getPost());
    }

    @Test
    void givenNestedParent_whenCreateComment_thenThrowCommentReplyException() {
        CommentCreateDto createDto = new CommentCreateDto();
        createDto.setAuthorId(UUID.randomUUID());
        createDto.setPostId(UUID.randomUUID());
        createDto.setParentId(UUID.randomUUID());

        Comment parentComment = new Comment();
        parentComment.setParentComment(new Comment());

        given(modelMapper.map(createDto, Comment.class))
                .willReturn(comment);
        given(userRepository.getReferenceById(createDto.getAuthorId()))
                .willReturn(new User());
        given(postRepository.getReferenceById(createDto.getPostId()))
                .willReturn(new Post());
        given(commentRepository.findById(createDto.getParentId()))
                .willReturn(Optional.of(parentComment));

        assertThrows(CommentReplyException.class,
                () -> commentService.createComment(createDto));
    }

    @Test
    void givenNoParent_whenCreateComment_thenReturnCommentGetDto() {
        CommentCreateDto createDto = new CommentCreateDto();
        createDto.setAuthorId(UUID.randomUUID());
        createDto.setPostId(UUID.randomUUID());

        given(modelMapper.map(createDto, Comment.class))
                .willReturn(comment);
        given(userRepository.getReferenceById(createDto.getAuthorId()))
                .willReturn(new User());
        given(postRepository.getReferenceById(createDto.getPostId()))
                .willReturn(new Post());
        given(commentRepository.save(comment))
                .willReturn(comment);
        given(modelMapper.map(comment, CommentGetDto.class))
                .willReturn(new CommentGetDto());

        assertThat(commentService.createComment(createDto))
                .isInstanceOf(CommentGetDto.class);
        assertNotNull(comment.getAuthor());
        assertNotNull(comment.getPost());
    }

    @Test
    void givenComment_whenGetCommentById_thenReturnCommentGetDto() {
        UUID id = comment.getId();

        given(commentRepository.findWithAuthorById(comment.getId()))
                .willReturn(Optional.of(comment));
        given(modelMapper.map(comment, CommentGetDto.class))
                .willReturn(new CommentGetDto());

        assertThat(commentService.getCommentBy(id))
                .isInstanceOf(CommentGetDto.class);
    }

    @Test
    void givenNoComment_whenGetCommentById_thenThrowEntityNotFoundException() {
        UUID id = comment.getId();

        given(commentRepository.findWithAuthorById(comment.getId()))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> commentService.getCommentBy(id));
    }

    @Test
    void givenComment_whenUpdateComment_thenReturnCommentGetDto() {
        CommentUpdateDto updateDto = new CommentUpdateDto();
        updateDto.setContent(STRING_PLACEHOLDER);
        UUID id = comment.getId();

        given(commentRepository.findWithAuthorById(id))
                .willReturn(Optional.of(comment));
        given(modelMapper.map(comment, CommentGetDto.class))
                .willReturn(new CommentGetDto());

        assertThat(commentService.updateComment(updateDto, id))
                .isInstanceOf(CommentGetDto.class);
    }

    @Test
    void givenNoComment_whenUpdateComment_thenThrowEntityNotFoundException() {
        CommentUpdateDto updateDto = new CommentUpdateDto();
        UUID id = comment.getId();

        given(commentRepository.findWithAuthorById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> commentService.updateComment(updateDto, id));
    }

    @Test
    void whenDeleteComment() {
        UUID id = comment.getId();

        commentService.deleteComment(id);

        verify(commentRepository, times(1)).deleteById(id);
    }
}
