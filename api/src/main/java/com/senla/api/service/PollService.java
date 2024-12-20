package com.senla.api.service;

import com.senla.api.dto.poll.PollCreateDto;
import com.senla.api.dto.poll.PollGetDto;
import com.senla.api.dto.poll.PollUpdateDto;

import java.util.List;
import java.util.UUID;

public interface PollService {
    PollGetDto createPoll(PollCreateDto poll);

    PollGetDto getPollById(UUID id);

    List<PollGetDto> getAllPolls();

    PollGetDto updatePoll(PollUpdateDto poll, UUID id);

    PollGetDto deletePoll(UUID id);
}
