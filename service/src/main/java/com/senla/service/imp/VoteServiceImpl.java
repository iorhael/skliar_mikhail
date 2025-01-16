package com.senla.service.imp;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
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
import com.senla.util.ModelMapperUtil;
import org.modelmapper.TypeMap;

import java.util.List;

@Component
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public VoteGetDto createVote(VoteCreateDto subscription) {
        Vote voteEntity = ModelMapperUtil.MODEL_MAPPER.map(subscription, Vote.class);
        Vote createdVote = voteRepository.create(voteEntity);

        return ModelMapperUtil.MODEL_MAPPER.map(createdVote, VoteGetDto.class);
    }

    @Override
    public VoteGetDto getVoteById(VoteId id) {
        TypeMap<Vote, VoteGetDto> getMapper = ModelMapperUtil.MODEL_MAPPER.createTypeMap(Vote.class, VoteGetDto.class);
        getMapper.addMappings(
                mapper -> {
                    mapper.map(src -> src.getPollOption().getDescription(), VoteGetDto::setPollOptionDescription);
                    mapper.map(src -> src.getOwner().getUsername(), VoteGetDto::setOwnerName);
                });

        return voteRepository.findById(id)
                .map(vote -> ModelMapperUtil.MODEL_MAPPER.map(vote, VoteGetDto.class))
                .orElseThrow(() -> new ServiceException("Vote not found"));
    }

    @Override
    public List<VoteGetDto> getAllVotes() {
        return voteRepository.findAll().stream()
                .map(vote -> ModelMapperUtil.MODEL_MAPPER.map(vote, VoteGetDto.class))
                .toList();
    }

    @Override
    public VoteGetDto updateVote(VoteUpdateDto vote, VoteId id) {
        Vote voteEntity = ModelMapperUtil.MODEL_MAPPER.map(vote, Vote.class);

        return voteRepository.update(voteEntity, id)
                .map(t -> ModelMapperUtil.MODEL_MAPPER.map(t, VoteGetDto.class))
                .orElseThrow(() -> new VoteUpdateException("Can't update vote"));
    }

    @Override
    public VoteGetDto deleteVote(VoteId id) {
        return voteRepository.deleteById(id)
                .map(vote -> ModelMapperUtil.MODEL_MAPPER.map(vote, VoteGetDto.class))
                .orElseThrow(() -> new VoteDeleteException("Can't delete vote"));
    }
}
