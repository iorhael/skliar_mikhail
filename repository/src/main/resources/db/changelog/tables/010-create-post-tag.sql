--liquibase formatted sql

--changeset iorhael:1740479190000
CREATE TABLE post_tags
(
    post_id UUID NOT NULL REFERENCES posts ON DELETE CASCADE,
    tag_id  UUID NOT NULL REFERENCES tags ON DELETE CASCADE,
    PRIMARY KEY (post_id, tag_id)
);

--rollback DROP TABLE post_tags
