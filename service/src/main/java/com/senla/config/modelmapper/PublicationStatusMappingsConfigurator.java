package com.senla.config.modelmapper;

import com.senla.dto.publicationStatus.PublicationStatusGetDto;
import com.senla.model.PublicationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PublicationStatusMappingsConfigurator implements ModelMapperConfigurator {

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(PublicationStatus.class, PublicationStatusGetDto.class).addMappings(
                mapper -> mapper.map(src -> src.getPost().getTitle(), PublicationStatusGetDto::setPostTitle)
        );
    }
}
