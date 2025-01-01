package com.senla.model;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VoteId(@NotNull UUID userId, @NotNull UUID pollOptionId) {
}
