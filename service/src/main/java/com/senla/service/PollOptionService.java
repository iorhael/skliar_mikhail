package com.senla.service;

import com.senla.dto.pollOption.PollOptionCreateDto;
import com.senla.dto.pollOption.PollOptionGetDto;

import java.util.List;
import java.util.UUID;

public interface PollOptionService {
    PollOptionGetDto createPollOption(PollOptionCreateDto pollOption);

    PollOptionGetDto getPollOptionBy(UUID id);

    List<PollOptionGetDto> getAllPollOptions(UUID pollId);

    PollOptionGetDto updatePollOption(PollOptionCreateDto pollOption, UUID id);

    void deletePollOption(UUID id);
}
