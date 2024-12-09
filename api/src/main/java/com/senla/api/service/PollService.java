package com.senla.api.service;

import com.senla.api.model.Poll;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PollService {
    Optional<Poll> createPoll(Poll poll);

    Optional<Poll> getPollById(UUID id);

    List<Poll> getAllPolls();

    Optional<Poll> updatePoll(Poll poll, UUID id);

    Optional<Poll> deletePoll(UUID id);
}
