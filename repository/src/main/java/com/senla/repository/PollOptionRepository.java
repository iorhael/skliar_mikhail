package com.senla.repository;

import com.senla.di.annotation.Component;
import com.senla.model.PollOption;
import com.senla.util.SessionManager;
import org.hibernate.Session;

import java.util.Optional;
import java.util.UUID;

@Component
public class PollOptionRepository extends BaseRepository<PollOption, UUID> {
    public PollOptionRepository() {
        super(PollOption.class);
    }

    @Override
    public Optional<PollOption> update(PollOption pollOption, UUID id) {
        Optional<PollOption> result = Optional.empty();

        try (Session session = SessionManager.openSession()) {
            session.beginTransaction();

            PollOption existingPollOption = session.get(PollOption.class, id);

            if (existingPollOption != null) {
                existingPollOption.setDescription(pollOption.getDescription());

                result = Optional.of(existingPollOption);
            }

            session.getTransaction().commit();
        }
        return result;
    }
}
