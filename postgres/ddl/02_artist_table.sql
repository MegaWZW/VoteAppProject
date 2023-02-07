CREATE TABLE IF NOT EXISTS app.artist
(
    id bigserial,
    name text NOT NULL,
    CONSTRAINT artist_pkey PRIMARY KEY (id)
);