-- liquibase formatted sql
-- changeset esley:4
CREATE TABLE TICKET_OFFENSE
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id INT NOT NULL,
    offense_id INT NOT NULL,
    price DOUBLE NOT NULL,
    score INT NOT NULL,
    country VARCHAR(255) NOT NULL
);


