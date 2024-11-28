package com.senla.dao;

import com.senla.model.Vote;

import java.util.Optional;
import java.util.UUID;

public interface VoteDao extends ParentDao<Vote> {
    Optional<Vote> getById(UUID pollOptionId, UUID userId);

    Optional<Vote> update(Vote vote, UUID pollOptionId, UUID userId);

    Optional<Vote> delete(UUID pollOptionId, UUID userId);

    @Override
    default Optional<Vote> getById(UUID entityId) {
        throw new UnsupportedOperationException("Use getById(UUID, UUID) instead");
    }

    @Override
    default Optional<Vote> update(Vote vote, UUID entityId) {
        throw new UnsupportedOperationException("Use update(Vote, UUID, UUID) instead");
    }

    @Override
    default Optional<Vote> delete(UUID entityId) {
        throw new UnsupportedOperationException("Use delete(Vote, UUID, UUID) instead");
    }
}
