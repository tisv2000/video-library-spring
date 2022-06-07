--liquibase formatted sql

--changeset lana:1
CREATE TABLE IF NOT EXISTS movie
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(256) NOT NULL,
    year        INT NOT NULL,
    country     VARCHAR(256) NOT NULL,
    genre       VARCHAR(256) NOT NULL,
    image       VARCHAR(128),
    description VARCHAR(512) NOT NULL
    );

--changeset lana:2
CREATE TABLE IF NOT EXISTS person
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(256) NOT NULL,
    birthday   DATE NOT NULL
    );

--changeset lana:3
CREATE TABLE IF NOT EXISTS movie_person
(
    id        SERIAL PRIMARY KEY,
    movie_id  INT NOT NULL REFERENCES movie (id) ON DELETE CASCADE,
    person_id INT NOT NULL REFERENCES person (id) ON DELETE CASCADE,
    role      VARCHAR(256) NOT NULL
    );

--changeset lana:4
CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(256) NOT NULL,
    birth_date DATE NOT NULL,
    password VARCHAR(256) NOT NULL,
    email    VARCHAR(256) NOT NULL UNIQUE,
    image   VARCHAR(124),
    role     VARCHAR(32)  NOT NULL,
    gender    VARCHAR(32)  NOT NULL
    );

--changeset lana:5
CREATE TABLE IF NOT EXISTS review
(
    id       SERIAL PRIMARY KEY,
    user_id  INT REFERENCES users (id) NOT NULL,
    movie_id INT REFERENCES movie (id) NOT NULL,
    text     VARCHAR(256) NOT NULL,
    rate     INT NOT NULL
    );
