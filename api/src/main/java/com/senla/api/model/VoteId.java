package com.senla.api.model;

import com.senla.api.util.ValidationUtil;

import java.util.UUID;

public record VoteId(UUID userId, UUID pollOptionId) {
    public VoteId {
        ValidationUtil.validateNotNull(pollOptionId);
        ValidationUtil.validateNotNull(userId);
    }
}
