package com.senla.service;

import com.senla.dto.vote.VoteDto;
import com.senla.model.VoteId;

import java.util.List;

public interface VoteService {
    VoteDto createVote(VoteDto vote);

    VoteDto getVoteById(VoteId id);

    List<VoteDto> getAllVotes();

    VoteDto updateVote(VoteDto vote, VoteId id);

    VoteDto deleteVote(VoteId id);
}
