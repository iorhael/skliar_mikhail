package com.senla.service.imp;

import com.senla.dto.pollOption.PollOptionCreateDto;
import com.senla.dto.pollOption.PollOptionGetDto;
import com.senla.model.PollOption;
import com.senla.repository.PollOptionRepository;
import com.senla.service.PollOptionService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.pollOption.PollOptionDeleteException;
import com.senla.service.exception.pollOption.PollOptionUpdateException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PollOptionServiceImpl implements PollOptionService {

    private final PollOptionRepository pollOptionRepository;

    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public PollOptionGetDto createPollOption(PollOptionCreateDto pollOption) {
        PollOption pollOptionEntity = modelMapper.map(pollOption, PollOption.class);

        PollOption createdPoll = pollOptionRepository.create(pollOptionEntity);

        return modelMapper.map(createdPoll, PollOptionGetDto.class);
    }

    @Transactional
    @Override
    public PollOptionGetDto getPollOptionById(UUID id) {
        return pollOptionRepository.findById(id)
                .map(pollOption -> modelMapper.map(pollOption, PollOptionGetDto.class))
                .orElseThrow(() -> new ServiceException("No poll option found"));
    }

    @Transactional
    @Override
    public List<PollOptionGetDto> getAllPollOptions() {
        return pollOptionRepository.findAll().stream()
                .map(pollOption -> modelMapper.map(pollOption, PollOptionGetDto.class))
                .toList();
    }

    @Transactional
    @Override
    public PollOptionGetDto updatePollOption(PollOptionGetDto pollOption, UUID id) {
        PollOption pollOptionEntity = modelMapper.map(pollOption, PollOption.class);

        return pollOptionRepository.update(pollOptionEntity, id)
                .map(p -> modelMapper.map(p, PollOptionGetDto.class))
                .orElseThrow(() -> new PollOptionUpdateException("Can't update poll option"));
    }

    @Transactional
    @Override
    public PollOptionGetDto deletePollOption(UUID id) {
        return pollOptionRepository.deleteById(id)
                .map(pollOption -> modelMapper.map(pollOption, PollOptionGetDto.class))
                .orElseThrow(() -> new PollOptionDeleteException("Can't delete poll option"));
    }
}
