CREATE TABLE IF NOT EXISTS app.genre
(
    id bigserial,
    name text NOT NULL,
    CONSTRAINT genre_pkey PRIMARY KEY (id)
)