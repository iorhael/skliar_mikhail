package com.senla.service;

import com.senla.dto.pollOption.PollOptionCreateDto;
import com.senla.dto.pollOption.PollOptionGetDto;

import java.util.List;
import java.util.UUID;

public interface PollOptionService {
    PollOptionGetDto createPollOption(PollOptionCreateDto pollOption);

    PollOptionGetDto getPollOptionById(UUID id);

    List<PollOptionGetDto> getAllPollOptions();

    PollOptionGetDto updatePollOption(PollOptionGetDto pollOption, UUID id);

    PollOptionGetDto deletePollOption(UUID id);
}
