package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.Poll;
import com.senla.util.SessionManager;
import org.hibernate.Session;

import java.util.Optional;
import java.util.UUID;

@Component
public class PollRepository extends BaseRepository<Poll, UUID> {
    public PollRepository() {
        super(Poll.class);
    }

    @Override
    public Optional<Poll> update(Poll poll, UUID id) {
        Optional<Poll> result = Optional.empty();

        try (Session session = SessionManager.openSession()) {
            session.beginTransaction();

            Poll existingPoll = session.get(Poll.class, id);

            if (existingPoll != null) {
                existingPoll.setDescription(poll.getDescription());

                result = Optional.of(existingPoll);
            }

            session.getTransaction().commit();
        }
        return result;
    }
}
