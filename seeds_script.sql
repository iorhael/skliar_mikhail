INSERT INTO users (user_name, email, password_digest, is_admin, created_date)
VALUES
    ('user1', 'user1@example.com', 'password_hash_1', false, now()),
    ('user2', 'user2@example.com', 'password_hash_2', false, now()),
    ('admin', 'admin@example.com', 'password_hash_3', true, now());


INSERT INTO subscriptions (user_id, started_date, expires_date)
VALUES
    ((SELECT user_id FROM users WHERE user_name = 'user1'), now(), now() + INTERVAL '1 month'),
    ((SELECT user_id FROM users WHERE user_name = 'user2'), now(), now() + INTERVAL '6 months');


INSERT INTO tags (tag_name)
VALUES
    ('Innovative'),
    ('Tutorial'),
    ('Music'),
    ('Beginner-friendly'),
    ('PostgreSQL');


INSERT INTO categories (category_name, parent_id)
VALUES
    ('Technology', NULL),
    ('Lifestyle', NULL);
INSERT INTO categories (category_name, parent_id)
VALUES
    ('Programming', (SELECT category_id FROM categories WHERE category_name = 'Technology'));
INSERT INTO categories (category_name, parent_id)
VALUES
    ('Databases', (SELECT category_id FROM categories WHERE category_name = 'Programming'));


INSERT INTO posts (author_id, title, content, is_premium, created_date, updated_date, publication_date)
VALUES
    ((SELECT user_id FROM users WHERE user_name = 'user1'), 'How to Start Programming', 'This post explains the basics of programming.', false, now(), now(), NULL),
    ((SELECT user_id FROM users WHERE user_name = 'user1'), 'How to Play Guitar', 'This beginner guitar lesson takes you from tuning the guitar and strumming your first chords through to playing your very first guitar solos', true, now(), now(), now()),
    ((SELECT user_id FROM users WHERE user_name = 'user2'), 'Top 5 AI Trends in 2024', 'This post discusses the top AI trends to watch.', true, now(), now(), NULL),
    ((SELECT user_id FROM users WHERE user_name = 'user2'), 'Some words about PostgreSQL', 'PostgreSQL is a powerful, open source object-relational database system with over 35 years of active development that has earned it a strong reputation for reliability, feature robustness, and performance.', true, now(), now(), now());


INSERT INTO publication_statuses (post_id, status_name, scheduled_date)
VALUES
    ((SELECT post_id FROM posts WHERE title = 'How to Start Programming'), 'Scheduled', now() + INTERVAL '1 day'),
    ((SELECT post_id FROM posts WHERE title = 'How to Play Guitar'), 'Published', NULL),
    ((SELECT post_id FROM posts WHERE title = 'Top 5 AI Trends in 2024'), 'Draft', NULL),
    ((SELECT post_id FROM posts WHERE title = 'Some words about PostgreSQL'), 'Published', NULL);


INSERT INTO post_categories (post_id, category_id)
VALUES
    ((SELECT post_id FROM posts WHERE title = 'How to Start Programming'), (SELECT category_id FROM categories WHERE category_name = 'Programming')),
    ((SELECT post_id FROM posts WHERE title = 'How to Play Guitar'), (SELECT category_id FROM categories WHERE category_name = 'Lifestyle')),
    ((SELECT post_id FROM posts WHERE title = 'Top 5 AI Trends in 2024'), (SELECT category_id FROM categories WHERE category_name = 'Technology')),
    ((SELECT post_id FROM posts WHERE title = 'Some words about PostgreSQL'), (SELECT category_id FROM categories WHERE category_name = 'Databases'));


INSERT INTO post_tags (post_id, tag_id)
VALUES
    ((SELECT post_id FROM posts WHERE title = 'How to Start Programming'), (SELECT tag_id FROM tags WHERE tag_name = 'Beginner-friendly')),
    ((SELECT post_id FROM posts WHERE title = 'How to Start Programming'), (SELECT tag_id FROM tags WHERE tag_name = 'Tutorial')),
    ((SELECT post_id FROM posts WHERE title = 'How to Play Guitar'), (SELECT tag_id FROM tags WHERE tag_name = 'Tutorial')),
    ((SELECT post_id FROM posts WHERE title = 'How to Play Guitar'), (SELECT tag_id FROM tags WHERE tag_name = 'Beginner-friendly')),
    ((SELECT post_id FROM posts WHERE title = 'How to Play Guitar'), (SELECT tag_id FROM tags WHERE tag_name = 'Music')),
    ((SELECT post_id FROM posts WHERE title = 'Top 5 AI Trends in 2024'), (SELECT tag_id FROM tags WHERE tag_name = 'Innovative')),
    ((SELECT post_id FROM posts WHERE title = 'Some words about PostgreSQL'), (SELECT tag_id FROM tags WHERE tag_name = 'PostgreSQL')),
    ((SELECT post_id FROM posts WHERE title = 'Some words about PostgreSQL'), (SELECT tag_id FROM tags WHERE tag_name = 'Beginner-friendly'));


