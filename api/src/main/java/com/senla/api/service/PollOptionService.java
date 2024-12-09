package com.senla.api.service;

import com.senla.api.model.PollOption;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PollOptionService {
    Optional<PollOption> createPollOption(PollOption pollOption);

    Optional<PollOption> getPollOptionById(UUID id);

    List<PollOption> getAllPollOptions();

    Optional<PollOption> updatePollOption(PollOption pollOption, UUID id);

    Optional<PollOption> deletePollOption(UUID id);
}
