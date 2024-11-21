-- CREATE DATABASE blog_platform;

CREATE TABLE users(
    user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_name VARCHAR(30) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_digest VARCHAR(255) NOT NULL,
    is_admin BOOLEAN NOT NULL DEFAULT 'false',
    created_date TIMESTAMP NOT NULL
);

CREATE TABLE subscriptions(
    subscription_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL UNIQUE REFERENCES users(user_id) ON DELETE CASCADE,
    started_date TIMESTAMP NOT NULL,
    expires_date TIMESTAMP NOT NULL
);

CREATE TABLE tags(
    tag_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tag_name VARCHAR(30) NOT NULL
);

CREATE TABLE categories(
    category_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    category_name VARCHAR(50) NOT NULL UNIQUE,
    parent_id UUID REFERENCES categories(category_id) ON DELETE SET NULL
);

CREATE TABLE posts(
    post_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    author_id UUID REFERENCES users(user_id) ON DELETE SET NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    is_premium BOOLEAN NOT NULL DEFAULT 'false',
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP NOT NULL,
    publication_date TIMESTAMP,
    views_total BIGINT NOT NULL DEFAULT '0'
);

CREATE TYPE post_status AS ENUM ('Draft', 'Published', 'Scheduled');

CREATE TABLE publication_statuses(
    status_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    post_id UUID NOT NULL UNIQUE REFERENCES posts(post_id) ON DELETE CASCADE,
    status_name post_status NOT NULL,
    scheduled_date TIMESTAMP
);

CREATE TABLE post_categories(
    post_id UUID NOT NULL REFERENCES posts(post_id) ON DELETE CASCADE,
    category_id UUID NOT NULL REFERENCES categories(category_id) ON DELETE CASCADE,
    PRIMARY KEY (post_id, category_id)
);

CREATE TABLE post_tags(
    post_id UUID NOT NULL REFERENCES posts(post_id) ON DELETE CASCADE,
    tag_id UUID NOT NULL REFERENCES tags(tag_id) ON DELETE CASCADE,
    PRIMARY KEY (post_id, tag_id)
);

CREATE TABLE comments(
    comment_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    post_id UUID NOT NULL REFERENCES posts(post_id) ON DELETE CASCADE,
    author_id UUID REFERENCES users(user_id) ON DELETE SET NULL,
    content TEXT NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP NOT NULL,
    parent_id UUID REFERENCES comments(comment_id) ON DELETE SET NULL
);

CREATE TABLE polls(
    poll_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    post_id UUID REFERENCES posts(post_id) ON DELETE CASCADE,
    author_id UUID REFERENCES users(user_id) ON DELETE SET NULL,
    description TEXT NOT NULL
);

CREATE TABLE poll_options(
    poll_option_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    poll_id UUID NOT NULL REFERENCES polls(poll_id) ON DELETE CASCADE,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE votes(
    vote_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    poll_option_id UUID NOT NULL REFERENCES poll_options(poll_option_id) ON DELETE CASCADE,
    user_id UUID REFERENCES users(user_id) ON DELETE SET NULL,
    vote_date TIMESTAMP NOT NULL,
    UNIQUE(user_id, poll_option_id)
);
