package com.senla.api.service;

import com.senla.api.model.Vote;
import com.senla.api.model.VoteId;

import java.util.List;

public interface VoteService {
    Vote createVote(Vote vote);

    Vote getVoteById(VoteId id);

    List<Vote> getAllVotes();

    Vote updateVote(Vote vote, VoteId id);

    Vote deleteVote(VoteId id);
}
