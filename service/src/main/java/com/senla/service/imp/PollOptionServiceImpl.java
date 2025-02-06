package com.senla.service.imp;

import com.senla.aspect.Benchmarked;
import com.senla.dto.pollOption.PollOptionCreateDto;
import com.senla.dto.pollOption.PollOptionGetDto;
import com.senla.model.Poll;
import com.senla.model.PollOption;
import com.senla.repository.PollOptionRepository;
import com.senla.repository.PollRepository;
import com.senla.service.PollOptionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Benchmarked
@RequiredArgsConstructor
public class PollOptionServiceImpl implements PollOptionService {
    public static final String POLL_OPTION_NOT_FOUND = "PollOption not found";

    private final PollOptionRepository pollOptionRepository;

    private final PollRepository pollRepository;

    private final ModelMapper modelMapper;

    @Override
    public PollOptionGetDto createPollOption(PollOptionCreateDto pollOptionCreateDto) {
        PollOption pollOption = modelMapper.map(pollOptionCreateDto, PollOption.class);

        Poll poll = pollRepository.getReferenceById(pollOptionCreateDto.getPollId());
        pollOption.setPoll(poll);

        pollOptionRepository.save(pollOption);

        return modelMapper.map(pollOption, PollOptionGetDto.class);
    }

    @Override
    public PollOptionGetDto getPollOptionBy(UUID id) {
        return pollOptionRepository.findWithVotesById(id)
                .map(pollOption -> modelMapper.map(pollOption, PollOptionGetDto.class))
                .orElseThrow(() -> new EntityNotFoundException(POLL_OPTION_NOT_FOUND));
    }

    @Override
    public List<PollOptionGetDto> getAllPollOptions(UUID pollId) {
        return pollOptionRepository.findByPollId(pollId)
                .stream()
                .map(pollOption -> modelMapper.map(pollOption, PollOptionGetDto.class))
                .toList();
    }

    @Override
    @Transactional
    public PollOptionGetDto updatePollOption(PollOptionCreateDto pollOptionCreateDto, UUID id) {
        PollOption pollOption = pollOptionRepository.findWithVotesById(id)
                .orElseThrow(() -> new EntityNotFoundException(POLL_OPTION_NOT_FOUND));

        Poll poll = pollRepository.getReferenceById(pollOptionCreateDto.getPollId());
        pollOption.setPoll(poll);

        pollOption.setDescription(pollOptionCreateDto.getDescription());

        return modelMapper.map(pollOption, PollOptionGetDto.class);
    }

    public void deletePollOption(UUID id) {
        pollOptionRepository.deleteById(id);
    }
}
