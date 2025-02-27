package com.senla.service.unit;

import com.senla.dto.vote.VoteCreateDto;
import com.senla.dto.vote.VoteGetDto;
import com.senla.dto.vote.VoteUpdateDto;
import com.senla.model.PollOption;
import com.senla.model.Vote;
import com.senla.model.User;
import com.senla.model.VoteId;
import com.senla.repository.PollOptionRepository;
import com.senla.repository.VoteRepository;
import com.senla.repository.UserRepository;
import com.senla.service.imp.VoteServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.Instant;
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
class VoteServiceImplTest {

    @Mock
    VoteRepository voteRepository;

    @Mock
    PollOptionRepository pollOptionRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    VoteServiceImpl voteService;

    Vote vote;

    @BeforeEach
    void setup() {
        vote = new Vote();
        vote.setVoteDate(Instant.now());
    }

    @Test
    void whenCreateVote_thenReturnVoteGetDto() {
        VoteCreateDto createDto = new VoteCreateDto();
        createDto.setOwnerId(UUID.randomUUID());
        createDto.setPollOptionId(UUID.randomUUID());

        given(modelMapper.map(createDto, Vote.class))
                .willReturn(vote);
        given(userRepository.getReferenceById(createDto.getOwnerId()))
                .willReturn(new User());
        given(pollOptionRepository.getReferenceById(createDto.getPollOptionId()))
                .willReturn(new PollOption());
        given(voteRepository.save(vote))
                .willReturn(vote);
        given(modelMapper.map(vote, VoteGetDto.class))
                .willReturn(new VoteGetDto());

        assertThat(voteService.createVote(createDto))
                .isInstanceOf(VoteGetDto.class);
        assertNotNull(vote.getOwner());
        assertNotNull(vote.getPollOption());
    }

    @Test
    void givenVote_whenGetVoteById_thenReturnVoteGetDto() {
        VoteId id = vote.getId();

        given(voteRepository.findById(id))
                .willReturn(Optional.of(vote));
        given(modelMapper.map(vote, VoteGetDto.class))
                .willReturn(new VoteGetDto());

        assertThat(voteService.getVoteBy(id))
                .isInstanceOf(VoteGetDto.class);
    }

    @Test
    void givenNoVote_whenGetVoteById_thenThrowEntityNotFoundException() {
        VoteId id = vote.getId();

        given(voteRepository.findById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> voteService.getVoteBy(id));
        verify(modelMapper, never()).map(any(Vote.class), eq(VoteGetDto.class));
    }

    @Test
    void givenListOfVotes_whenGetAllVotes_thenReturnListOfVoteGetDto() {
        Vote vote1 = new Vote();
        List<Vote> votes = List.of(vote, vote1);

        given(voteRepository.findAll())
                .willReturn(votes);
        given(modelMapper.map(any(Vote.class), eq(VoteGetDto.class)))
                .willReturn(new VoteGetDto());

        assertThat(voteService.getAllVotes())
                .hasSize(votes.size());
    }

    @Test
    void givenVote_whenUpdateVote_thenReturnVoteGetDto() {
        VoteUpdateDto updateDto = new VoteUpdateDto();
        updateDto.setPollOptionId(UUID.randomUUID());
        VoteId id = vote.getId();

        given(voteRepository.findById(id))
                .willReturn(Optional.of(vote));
        given(pollOptionRepository.getReferenceById(updateDto.getPollOptionId()))
                .willReturn(new PollOption());
        given(modelMapper.map(vote, VoteGetDto.class))
                .willReturn(new VoteGetDto());

        assertThat(voteService.updateVote(updateDto, id))
                .isInstanceOf(VoteGetDto.class);
        assertNotNull(vote.getPollOption());
    }

    @Test
    void givenNoVote_whenUpdateVote_thenThrowEntityNotFoundException() {
        VoteUpdateDto updateDto = new VoteUpdateDto();
        VoteId id = vote.getId();

        given(voteRepository.findById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> voteService.updateVote(updateDto, id));
    }

    @Test
    void whenDeleteVote() {
        VoteId id = vote.getId();

        voteService.deleteVote(id);

        verify(voteRepository, times(1)).deleteById(id);
    }
}
