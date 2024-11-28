package com.senla.DAO;

import com.senla.model.Vote;

import java.util.Optional;
import java.util.UUID;

public interface VoteDAO extends ParentDAO<Vote> {
    Optional<Vote> getById(UUID pollOptionId, UUID userId);

    Optional<Vote> update(Vote vote, UUID pollOptionId, UUID userId);

    Optional<Vote> delete(UUID pollOptionId, UUID userId);

    @Override
    default Optional<Vote> getById(UUID entityId) {
        throw new UnsupportedOperationException("Use the getById(UUID, UUID) instead");
    }

    @Override
    default Optional<Vote> update(Vote vote, UUID entityId) {
        throw new UnsupportedOperationException("Use the update(Vote, UUID, UUID) instead");
    }

    @Override
    default Optional<Vote> delete(UUID entityId) {
        throw new UnsupportedOperationException("Use the delete(Vote, UUID, UUID) instead");
    }
}
