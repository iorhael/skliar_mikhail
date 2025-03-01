--liquibase formatted sql

--changeset iorhael:1740479150000
CREATE TABLE tags
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(30) NOT NULL
);

--rollback DROP TABLE tags
