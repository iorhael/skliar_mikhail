package com.senla.config.modelmapper;

import com.senla.dto.poll.PollGetDto;
import com.senla.model.Poll;
import org.modelmapper.ModelMapper;


public class PollMappingsConfigurator implements ModelMapperConfigurator {

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Poll.class, PollGetDto.class).addMappings(
                mapper -> {
                    mapper.map(src -> src.getAuthor().getUsername(), PollGetDto::setAuthorName);
                    mapper.map(src -> src.getPost().getTitle(), PollGetDto::setPostTitle);
                });
    }
}
