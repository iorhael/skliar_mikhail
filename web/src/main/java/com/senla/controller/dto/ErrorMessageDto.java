package com.senla.controller.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
public class ErrorMessageDto {

    @Builder.Default
    private final Instant timestamp = Instant.now();

    @Builder.Default
    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    private final String error;

    @Builder.Default
    private final Map<String, String> details = new HashMap<>();

    private final String path;
}
