package com.senla.service.imp;

import com.senla.dto.vote.VoteCreateDto;
import com.senla.dto.vote.VoteGetDto;
import com.senla.dto.vote.VoteUpdateDto;
import com.senla.model.Vote;
import com.senla.model.VoteId;
import com.senla.repository.VoteRepository;
import com.senla.service.VoteService;
import com.senla.service.exception.ServiceException;
import com.senla.service.exception.vote.VoteDeleteException;
import com.senla.service.exception.vote.VoteUpdateException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    private final ModelMapper modelMapper;

    public VoteServiceImpl(VoteRepository voteRepository, ModelMapper modelMapper) {
        this.voteRepository = voteRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public VoteGetDto createVote(VoteCreateDto subscription) {
        Vote voteEntity = modelMapper.map(subscription, Vote.class);

        Vote createdVote = voteRepository.create(voteEntity);

        return modelMapper.map(createdVote, VoteGetDto.class);
    }

    @Transactional
    @Override
    public VoteGetDto getVoteById(VoteId id) {
        return voteRepository.findById(id)
                .map(vote -> modelMapper.map(vote, VoteGetDto.class))
                .orElseThrow(() -> new ServiceException("Vote not found"));
    }

    @Transactional
    @Override
    public List<VoteGetDto> getAllVotes() {
        return voteRepository.findAll().stream()
                .map(vote -> modelMapper.map(vote, VoteGetDto.class))
                .toList();
    }

    @Transactional
    @Override
    public VoteGetDto updateVote(VoteUpdateDto vote, VoteId id) {
        Vote voteEntity = modelMapper.map(vote, Vote.class);

        return voteRepository.update(voteEntity, id)
                .map(t -> modelMapper.map(t, VoteGetDto.class))
                .orElseThrow(() -> new VoteUpdateException("Can't update vote"));
    }

    @Transactional
    @Override
    public VoteGetDto deleteVote(VoteId id) {
        return voteRepository.deleteById(id)
                .map(vote -> modelMapper.map(vote, VoteGetDto.class))
                .orElseThrow(() -> new VoteDeleteException("Can't delete vote"));
    }
}
