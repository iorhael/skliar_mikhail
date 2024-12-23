package com.senla.api.service.imp;

import com.senla.api.dao.PollDao;
import com.senla.api.dao.exception.PollNotFoundException;
import com.senla.api.dto.poll.PollCreateDto;
import com.senla.api.dto.poll.PollGetDto;
import com.senla.api.dto.poll.PollUpdateDto;
import com.senla.api.model.Poll;
import com.senla.api.service.PollService;
import com.senla.api.service.exception.poll.PollCreateException;
import com.senla.api.service.exception.poll.PollDeleteException;
import com.senla.api.service.exception.poll.PollUpdateException;
import com.senla.api.util.ModelMapperUtil;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PollServiceImpl implements PollService {
    @Autowired
    private PollDao pollDao;

    @Override
    public PollGetDto createPoll(PollCreateDto poll) {
        Poll pollEntity = ModelMapperUtil.getConfiguredMapper().map(poll, Poll.class);

        return pollDao.create(pollEntity)
                .map(p -> ModelMapperUtil.getConfiguredMapper().map(p, PollGetDto.class))
                .orElseThrow(() -> new PollCreateException("Can't create poll"));
    }

    @Override
    public PollGetDto getPollById(UUID id) {
        return pollDao.getById(id)
                .map(poll -> ModelMapperUtil.getConfiguredMapper().map(poll, PollGetDto.class))
                .orElseThrow(() -> new PollNotFoundException("No poll found"));
    }

    @Override
    public List<PollGetDto> getAllPolls() {
        List<Poll> polls = pollDao.getAll();
        List<PollGetDto> pollGetDtos = new ArrayList<>();

        for (Poll poll : polls) {
            PollGetDto pollGetDto = ModelMapperUtil.getConfiguredMapper().map(poll, PollGetDto.class);
            pollGetDtos.add(pollGetDto);
        }

        return pollGetDtos;
    }

    @Override
    public PollGetDto updatePoll(PollUpdateDto poll, UUID id) {
        Poll pollEntity = ModelMapperUtil.getConfiguredMapper().map(poll, Poll.class);

        return pollDao.update(pollEntity, id)
                .map(p -> ModelMapperUtil.getConfiguredMapper().map(p, PollGetDto.class))
                .orElseThrow(() -> new PollUpdateException("Can't update poll"));
    }

    @Override
    public PollGetDto deletePoll(UUID id) {
        return pollDao.delete(id)
                .map(category -> ModelMapperUtil.getConfiguredMapper().map(category, PollGetDto.class))
                .orElseThrow(() -> new PollDeleteException("Can't delete poll"));
    }
}
