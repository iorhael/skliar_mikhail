--liquibase formatted sql

--changeset iorhael:1740479140000
CREATE TABLE subscriptions
(
    id                   UUID PRIMARY KEY REFERENCES users ON DELETE CASCADE,
    subscription_plan_id UUID        NOT NULL REFERENCES subscription_plans,
    started_date         TIMESTAMPTZ NOT NULL DEFAULT now(),
    expires_date         TIMESTAMPTZ
);

--rollback DROP TABLE subscriptions
