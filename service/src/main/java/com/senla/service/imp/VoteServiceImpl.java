package com.senla.service.imp;

import com.senla.aspect.Benchmarked;
import com.senla.dto.vote.VoteCreateDto;
import com.senla.dto.vote.VoteGetDto;
import com.senla.dto.vote.VoteUpdateDto;
import com.senla.model.PollOption;
import com.senla.model.User;
import com.senla.model.Vote;
import com.senla.model.VoteId;
import com.senla.repository.PollOptionRepository;
import com.senla.repository.UserRepository;
import com.senla.repository.VoteRepository;
import com.senla.service.VoteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Benchmarked
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    public static final String VOTE_NOT_FOUND = "Vote not found";

    private final VoteRepository voteRepository;

    private final PollOptionRepository pollOptionRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public VoteGetDto createVote(VoteCreateDto voteCreateDto) {
        Vote vote = modelMapper.map(voteCreateDto, Vote.class);

        User owner = userRepository.getReferenceById(voteCreateDto.getOwnerId());
        PollOption pollOption = pollOptionRepository.getReferenceById(voteCreateDto.getPollOptionId());

        vote.setOwner(owner);
        vote.setPollOption(pollOption);

        voteRepository.save(vote);

        return modelMapper.map(vote, VoteGetDto.class);
    }

    @Override
    public VoteGetDto getVoteBy(VoteId id) {
        return voteRepository.findById(id)
                .map(vote -> modelMapper.map(vote, VoteGetDto.class))
                .orElseThrow(() -> new EntityNotFoundException(VOTE_NOT_FOUND));
    }

    @Override
    public List<VoteGetDto> getAllVotes() {
        return voteRepository.findAll()
                .stream()
                .map(vote -> modelMapper.map(vote, VoteGetDto.class))
                .toList();
    }

    @Override
    @Transactional
    public VoteGetDto updateVote(VoteUpdateDto voteUpdateDto, VoteId id) {
        Vote vote = voteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(VOTE_NOT_FOUND));

        PollOption pollOption = pollOptionRepository.getReferenceById(voteUpdateDto.getPollOptionId());
        vote.setPollOption(pollOption);

        return modelMapper.map(vote, VoteGetDto.class);
    }

    @Override
    public void deleteVote(VoteId id) {
        voteRepository.deleteById(id);
    }
}
