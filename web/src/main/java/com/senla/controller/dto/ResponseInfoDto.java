package com.senla.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ResponseInfoDto {

    @Builder.Default
    private final Instant timestamp = Instant.now();

    private final String message;
}
