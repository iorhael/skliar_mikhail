-- CREATE DATABASE blog_platform;

CREATE TABLE users(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(30) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(32) NOT NULL,
    created_date TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TYPE role_name AS ENUM ('Reader', 'Author', 'Admin');

CREATE TABLE roles(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users ON DELETE CASCADE,
    name role_name NOT NULL DEFAULT 'Reader',
    UNIQUE(user_id, name)
);

CREATE TYPE subscription_type AS ENUM ('FREE', 'PREMIUM', 'PREMIUM++');

CREATE TABLE subscription_plans(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name subscription_type NOT NULL,
    price_per_month DECIMAL(8, 2) NOT NULL
);

CREATE TABLE subscriptions(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL UNIQUE REFERENCES users ON DELETE CASCADE,
    subscription_plan_id UUID NOT NULL REFERENCES subscription_plans,
    started_date TIMESTAMP NOT NULL DEFAULT now(),
    expires_date TIMESTAMP
);

CREATE TABLE tags(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tag_name VARCHAR(30) NOT NULL
);

CREATE TABLE categories(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    parent_id UUID REFERENCES categories ON DELETE SET NULL
);

CREATE TABLE posts(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    author_id UUID REFERENCES users ON DELETE SET NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created_date TIMESTAMP NOT NULL DEFAULT now(),
    updated_date TIMESTAMP NOT NULL DEFAULT now(),
    publication_date TIMESTAMP DEFAULT now(),
    subscription_plan_id UUID NOT NULL REFERENCES subscription_plans,
    views_total BIGINT NOT NULL DEFAULT '0'
);

CREATE TYPE post_status AS ENUM ('Draft', 'Published', 'Scheduled');

CREATE TABLE publication_statuses(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    post_id UUID NOT NULL UNIQUE REFERENCES posts ON DELETE CASCADE,
    status_name post_status NOT NULL DEFAULT 'Published',
    scheduled_date TIMESTAMP
);

CREATE TABLE post_categories(
    post_id UUID NOT NULL REFERENCES posts ON DELETE CASCADE,
    category_id UUID NOT NULL REFERENCES categories ON DELETE CASCADE,
    PRIMARY KEY (post_id, category_id)
);

CREATE TABLE post_tags(
    post_id UUID NOT NULL REFERENCES posts ON DELETE CASCADE,
    tag_id UUID NOT NULL REFERENCES tags ON DELETE CASCADE,
    PRIMARY KEY (post_id, tag_id)
);

CREATE TABLE comments(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    post_id UUID NOT NULL REFERENCES posts ON DELETE CASCADE,
    author_id UUID REFERENCES users ON DELETE SET NULL,
    content TEXT NOT NULL,
    created_date TIMESTAMP NOT NULL DEFAULT now(),
    updated_date TIMESTAMP NOT NULL DEFAULT now(),
    parent_id UUID REFERENCES comments ON DELETE SET NULL
);

CREATE TABLE polls(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    post_id UUID REFERENCES posts ON DELETE CASCADE,
    author_id UUID REFERENCES users ON DELETE SET NULL,
    description TEXT NOT NULL
);

CREATE TABLE poll_options(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    poll_id UUID NOT NULL REFERENCES polls ON DELETE CASCADE,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE votes(
    poll_option_id UUID NOT NULL REFERENCES poll_options ON DELETE CASCADE,
    user_id UUID REFERENCES users ON DELETE SET NULL,
    vote_date TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id, poll_option_id)
);
