package com.senla.api.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public final class ModelMapperUtil {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private ModelMapperUtil() {
    }

    public static ModelMapper getConfiguredMapper() {
        return modelMapper;
    }
}
