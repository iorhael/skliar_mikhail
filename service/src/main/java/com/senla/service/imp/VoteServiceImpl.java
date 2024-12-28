package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.vote.VoteDto;
import com.senla.model.Vote;
import com.senla.model.VoteId;
import com.senla.repository.VoteRepository;
import com.senla.repository.exception.VoteNotFoundException;
import com.senla.service.VoteService;
import com.senla.service.exception.vote.VoteCreateException;
import com.senla.service.exception.vote.VoteDeleteException;
import com.senla.service.exception.vote.VoteUpdateException;
import com.senla.util.ModelMapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteRepository voteRepository;

    @Override
    public VoteDto createVote(VoteDto subscription) {
        Vote voteEntity = ModelMapperUtil.MODEL_MAPPER.map(subscription, Vote.class);

        return voteRepository.create(voteEntity)
                .map(v -> ModelMapperUtil.MODEL_MAPPER.map(v, VoteDto.class))
                .orElseThrow(() -> new VoteCreateException("Can't create vote"));
    }

    @Override
    public VoteDto getVoteById(VoteId id) {
        return voteRepository.getById(id)
                .map(vote -> ModelMapperUtil.MODEL_MAPPER.map(vote, VoteDto.class))
                .orElseThrow(() -> new VoteNotFoundException("Vote not found"));
    }

    @Override
    public List<VoteDto> getAllVotes() {
        return voteRepository.getAll().stream()
                .map(vote -> ModelMapperUtil.MODEL_MAPPER.map(vote, VoteDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public VoteDto updateVote(VoteDto vote, VoteId id) {
        Vote voteEntity = ModelMapperUtil.MODEL_MAPPER.map(vote, Vote.class);

        return voteRepository.update(voteEntity, id)
                .map(t -> ModelMapperUtil.MODEL_MAPPER.map(t, VoteDto.class))
                .orElseThrow(() -> new VoteUpdateException("Can't update vote"));
    }

    @Override
    public VoteDto deleteVote(VoteId id) {
        return voteRepository.delete(id)
                .map(vote -> ModelMapperUtil.MODEL_MAPPER.map(vote, VoteDto.class))
                .orElseThrow(() -> new VoteDeleteException("Can't delete vote"));
    }
}
