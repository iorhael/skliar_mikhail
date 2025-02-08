package com.senla.config.modelmapper;

import com.senla.dto.pollOption.PollOptionGetDto;
import com.senla.model.PollOption;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PollOptionMappingsConfigurator implements ModelMapperConfigurator {

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(PollOption.class, PollOptionGetDto.class).addMappings(
                mapper -> mapper.using(ConverterUtil.VOTES_TO_COUNT)
                        .map(PollOption::getVotes, PollOptionGetDto::setVotesCount)
        );
    }
}
