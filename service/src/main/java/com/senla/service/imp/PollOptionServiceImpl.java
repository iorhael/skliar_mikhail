package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.pollOption.PollOptionDto;
import com.senla.model.PollOption;
import com.senla.repository.PollOptionRepository;
import com.senla.repository.exception.PollOptionNotFoundException;
import com.senla.service.PollOptionService;
import com.senla.service.exception.pollOption.PollOptionCreateException;
import com.senla.service.exception.pollOption.PollOptionDeleteException;
import com.senla.service.exception.pollOption.PollOptionUpdateException;
import com.senla.util.ModelMapperUtil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PollOptionServiceImpl implements PollOptionService {
    @Autowired
    private PollOptionRepository pollOptionRepository;

    @Override
    public PollOptionDto createPollOption(PollOptionDto pollOption) {
        PollOption pollOptionEntity = ModelMapperUtil.MODEL_MAPPER.map(pollOption, PollOption.class);

        return pollOptionRepository.create(pollOptionEntity)
                .map(p -> ModelMapperUtil.MODEL_MAPPER.map(p, PollOptionDto.class))
                .orElseThrow(() -> new PollOptionCreateException("Can't create poll option"));
    }

    @Override
    public PollOptionDto getPollOptionById(UUID id) {
        return pollOptionRepository.getById(id)
                .map(pollOption -> ModelMapperUtil.MODEL_MAPPER.map(pollOption, PollOptionDto.class))
                .orElseThrow(() -> new PollOptionNotFoundException("No poll option found"));
    }

    @Override
    public List<PollOptionDto> getAllPollOptions() {
        return pollOptionRepository.getAll().stream()
                .map(pollOption -> ModelMapperUtil.MODEL_MAPPER.map(pollOption, PollOptionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PollOptionDto updatePollOption(PollOptionDto pollOption, UUID id) {
        PollOption pollOptionEntity = ModelMapperUtil.MODEL_MAPPER.map(pollOption, PollOption.class);

        return pollOptionRepository.update(pollOptionEntity, id)
                .map(p -> ModelMapperUtil.MODEL_MAPPER.map(p, PollOptionDto.class))
                .orElseThrow(() -> new PollOptionUpdateException("Can't update poll option"));
    }

    @Override
    public PollOptionDto deletePollOption(UUID id) {
        return pollOptionRepository.delete(id)
                .map(pollOption -> ModelMapperUtil.MODEL_MAPPER.map(pollOption, PollOptionDto.class))
                .orElseThrow(() -> new PollOptionDeleteException("Can't delete poll option"));
    }
}
