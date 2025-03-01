--liquibase formatted sql

--changeset iorhael:1740479200000
CREATE TABLE polls
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    post_id     UUID REFERENCES posts ON DELETE CASCADE,
    author_id   UUID REFERENCES users ON DELETE SET NULL,
    description TEXT NOT NULL
);

--rollback DROP TABLE polls
