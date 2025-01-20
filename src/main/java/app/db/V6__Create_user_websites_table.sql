CREATE TABLE IF NOT EXISTS user_websites (
    user_id UUID NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    website_id BIGINT NOT NULL REFERENCES websites(website_id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, website_id)
);
