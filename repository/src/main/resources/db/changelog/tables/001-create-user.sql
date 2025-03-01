--liquibase formatted sql

--changeset iorhael:1740479134000
CREATE TABLE users
(
    id           UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    username     VARCHAR(30)  NOT NULL UNIQUE,
    email        VARCHAR(255) NOT NULL UNIQUE,
    password     VARCHAR(60)  NOT NULL,
    created_date TIMESTAMPTZ  NOT NULL DEFAULT now()
);

--rollback DROP TABLE users
