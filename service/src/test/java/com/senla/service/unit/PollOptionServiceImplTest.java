package com.senla.service.unit;

import com.senla.dto.pollOption.PollOptionCreateDto;
import com.senla.dto.pollOption.PollOptionGetDto;
import com.senla.model.Poll;
import com.senla.model.PollOption;
import com.senla.repository.PollOptionRepository;
import com.senla.repository.PollRepository;
import com.senla.service.imp.PollOptionServiceImpl;
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
class PollOptionServiceImplTest {
    static final String STRING_PLACEHOLDER =  "Test";

    @Mock
    PollOptionRepository pollOptionRepository;

    @Mock
    PollRepository pollRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    PollOptionServiceImpl pollOptionService;

    PollOption pollOption;

    @BeforeEach
    void setup() {
        pollOption = new PollOption();
        pollOption.setId(UUID.randomUUID());
        pollOption.setDescription(STRING_PLACEHOLDER);
    }

    @Test
    void whenCreatePollOption_thenReturnPollOptionGetDto() {
        PollOptionCreateDto createDto = new PollOptionCreateDto();
        createDto.setPollId(UUID.randomUUID());

        given(modelMapper.map(createDto, PollOption.class))
                .willReturn(pollOption);
        given(pollRepository.getReferenceById(createDto.getPollId()))
                .willReturn(new Poll());
        given(pollOptionRepository.save(pollOption))
                .willReturn(pollOption);
        given(modelMapper.map(pollOption, PollOptionGetDto.class))
                .willReturn(new PollOptionGetDto());

        assertThat(pollOptionService.createPollOption(createDto))
                .isInstanceOf(PollOptionGetDto.class);
        assertNotNull(pollOption.getPoll());
    }

    @Test
    void givenPollOption_whenGetPollOptionById_thenReturnPollOptionGetDto() {
        UUID id = pollOption.getId();

        given(pollOptionRepository.findWithVotesById(pollOption.getId()))
                .willReturn(Optional.of(pollOption));
        given(modelMapper.map(pollOption, PollOptionGetDto.class))
                .willReturn(new PollOptionGetDto());

        assertThat(pollOptionService.getPollOptionBy(id))
                .isInstanceOf(PollOptionGetDto.class);
    }

    @Test
    void givenNoPollOption_whenGetPollOptionById_thenThrowEntityNotFoundException() {
        UUID id = pollOption.getId();

        given(pollOptionRepository.findWithVotesById(pollOption.getId()))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> pollOptionService.getPollOptionBy(id));
        verify(modelMapper, never()).map(any(PollOption.class), eq(PollOptionGetDto.class));
    }

    @Test
    void givenListOfPollOptions_whenGetAllPollOptions_thenReturnListOfPollOptionGetDto() {
        PollOption pollOption1 = new PollOption();
        UUID pollId = UUID.randomUUID();
        List<PollOption> pollOptions = List.of(pollOption, pollOption1);

        given(pollOptionRepository.findByPollId(pollId))
                .willReturn(pollOptions);
        given(modelMapper.map(any(PollOption.class), eq(PollOptionGetDto.class)))
                .willReturn(new PollOptionGetDto());

        assertThat(pollOptionService.getAllPollOptions(pollId))
                .hasSize(pollOptions.size());
    }

    @Test
    void givenPollOption_whenUpdatePollOption_thenReturnPollOptionGetDto() {
        PollOptionCreateDto createDto = new PollOptionCreateDto();
        createDto.setPollId(UUID.randomUUID());
        UUID id = pollOption.getId();

        given(pollOptionRepository.findWithVotesById(id))
                .willReturn(Optional.of(pollOption));
        given(pollRepository.getReferenceById(createDto.getPollId()))
                .willReturn(new Poll());
        given(modelMapper.map(pollOption, PollOptionGetDto.class))
                .willReturn(new PollOptionGetDto());

        assertThat(pollOptionService.updatePollOption(createDto, id))
                .isInstanceOf(PollOptionGetDto.class);
        assertNotNull(pollOption.getPoll());
    }

    @Test
    void givenNoPollOption_whenUpdatePollOption_thenThrowEntityNotFoundException() {
        PollOptionCreateDto createDto = new PollOptionCreateDto();
        UUID id = pollOption.getId();

        given(pollOptionRepository.findWithVotesById(id))
                .willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> pollOptionService.updatePollOption(createDto, id));
    }

    @Test
    void whenDeletePollOption() {
        UUID id = pollOption.getId();

        pollOptionService.deletePollOption(id);

        verify(pollOptionRepository, times(1)).deleteById(id);
    }
}
