package com.senla.DAO;

import com.senla.model.Vote;

import java.util.Optional;
import java.util.UUID;

public interface VoteDAO extends ParentDAO<Vote> {
    Optional<Vote> update(Vote vote, UUID pollOptionId, UUID userId);

    Optional<Vote> delete(UUID pollOptionId, UUID userId);

    @Override
    default Optional<Vote> update(Vote vote, UUID entityId) {
        throw new UnsupportedOperationException("Use the update method with pollOptionId and userId instead");
    }

    @Override
    default Optional<Vote> delete(UUID entityId) {
        throw new UnsupportedOperationException("Use the delete method with pollOptionId and userId instead");
    }
}
