CREATE TABLE IF NOT EXISTS users (
    user_id UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    username TEXT NOT NULL
);

CREATE INDEX IF NOT EXISTS index_users_email ON users (email);
