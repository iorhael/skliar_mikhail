package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Vote;
import com.senla.model.VoteId;
import com.senla.util.SessionManager;
import org.hibernate.Session;

import java.util.Optional;

@Component
public class VoteRepository extends BaseRepository<Vote, VoteId> {
    public VoteRepository() {
        super(Vote.class);
    }

    @Override
    public Optional<Vote> update(Vote vote, VoteId id) {
        Optional<Vote> result = Optional.empty();

        try (Session session = SessionManager.openSession()) {
            session.beginTransaction();

            Vote existingVote = session.get(Vote.class, id);

            if (existingVote != null) {
                existingVote.setPollOption(vote.getPollOption());

                result = Optional.of(existingVote);
            }

            session.getTransaction().commit();
        }
        return result;
    }
}
