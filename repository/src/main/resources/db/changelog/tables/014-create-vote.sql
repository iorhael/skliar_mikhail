--liquibase formatted sql

--changeset iorhael:1740479220000
CREATE TABLE votes
(
    poll_option_id UUID        NOT NULL REFERENCES poll_options ON DELETE CASCADE,
    user_id        UUID        REFERENCES users ON DELETE SET NULL,
    vote_date      TIMESTAMPTZ NOT NULL DEFAULT now(),
    PRIMARY KEY (user_id, poll_option_id)
);

--rollback DROP TABLE votes
