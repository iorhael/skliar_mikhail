--liquibase formatted sql

--changeset iorhael:1740479170000
CREATE TABLE posts
(
    id                   UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    author_id            UUID         REFERENCES users ON DELETE SET NULL,
    title                VARCHAR(255) NOT NULL,
    content              TEXT         NOT NULL,
    created_date         TIMESTAMPTZ  NOT NULL DEFAULT now(),
    updated_date         TIMESTAMPTZ  NOT NULL DEFAULT now(),
    publication_date     TIMESTAMPTZ           DEFAULT now(),
    subscription_plan_id UUID         NOT NULL REFERENCES subscription_plans,
    views_total          BIGINT       NOT NULL DEFAULT '0'
);

--rollback DROP TABLE posts
