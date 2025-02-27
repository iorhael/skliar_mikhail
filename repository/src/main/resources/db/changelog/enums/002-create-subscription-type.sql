--liquibase formatted sql

--changeset iorhael:1740479120000
CREATE TYPE subscription_type AS ENUM ('FREE', 'PREMIUM', 'PREMIUM_PLUS');

--rollback DROP TYPE subscription_type
