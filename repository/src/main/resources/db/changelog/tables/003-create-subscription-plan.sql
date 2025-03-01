--liquibase formatted sql

--changeset iorhael:1740479136000
CREATE TABLE subscription_plans
(
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name            subscription_type NOT NULL,
    price_per_month DECIMAL(8, 2)     NOT NULL
);

--rollback DROP TABLE subscription_plans
