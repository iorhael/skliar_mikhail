--liquibase formatted sql

--changeset iorhael:1740479180000
CREATE TABLE publication_statuses
(
    id             UUID PRIMARY KEY REFERENCES posts ON DELETE CASCADE,
    status_name    post_status NOT NULL DEFAULT 'PUBLISHED',
    scheduled_date TIMESTAMP
);

--rollback DROP TABLE publication_statuses
