package com.senla.config.modelmapper;

import com.senla.dto.comment.CommentGetDto;
import com.senla.model.Comment;
import org.modelmapper.ModelMapper;

public class CommentMappingsConfigurator implements ModelMapperConfigurator {

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Comment.class, CommentGetDto.class).addMappings(
                mapper -> mapper.map(src -> src.getAuthor().getUsername(), CommentGetDto::setAuthorName)
        );
    }
}
