package com.senla.controller.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Builder
@Getter
public class ResponseInfoDto {

    @Builder.Default
    private final Instant timestamp = Instant.now();

    @Builder.Default
    private final HttpStatus status = HttpStatus.OK;

    private final String message;
}
