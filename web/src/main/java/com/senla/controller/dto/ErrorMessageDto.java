package com.senla.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
public class ErrorMessageDto {
    private static final String BAD_REQUEST = "Bad Request";

    @Builder.Default
    private final Instant timestamp = Instant.now();

    @Builder.Default
    private final String error = BAD_REQUEST;

    @Builder.Default
    private final Map<String, String> details = new HashMap<>();

    private final String path;
}
