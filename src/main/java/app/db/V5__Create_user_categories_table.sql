CREATE TABLE IF NOT EXISTS user_categories (
    user_id UUID NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    category_id BIGINT NOT NULL REFERENCES categories(category_id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, category_id)
);
