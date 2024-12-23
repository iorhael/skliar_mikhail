package com.senla.api.service.imp;

import com.senla.api.dao.PollOptionDao;
import com.senla.api.dao.exception.PollOptionNotFoundException;
import com.senla.api.model.PollOption;
import com.senla.api.service.PollOptionService;
import com.senla.api.service.exception.pollOption.PollOptionCreateException;
import com.senla.api.service.exception.pollOption.PollOptionDeleteException;
import com.senla.api.service.exception.pollOption.PollOptionUpdateException;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PollOptionServiceImpl implements PollOptionService {
    @Autowired
    private PollOptionDao pollOptionDao;

    @Override
    public PollOption createPollOption(PollOption pollOption) {
        return pollOptionDao.create(pollOption).orElseThrow(() -> new PollOptionCreateException("Can't create poll option"));
    }

    @Override
    public PollOption getPollOptionById(UUID id) {
        return pollOptionDao.getById(id).orElseThrow(() -> new PollOptionNotFoundException("No poll option found"));
    }

    @Override
    public List<PollOption> getAllPollOptions() {
        return pollOptionDao.getAll();
    }

    @Override
    public PollOption updatePollOption(PollOption pollOption, UUID id) {
        return pollOptionDao.update(pollOption, id).orElseThrow(() -> new PollOptionUpdateException("Can't update poll option"));
    }

    @Override
    public PollOption deletePollOption(UUID id) {
        return pollOptionDao.delete(id).orElseThrow(() -> new PollOptionDeleteException("Can't delete poll option"));
    }
}
