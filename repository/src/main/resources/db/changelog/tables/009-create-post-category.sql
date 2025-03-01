--liquibase formatted sql

--changeset iorhael:1740479180000
CREATE TABLE post_categories
(
    post_id     UUID NOT NULL REFERENCES posts ON DELETE CASCADE,
    category_id UUID NOT NULL REFERENCES categories ON DELETE CASCADE,
    PRIMARY KEY (post_id, category_id)
);

--rollback DROP TABLE post_categories
