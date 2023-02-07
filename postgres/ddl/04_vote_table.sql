CREATE TABLE IF NOT EXISTS app.vote
(
    id bigserial,
    dtcreate timestamp without time zone NOT NULL,
    about text NOT NULL,
    CONSTRAINT vote_pkey PRIMARY KEY (id)
)