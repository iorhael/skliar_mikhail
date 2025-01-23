package com.senla.service;

import com.senla.dto.vote.VoteCreateDto;
import com.senla.dto.vote.VoteGetDto;
import com.senla.dto.vote.VoteUpdateDto;
import com.senla.model.VoteId;

import java.util.List;

public interface VoteService {
    VoteGetDto createVote(VoteCreateDto vote);

    VoteGetDto getVoteById(VoteId id);

    List<VoteGetDto> getAllVotes();

    VoteGetDto updateVote(VoteUpdateDto vote, VoteId id);

    VoteGetDto deleteVote(VoteId id);
}
