--liquibase formatted sql

--changeset iorhael:1740479160000
CREATE TABLE categories
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    parent_id   UUID        REFERENCES categories ON DELETE SET NULL
);

--rollback DROP TABLE categories
