--liquibase formatted sql

--changeset iorhael:1740479210000
CREATE TABLE poll_options
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    poll_id     UUID         NOT NULL REFERENCES polls ON DELETE CASCADE,
    description VARCHAR(255) NOT NULL
);

--rollback DROP TABLE poll_options
