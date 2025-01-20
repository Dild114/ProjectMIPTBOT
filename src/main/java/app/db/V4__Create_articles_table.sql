CREATE TABLE IF NOT EXISTS articles (
    article_id BIGSERIAL NOT NULL PRIMARY KEY,
    name_articles TEXT NOT NULL,
    url TEXT NOT NULL,
    website_id BIGINT NOT NULL REFERENCES websites(website_id) ON DELETE CASCADE,
    category_id BIGINT NOT NULL REFERENCES categories(category_id) ON DELETE CASCADE
);
