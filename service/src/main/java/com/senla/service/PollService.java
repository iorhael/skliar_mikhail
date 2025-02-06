package com.senla.service;

import com.senla.dto.poll.PollCreateDto;
import com.senla.dto.poll.PollGetDto;
import com.senla.dto.poll.PollUpdateDto;

import java.util.List;
import java.util.UUID;

public interface PollService {
    PollGetDto createPoll(PollCreateDto poll);

    PollGetDto getPollBy(UUID id);

    List<PollGetDto> getAllPolls();

    List<PollGetDto> getAllPollsForPost(UUID postId);

    PollGetDto updatePoll(PollUpdateDto poll, UUID id);

    void deletePoll(UUID id);
}
