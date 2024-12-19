package com.senla.api.service.imp;

import com.senla.api.dao.VoteDao;
import com.senla.api.model.Vote;
import com.senla.api.model.VoteId;
import com.senla.api.service.VoteService;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteDao voteDao;

    @Override
    public Optional<Vote> createVote(Vote vote) {
        return voteDao.create(vote);
    }

    @Override
    public Optional<Vote> getVoteById(VoteId id) {
        return voteDao.getById(id);
    }

    @Override
    public List<Vote> getAllVotes() {
        return voteDao.getAll();
    }

    @Override
    public Optional<Vote> updateVote(Vote vote, VoteId id) {
        return voteDao.update(vote, id);
    }

    @Override
    public Optional<Vote> deleteVote(VoteId id) {
        return voteDao.delete(id);
    }
}
