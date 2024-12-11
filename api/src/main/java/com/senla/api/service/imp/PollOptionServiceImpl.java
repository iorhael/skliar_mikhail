package com.senla.api.service.imp;

import com.senla.api.dao.PollOptionDao;
import com.senla.api.model.PollOption;
import com.senla.api.service.PollOptionService;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PollOptionServiceImpl implements PollOptionService {
    @Autowired
    private PollOptionDao pollOptionDao;

    @Override
    public Optional<PollOption> createPollOption(PollOption pollOption) {
        return pollOptionDao.create(pollOption);
    }

    @Override
    public Optional<PollOption> getPollOptionById(UUID id) {
        return pollOptionDao.getById(id);
    }

    @Override
    public List<PollOption> getAllPollOptions() {
        return pollOptionDao.getAll();
    }

    @Override
    public Optional<PollOption> updatePollOption(PollOption pollOption, UUID id) {
        return pollOptionDao.update(pollOption, id);
    }

    @Override
    public Optional<PollOption> deletePollOption(UUID id) {
        return pollOptionDao.delete(id);
    }
}