INSERT INTO comments (post_id, author_id, content, created_date, updated_date, parent_id)
VALUES
    ((SELECT post_id FROM posts WHERE title = 'How to Start Programming'), (SELECT user_id FROM users WHERE user_name = 'user2'), 'Great guide for beginners!', now(), now(), NULL),
    ((SELECT post_id FROM posts WHERE title = 'Top 5 AI Trends in 2024'), (SELECT user_id FROM users WHERE user_name = 'user1'), 'Really insightful post!', now(), now(), NULL),
    ((SELECT post_id FROM posts WHERE title = 'Top 5 AI Trends in 2024'), (SELECT user_id FROM users WHERE user_name = 'user2'), 'Boring.', now(), now(), NULL),
    ((SELECT post_id FROM posts WHERE title = 'Some words about PostgreSQL'), (SELECT user_id FROM users WHERE user_name = 'user1'), 'MySQL is the best RDBMS!', now(), now(), NULL);


INSERT INTO polls (post_id, author_id, description)
VALUES
    ((SELECT post_id FROM posts WHERE title = 'Top 5 AI Trends in 2024'), (SELECT user_id FROM users WHERE user_name = 'user2'), 'Which AI trend is most exciting for you?'),
    (NULL, (SELECT user_id FROM users WHERE user_name = 'user1'), 'Best genre of music?'),
    (NULL, (SELECT user_id FROM users WHERE user_name = 'admin'), 'Are you satisfied with the quality of the platform?');


INSERT INTO poll_options (poll_id, description)
VALUES
    ((SELECT poll_id FROM polls WHERE description = 'Which AI trend is most exciting for you?'), 'Generative AI'),
    ((SELECT poll_id FROM polls WHERE description = 'Which AI trend is most exciting for you?'), 'Edge AI'),
    ((SELECT poll_id FROM polls WHERE description = 'Which AI trend is most exciting for you?'), 'Ethical AI'),
    ((SELECT poll_id FROM polls WHERE description = 'Best genre of music?'), 'Rock music'),
    ((SELECT poll_id FROM polls WHERE description = 'Best genre of music?'), 'Pop music'),
    ((SELECT poll_id FROM polls WHERE description = 'Best genre of music?'), 'Folk music'),
    ((SELECT poll_id FROM polls WHERE description = 'Are you satisfied with the quality of the platform?'), 'Yes'),
    ((SELECT poll_id FROM polls WHERE description = 'Are you satisfied with the quality of the platform?'), 'No');


INSERT INTO votes (poll_option_id, user_id, vote_date)
VALUES
    ((SELECT poll_option_id FROM poll_options WHERE description = 'Generative AI'), (SELECT user_id FROM users WHERE user_name = 'user1'), now()),
    ((SELECT poll_option_id FROM poll_options WHERE description = 'Ethical AI'), (SELECT user_id FROM users WHERE user_name = 'user2'), now()),
    ((SELECT poll_option_id FROM poll_options WHERE description = 'Rock music'), (SELECT user_id FROM users WHERE user_name = 'user1'), now()),
    ((SELECT poll_option_id FROM poll_options WHERE description = 'Pop music'), (SELECT user_id FROM users WHERE user_name = 'user2'), now()),
    ((SELECT poll_option_id FROM poll_options WHERE description = 'Folk music'), (SELECT user_id FROM users WHERE user_name = 'admin'), now()),
    ((SELECT poll_option_id FROM poll_options WHERE description = 'Yes'), (SELECT user_id FROM users WHERE user_name = 'user1'), now()),
    ((SELECT poll_option_id FROM poll_options WHERE description = 'No'), (SELECT user_id FROM users WHERE user_name = 'user2'), now());
