package com.senla.config.modelmapper;

import com.senla.dto.post.PostGetDto;
import com.senla.model.Post;
import org.modelmapper.ModelMapper;

public class PostMappingsConfigurator implements ModelMapperConfigurator {

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Post.class, PostGetDto.class).addMappings(
                mapper -> mapper.map(src -> src.getAuthor().getUsername(), PostGetDto::setAuthorName)
        );
    }
}
