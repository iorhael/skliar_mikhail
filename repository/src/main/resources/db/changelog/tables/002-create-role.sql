--liquibase formatted sql

--changeset iorhael:1740479135000
CREATE TABLE roles
(
    id      UUID PRIMARY KEY   DEFAULT gen_random_uuid(),
    user_id UUID      NOT NULL REFERENCES users ON DELETE CASCADE,
    name    role_name NOT NULL DEFAULT 'READER',
    UNIQUE (user_id, name)
);

--rollback DROP TABLE roles
