package com.senla.api.service.imp;

import com.senla.api.dao.VoteDao;
import com.senla.api.dao.exception.VoteNotFoundException;
import com.senla.api.model.Vote;
import com.senla.api.model.VoteId;
import com.senla.api.service.VoteService;
import com.senla.api.service.exception.vote.VoteCreateException;
import com.senla.api.service.exception.vote.VoteDeleteException;
import com.senla.api.service.exception.vote.VoteUpdateException;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;

@Component
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteDao voteDao;

    @Override
    public Vote createVote(Vote vote) {
        return voteDao.create(vote).orElseThrow(() -> new VoteCreateException("Can't create vote"));
    }

    @Override
    public Vote getVoteById(VoteId id) {
        return voteDao.getById(id).orElseThrow(() -> new VoteNotFoundException("No vote found"));
    }

    @Override
    public List<Vote> getAllVotes() {
        return voteDao.getAll();
    }

    @Override
    public Vote updateVote(Vote vote, VoteId id) {
        return voteDao.update(vote, id).orElseThrow(() -> new VoteUpdateException("Can't update vote"));
    }

    @Override
    public Vote deleteVote(VoteId id) {
        return voteDao.delete(id).orElseThrow(() -> new VoteDeleteException("Can't update vote"));
    }
}
