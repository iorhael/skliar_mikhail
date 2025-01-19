package com.senla.config.modelmapper;

import com.senla.dto.vote.VoteGetDto;
import com.senla.model.Vote;
import org.modelmapper.ModelMapper;

public class VoteMappingsConfigurator implements ModelMapperConfigurator {

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Vote.class, VoteGetDto.class).addMappings(
                mapper -> {
                    mapper.map(src -> src.getPollOption().getDescription(), VoteGetDto::setPollOptionDescription);
                    mapper.map(src -> src.getOwner().getUsername(), VoteGetDto::setOwnerName);
                });
    }
}
