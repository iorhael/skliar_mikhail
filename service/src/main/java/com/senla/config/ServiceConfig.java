package com.senla.config;

import com.senla.config.modelmapper.CommentMappingsConfigurator;
import com.senla.config.modelmapper.ModelMapperConfigurator;
import com.senla.config.modelmapper.PollMappingsConfigurator;
import com.senla.config.modelmapper.PostMappingsConfigurator;
import com.senla.config.modelmapper.VoteMappingsConfigurator;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ServiceConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<ModelMapperConfigurator> configurators = List.of(
                new CommentMappingsConfigurator(),
                new PollMappingsConfigurator(),
                new PostMappingsConfigurator(),
                new VoteMappingsConfigurator()
        );

        configurators.forEach(configurator -> configurator.configure(modelMapper));

        return modelMapper;
    }
}
