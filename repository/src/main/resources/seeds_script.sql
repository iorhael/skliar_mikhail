INSERT INTO users (username, email, password)
VALUES ('user1', 'user1@example.com', 'password_hash_1'),
       ('user2', 'user2@example.com', 'password_hash_2'),
       ('admin', 'admin@example.com', 'password_hash_3');

INSERT INTO roles (user_id, name)
VALUES ((SELECT id FROM users WHERE username = 'user1'), 'READER'),
       ((SELECT id FROM users WHERE username = 'user2'), 'AUTHOR'),
       ((SELECT id FROM users WHERE username = 'admin'), 'ADMIN');

INSERT INTO subscription_plans(name, price_per_month)
VALUES ('FREE', 0.0),
       ('PREMIUM', 9.99),
       ('PREMIUM_PLUS', 19.99);

INSERT INTO subscriptions (user_id, subscription_plan_id, expires_date)
VALUES ((SELECT id FROM users WHERE username = 'user1'), (SELECT id FROM subscription_plans WHERE name = 'FREE'), NULL),
       ((SELECT id FROM users WHERE username = 'user2'), (SELECT id FROM subscription_plans WHERE name = 'PREMIUM'),
        now() + INTERVAL '1 month');

INSERT INTO tags (name)
VALUES ('Innovative'),
       ('Tutorial'),
       ('Music'),
       ('Beginner-friendly'),
       ('PostgreSQL');

INSERT INTO categories (name, description, parent_id)
VALUES ('Technology', 'Here you can find information about new developments in technology', NULL),
       ('Lifestyle', 'Useful habits, hobbies and communication', NULL);
INSERT INTO categories (name, description, parent_id)
VALUES ('Programming', 'About programming languages and frameworks',
        (SELECT id FROM categories WHERE name = 'Technology'));
INSERT INTO categories (name, description, parent_id)
VALUES ('Databases', 'About different databases', (SELECT id FROM categories WHERE name = 'Programming'));

INSERT INTO posts (author_id, title, content, publication_date, subscription_plan_id)
VALUES ((SELECT id FROM users WHERE username = 'user1'), 'How to Start Programming',
        'This post explains the basics of programming.', NULL, (SELECT id FROM subscription_plans WHERE name = 'FREE')),
       ((SELECT id FROM users WHERE username = 'user1'), 'How to Play Guitar',
        'This beginner guitar lesson takes you from tuning the guitar and strumming your first chords through to playing your very first guitar solos',
        now(), (SELECT id FROM subscription_plans WHERE name = 'PREMIUM')),
       ((SELECT id FROM users WHERE username = 'user2'), 'Top 5 AI Trends in 2024',
        'This post discusses the top AI trends to watch.', NULL,
        (SELECT id FROM subscription_plans WHERE name = 'FREE')),
       ((SELECT id FROM users WHERE username = 'user2'), 'Some words about PostgreSQL',
        'PostgreSQL is a powerful, open source object-relational database system with over 35 years of active development that has earned it a strong reputation for reliability, feature robustness, and performance.',
        now(), (SELECT id FROM subscription_plans WHERE name = 'PREMIUM_PLUS'));

INSERT INTO publication_statuses (post_id, status_name, scheduled_date)
VALUES ((SELECT id FROM posts WHERE title = 'How to Start Programming'), 'SCHEDULED', now() + INTERVAL '1 day'),
       ((SELECT id FROM posts WHERE title = 'How to Play Guitar'), 'PUBLISHED', NULL),
       ((SELECT id FROM posts WHERE title = 'Top 5 AI Trends in 2024'), 'DRAFT', NULL),
       ((SELECT id FROM posts WHERE title = 'Some words about PostgreSQL'), 'PUBLISHED', NULL);

INSERT INTO post_categories (post_id, category_id)
VALUES ((SELECT id FROM posts WHERE title = 'How to Start Programming'),
        (SELECT id FROM categories WHERE name = 'Programming')),
       ((SELECT id FROM posts WHERE title = 'How to Play Guitar'),
        (SELECT id FROM categories WHERE name = 'Lifestyle')),
       ((SELECT id FROM posts WHERE title = 'Top 5 AI Trends in 2024'),
        (SELECT id FROM categories WHERE name = 'Technology')),
       ((SELECT id FROM posts WHERE title = 'Some words about PostgreSQL'),
        (SELECT id FROM categories WHERE name = 'Databases'));

