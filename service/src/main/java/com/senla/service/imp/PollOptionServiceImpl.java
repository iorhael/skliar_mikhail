package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.pollOption.PollOptionCreateDto;
import com.senla.dto.pollOption.PollOptionGetDto;
import com.senla.model.PollOption;
import com.senla.repository.PollOptionRepository;
import com.senla.service.PollOptionService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.pollOption.PollOptionDeleteException;
import com.senla.service.exception.pollOption.PollOptionUpdateException;
import com.senla.util.ModelMapperUtil;

import java.util.List;
import java.util.UUID;

@Component
public class PollOptionServiceImpl implements PollOptionService {

    @Autowired
    private PollOptionRepository pollOptionRepository;

    @Override
    public PollOptionGetDto createPollOption(PollOptionCreateDto pollOption) {
        PollOption pollOptionEntity = ModelMapperUtil.MODEL_MAPPER.map(pollOption, PollOption.class);
        PollOption createdPoll = pollOptionRepository.create(pollOptionEntity);

        return ModelMapperUtil.MODEL_MAPPER.map(createdPoll, PollOptionGetDto.class);
    }

    @Override
    public PollOptionGetDto getPollOptionById(UUID id) {
        return pollOptionRepository.findById(id)
                .map(pollOption -> ModelMapperUtil.MODEL_MAPPER.map(pollOption, PollOptionGetDto.class))
                .orElseThrow(() -> new ServiceException("No poll option found"));
    }

    @Override
    public List<PollOptionGetDto> getAllPollOptions() {
        return pollOptionRepository.findAll().stream()
                .map(pollOption -> ModelMapperUtil.MODEL_MAPPER.map(pollOption, PollOptionGetDto.class))
                .toList();
    }

    @Override
    public PollOptionGetDto updatePollOption(PollOptionGetDto pollOption, UUID id) {
        PollOption pollOptionEntity = ModelMapperUtil.MODEL_MAPPER.map(pollOption, PollOption.class);

        return pollOptionRepository.update(pollOptionEntity, id)
                .map(p -> ModelMapperUtil.MODEL_MAPPER.map(p, PollOptionGetDto.class))
                .orElseThrow(() -> new PollOptionUpdateException("Can't update poll option"));
    }

    @Override
    public PollOptionGetDto deletePollOption(UUID id) {
        return pollOptionRepository.deleteById(id)
                .map(pollOption -> ModelMapperUtil.MODEL_MAPPER.map(pollOption, PollOptionGetDto.class))
                .orElseThrow(() -> new PollOptionDeleteException("Can't delete poll option"));
    }
}
