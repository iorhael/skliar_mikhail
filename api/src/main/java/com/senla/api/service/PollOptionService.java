package com.senla.api.service;

import com.senla.api.model.PollOption;

import java.util.List;
import java.util.UUID;

public interface PollOptionService {
    PollOption createPollOption(PollOption pollOption);

    PollOption getPollOptionById(UUID id);

    List<PollOption> getAllPollOptions();

    PollOption updatePollOption(PollOption pollOption, UUID id);

    PollOption deletePollOption(UUID id);
}
