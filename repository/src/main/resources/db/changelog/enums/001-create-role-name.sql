--liquibase formatted sql

--changeset iorhael:1740577602078
CREATE TYPE role_name AS ENUM ('READER', 'AUTHOR', 'ADMIN');

--rollback DROP TYPE role_name
