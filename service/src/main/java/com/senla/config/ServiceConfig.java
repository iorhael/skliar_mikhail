package com.senla.config;

import com.senla.config.modelmapper.ModelMapperConfigurator;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ServiceConfig {

    @Bean
    public ModelMapper modelMapper(List<ModelMapperConfigurator> configurators) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        configurators.forEach(configurator -> configurator.configure(modelMapper));

        return modelMapper;
    }
}
