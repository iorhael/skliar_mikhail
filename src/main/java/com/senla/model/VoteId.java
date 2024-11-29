package com.senla.model;

import com.senla.util.ValidationUtil;

import java.util.UUID;

public record VoteId(UUID userId, UUID pollOptionId) {
    public VoteId {
        ValidationUtil.validateNotNull(pollOptionId);
        ValidationUtil.validateNotNull(userId);
    }
}
