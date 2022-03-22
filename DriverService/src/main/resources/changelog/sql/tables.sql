-- liquibase formatted sql
-- changeset esley:1
CREATE TABLE DRIVER
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    years_Old INT NOT NULL,
    score INT NOT NULL
);



