CREATE TABLE IF NOT EXISTS TICKET_OFFENSE
(
    id SERIAL PRIMARY KEY,
    ticket_id INT NOT NULL,
    offense_id INT NOT NULL,
    price DOUBLE NOT NULL,
    score INT NOT NULL,
    country VARCHAR(255) NOT NULL
);

