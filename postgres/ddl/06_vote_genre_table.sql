CREATE TABLE IF NOT EXISTS app.vote_genre
(
    vote_id bigint NOT NULL,
    genre_id bigint NOT NULL,
    CONSTRAINT vote_genre_vote_id_genre_id_key UNIQUE (vote_id, genre_id),
    CONSTRAINT vote_genre_genre_id_fkey FOREIGN KEY (genre_id)
        REFERENCES app.genre (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT vote_genre_vote_id_fkey FOREIGN KEY (vote_id)
        REFERENCES app.vote (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)