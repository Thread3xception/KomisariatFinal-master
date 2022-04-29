CREATE TABLE IF NOT EXISTS OFFENSE
(
    id SERIAL PRIMARY KEY,
    price_From double precision NOT NULL,
    price_To double precision NOT NULL,
    description VARCHAR(255) NOT NULL,
    score_From INT NOT NULL,
    score_To INT NOT NULL
);
