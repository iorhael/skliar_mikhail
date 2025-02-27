package com.senla.service.unit;

import com.senla.dto.poll.PollCreateDto;
import com.senla.dto.poll.PollGetDto;
import com.senla.dto.poll.PollUpdateDto;
import com.senla.model.Poll;
import com.senla.model.Post;
import com.senla.model.User;
import com.senla.repository.PollRepository;
import com.senla.repository.PostRepository;
import com.senla.repository.UserRepository;
import com.senla.service.imp.PollServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PollServiceImplTest {
    static final String STRING_PLACEHOLDER =  "Test";

    @Mock
    PollRepository pollRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    PostRepository postRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    PollServiceImpl pollService;

    Poll poll;

    @BeforeEach
    void setup() {
        poll = new Poll();
        poll.setId(UUID.randomUUID());
        poll.setDescription(STRING_PLACEHOLDER);
    }

    @Test
    void whenCreatePoll_thenReturnPollGetDto() {
        PollCreateDto createDto = new PollCreateDto();
        createDto.setAuthorId(UUID.randomUUID());
        createDto.setPostId(UUID.randomUUID());

        given(modelMapper.map(createDto, Poll.class))
                .willReturn(poll);
        given(userRepository.getReferenceById(createDto.getAuthorId()))
                .willReturn(new User());
        given(postRepository.getReferenceById(createDto.getPostId()))
                .willReturn(new Post());
        given(pollRepository.save(poll))
                .willReturn(poll);
        given(modelMapper.map(poll, PollGetDto.class))
                .willReturn(new PollGetDto());

        assertThat(pollService.createPoll(createDto))
                .isInstanceOf(PollGetDto.class);
        assertNotNull(poll.getAuthor());
        assertNotNull(poll.getPost());
    }

    @Test
    void givenPoll_whenGetPollById_thenReturnPollGetDto() {
        UUID id = poll.getId();

        given(pollRepository.findWithPostAndAuthorById(poll.getId()))
                .willReturn(Optional.of(poll));
        given(modelMapper.map(poll, PollGetDto.class))
                .willReturn(new PollGetDto());

        assertThat(pollService.getPollBy(id))
                .isInstanceOf(PollGetDto.class);
    }

    @Test
    void givenNoPoll_whenGetPollById_thenThrowEntityNotFoundException() {
        UUID id = poll.getId();

        given(pollRepository.findWithPostAndAuthorById(poll.getId()))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> pollService.getPollBy(id));
        verify(modelMapper, never()).map(any(Poll.class), eq(PollGetDto.class));
    }

    @Test
    void givenListOfPolls_whenGetAllPolls_thenReturnListOfPollGetDto() {
        Poll poll1 = new Poll();
        List<Poll> polls = List.of(poll, poll1);

        given(pollRepository.findWithPostAndAuthorBy())
                .willReturn(polls);
        given(modelMapper.map(any(Poll.class), eq(PollGetDto.class)))
                .willReturn(new PollGetDto());

        assertThat(pollService.getAllPolls())
                .hasSize(polls.size());
    }

    @Test
    void givenPoll_whenUpdatePoll_thenReturnPollGetDto() {
        PollUpdateDto updateDto = new PollUpdateDto();
        updateDto.setDescription(STRING_PLACEHOLDER);
        UUID id = poll.getId();

        given(pollRepository.findWithPostAndAuthorById(id))
                .willReturn(Optional.of(poll));
        given(modelMapper.map(poll, PollGetDto.class))
                .willReturn(new PollGetDto());

        assertThat(pollService.updatePoll(updateDto, id))
                .isInstanceOf(PollGetDto.class);
    }

    @Test
    void givenNoPoll_whenUpdatePoll_thenThrowEntityNotFoundException() {
        PollUpdateDto updateDto = new PollUpdateDto();
        UUID id = poll.getId();

        given(pollRepository.findWithPostAndAuthorById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> pollService.updatePoll(updateDto, id));
    }

    @Test
    void whenDeletePoll() {
        UUID id = poll.getId();

        pollService.deletePoll(id);

        verify(pollRepository, times(1)).deleteById(id);
    }
}
