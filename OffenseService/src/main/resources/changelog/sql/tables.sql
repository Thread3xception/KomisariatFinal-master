-- liquibase formatted sql
-- changeset esley:2
CREATE TABLE OFFENSE
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    price_From DOUBLE NOT NULL,
    price_To DOUBLE NOT NULL,
    description VARCHAR(255) NOT NULL,
    score_From INT NOT NULL,
    score_To INT NOT NULL
);


