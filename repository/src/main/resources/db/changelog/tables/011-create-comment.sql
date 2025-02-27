--liquibase formatted sql

--changeset iorhael:1740479191200
CREATE TABLE comments
(
    id           UUID PRIMARY KEY     DEFAULT gen_random_uuid(),
    post_id      UUID        NOT NULL REFERENCES posts ON DELETE CASCADE,
    author_id    UUID        REFERENCES users ON DELETE SET NULL,
    content      TEXT        NOT NULL,
    created_date TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_date TIMESTAMPTZ NOT NULL DEFAULT now(),
    parent_id    UUID        REFERENCES comments ON DELETE SET NULL
);

--rollback DROP TABLE comments
