--liquibase formatted sql

--changeset iorhael:1740479130000
CREATE TYPE post_status AS ENUM ('DRAFT', 'PUBLISHED', 'SCHEDULED');

--rollback DROP TYPE post_status