INSERT INTO post_tags (post_id, tag_id)
VALUES ((SELECT id FROM posts WHERE title = 'How to Start Programming'),
        (SELECT id FROM tags WHERE name = 'Beginner-friendly')),
       ((SELECT id FROM posts WHERE title = 'How to Start Programming'), (SELECT id FROM tags WHERE name = 'Tutorial')),
       ((SELECT id FROM posts WHERE title = 'How to Play Guitar'), (SELECT id FROM tags WHERE name = 'Tutorial')),
       ((SELECT id FROM posts WHERE title = 'How to Play Guitar'),
        (SELECT id FROM tags WHERE name = 'Beginner-friendly')),
       ((SELECT id FROM posts WHERE title = 'How to Play Guitar'), (SELECT id FROM tags WHERE name = 'Music')),
       ((SELECT id FROM posts WHERE title = 'Top 5 AI Trends in 2024'),
        (SELECT id FROM tags WHERE name = 'Innovative')),
       ((SELECT id FROM posts WHERE title = 'Some words about PostgreSQL'),
        (SELECT id FROM tags WHERE name = 'PostgreSQL')),
       ((SELECT id FROM posts WHERE title = 'Some words about PostgreSQL'),
        (SELECT id FROM tags WHERE name = 'Beginner-friendly'));

INSERT INTO comments (post_id, author_id, content, created_date, updated_date, parent_id)
VALUES ((SELECT id FROM posts WHERE title = 'How to Start Programming'),
        (SELECT id FROM users WHERE username = 'user2'), 'Great guide for beginners!', now(), now(), NULL),
       ((SELECT id FROM posts WHERE title = 'Top 5 AI Trends in 2024'), (SELECT id FROM users WHERE username = 'user1'),
        'Really insightful post!', now(), now(), NULL),
       ((SELECT id FROM posts WHERE title = 'Top 5 AI Trends in 2024'), (SELECT id FROM users WHERE username = 'user2'),
        'Boring.', now(), now(), NULL),
       ((SELECT id FROM posts WHERE title = 'Some words about PostgreSQL'),
        (SELECT id FROM users WHERE username = 'user1'), 'MySQL is the best RDBMS!', now(), now(), NULL);

INSERT INTO polls (post_id, author_id, description)
VALUES ((SELECT id FROM posts WHERE title = 'Top 5 AI Trends in 2024'), (SELECT id FROM users WHERE username = 'user2'),
        'Which AI trend is most exciting for you?'),
       (NULL, (SELECT id FROM users WHERE username = 'user1'), 'Best genre of music?'),
       (NULL, (SELECT id FROM users WHERE username = 'admin'), 'Are you satisfied with the quality of the platform?');

INSERT INTO poll_options (poll_id, description)
VALUES ((SELECT id FROM polls WHERE description = 'Which AI trend is most exciting for you?'), 'Generative AI'),
       ((SELECT id FROM polls WHERE description = 'Which AI trend is most exciting for you?'), 'Edge AI'),
       ((SELECT id FROM polls WHERE description = 'Which AI trend is most exciting for you?'), 'Ethical AI'),
       ((SELECT id FROM polls WHERE description = 'Best genre of music?'), 'Rock music'),
       ((SELECT id FROM polls WHERE description = 'Best genre of music?'), 'Pop music'),
       ((SELECT id FROM polls WHERE description = 'Best genre of music?'), 'Folk music'),
       ((SELECT id FROM polls WHERE description = 'Are you satisfied with the quality of the platform?'), 'Yes'),
       ((SELECT id FROM polls WHERE description = 'Are you satisfied with the quality of the platform?'), 'No');

INSERT INTO votes (poll_option_id, user_id)
VALUES ((SELECT id FROM poll_options WHERE description = 'Generative AI'),
        (SELECT id FROM users WHERE username = 'user1')),
       ((SELECT id FROM poll_options WHERE description = 'Ethical AI'),
        (SELECT id FROM users WHERE username = 'user2')),
       ((SELECT id FROM poll_options WHERE description = 'Rock music'),
        (SELECT id FROM users WHERE username = 'user1')),
       ((SELECT id FROM poll_options WHERE description = 'Pop music'), (SELECT id FROM users WHERE username = 'user2')),
       ((SELECT id FROM poll_options WHERE description = 'Folk music'),
        (SELECT id FROM users WHERE username = 'admin')),
       ((SELECT id FROM poll_options WHERE description = 'Yes'), (SELECT id FROM users WHERE username = 'user1')),
       ((SELECT id FROM poll_options WHERE description = 'No'), (SELECT id FROM users WHERE username = 'user2'));
