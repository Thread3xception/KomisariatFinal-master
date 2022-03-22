-- liquibase formatted sql
-- changeset esley:3
CREATE TABLE TICKET
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    driver_id INT NOT NULL,
    data VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL
);

