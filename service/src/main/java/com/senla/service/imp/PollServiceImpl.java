package com.senla.service.imp;

import com.senla.dto.poll.PollCreateDto;
import com.senla.dto.poll.PollGetDto;
import com.senla.dto.poll.PollUpdateDto;
import com.senla.model.Poll;
import com.senla.repository.PollRepository;
import com.senla.service.PollService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.poll.PollDeleteException;
import com.senla.service.exception.poll.PollUpdateException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;

    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public PollGetDto createPoll(PollCreateDto poll) {
        Poll pollEntity = modelMapper.map(poll, Poll.class);

        Poll createdPoll = pollRepository.create(pollEntity);

        return modelMapper.map(createdPoll, PollGetDto.class);
    }

    @Transactional
    @Override
    public PollGetDto getPollById(UUID id) {
        return pollRepository.findById(id)
                .map(poll -> modelMapper.map(poll, PollGetDto.class))
                .orElseThrow(() -> new ServiceException("No poll found"));
    }

    @Transactional
    @Override
    public List<PollGetDto> getAllPolls() {
        return pollRepository.findAll().stream()
                .map(poll -> modelMapper.map(poll, PollGetDto.class))
                .toList();
    }

    @Transactional
    @Override
    public PollGetDto updatePoll(PollUpdateDto poll, UUID id) {
        Poll pollEntity = modelMapper.map(poll, Poll.class);

        return pollRepository.update(pollEntity, id)
                .map(p -> modelMapper.map(p, PollGetDto.class))
                .orElseThrow(() -> new PollUpdateException("Can't update poll"));
    }

    @Transactional
    @Override
    public PollGetDto deletePoll(UUID id) {
        return pollRepository.deleteById(id)
                .map(category -> modelMapper.map(category, PollGetDto.class))
                .orElseThrow(() -> new PollDeleteException("Can't delete poll"));
    }
}
