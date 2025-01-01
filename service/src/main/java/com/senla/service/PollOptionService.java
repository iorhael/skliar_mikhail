package com.senla.service;

import com.senla.dto.pollOption.PollOptionDto;

import java.util.List;
import java.util.UUID;

public interface PollOptionService {
    PollOptionDto createPollOption(PollOptionDto pollOption);

    PollOptionDto getPollOptionById(UUID id);

    List<PollOptionDto> getAllPollOptions();

    PollOptionDto updatePollOption(PollOptionDto pollOption, UUID id);

    PollOptionDto deletePollOption(UUID id);
}
