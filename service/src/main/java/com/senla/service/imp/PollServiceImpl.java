package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.poll.PollCreateDto;
import com.senla.dto.poll.PollGetDto;
import com.senla.dto.poll.PollUpdateDto;
import com.senla.model.Poll;
import com.senla.repository.PollRepository;
import com.senla.repository.exception.PollNotFoundException;
import com.senla.service.PollService;
import com.senla.service.exception.poll.PollCreateException;
import com.senla.service.exception.poll.PollDeleteException;
import com.senla.service.exception.poll.PollUpdateException;
import com.senla.util.ModelMapperUtil;

import java.util.List;
import java.util.UUID;

@Component
public class PollServiceImpl implements PollService {
    @Autowired
    private PollRepository pollRepository;

    @Override
    public PollGetDto createPoll(PollCreateDto poll) {
        Poll pollEntity = ModelMapperUtil.MODEL_MAPPER.map(poll, Poll.class);

        return pollRepository.create(pollEntity)
                .map(p -> ModelMapperUtil.MODEL_MAPPER.map(p, PollGetDto.class))
                .orElseThrow(() -> new PollCreateException("Can't create poll"));
    }

    @Override
    public PollGetDto getPollById(UUID id) {
        return pollRepository.getById(id)
                .map(poll -> ModelMapperUtil.MODEL_MAPPER.map(poll, PollGetDto.class))
                .orElseThrow(() -> new PollNotFoundException("No poll found"));
    }

    @Override
    public List<PollGetDto> getAllPolls() {
        return pollRepository.getAll().stream()
                .map(poll -> ModelMapperUtil.MODEL_MAPPER.map(poll, PollGetDto.class))
                .toList();
    }

    @Override
    public PollGetDto updatePoll(PollUpdateDto poll, UUID id) {
        Poll pollEntity = ModelMapperUtil.MODEL_MAPPER.map(poll, Poll.class);

        return pollRepository.update(pollEntity, id)
                .map(p -> ModelMapperUtil.MODEL_MAPPER.map(p, PollGetDto.class))
                .orElseThrow(() -> new PollUpdateException("Can't update poll"));
    }

    @Override
    public PollGetDto deletePoll(UUID id) {
        return pollRepository.delete(id)
                .map(category -> ModelMapperUtil.MODEL_MAPPER.map(category, PollGetDto.class))
                .orElseThrow(() -> new PollDeleteException("Can't delete poll"));
    }
}
