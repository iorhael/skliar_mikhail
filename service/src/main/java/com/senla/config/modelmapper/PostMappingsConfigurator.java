package com.senla.config.modelmapper;

import com.senla.dto.post.PostDetailedDto;
import com.senla.dto.post.PostPreviewDto;
import com.senla.model.Post;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostMappingsConfigurator implements ModelMapperConfigurator {

    @Override
    public void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Post.class, PostPreviewDto.class).addMappings(
                mapper -> {
                    mapper.map(src -> src.getAuthor().getUsername(), PostPreviewDto::setAuthorName);
                    mapper.using(ConverterUtil.CATEGORIES_TO_NAMES)
                            .map(Post::getCategories, PostPreviewDto::setCategories);
                }
        );

        modelMapper.createTypeMap(Post.class, PostDetailedDto.class).addMappings(
                mapper -> {
                    mapper.map(src -> src.getAuthor().getUsername(), PostDetailedDto::setAuthorName);
                    mapper.using(ConverterUtil.CATEGORIES_TO_NAMES)
                            .map(Post::getCategories, PostDetailedDto::setCategories);
                    mapper.using(ConverterUtil.TAGS_TO_NAMES)
                            .map(Post::getTags, PostDetailedDto::setTags);
                }
        );
    }
}
