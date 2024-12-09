package com.senla.api.service.imp;

import com.senla.api.dao.PollDao;
import com.senla.api.model.Poll;
import com.senla.api.service.PollService;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PollServiceImpl implements PollService {
    @Autowired
    private PollDao pollDao;

    @Override
    public Optional<Poll> createPoll(Poll poll) {
        return pollDao.create(poll);
    }

    @Override
    public Optional<Poll> getPollById(UUID id) {
        return pollDao.getById(id);
    }

    @Override
    public List<Poll> getAllPolls() {
        return pollDao.getAll();
    }

    @Override
    public Optional<Poll> updatePoll(Poll poll, UUID id) {
        return pollDao.update(poll, id);
    }

    @Override
    public Optional<Poll> deletePoll(UUID id) {
        return pollDao.delete(id);
    }
}
