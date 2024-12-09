package com.senla.api.service;

import com.senla.api.model.Vote;
import com.senla.api.model.VoteId;

import java.util.List;
import java.util.Optional;

public interface VoteService {
    Optional<Vote> createVote(Vote vote);

    Optional<Vote> getVoteById(VoteId id);

    List<Vote> getAllVotes();

    Optional<Vote> updateVote(Vote vote, VoteId id);

    Optional<Vote> deleteVote(VoteId id);
}
