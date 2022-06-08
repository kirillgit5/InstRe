CREATE TABLE roles
(
    id   INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(20),
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    login      VARCHAR(255),
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    nickname   VARCHAR(255),
    password   VARCHAR(255),
    gender     INTEGER,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_login UNIQUE (login);

ALTER TABLE users
    ADD CONSTRAINT uc_users_nickname UNIQUE (nickname);

CREATE TABLE postinfo
(
    id      BIGINT      NOT NULL,
    content VARCHAR(255),
    user_id BIGINT      NOT NULL,
    path    VARCHAR(64) NOT NULL,
    CONSTRAINT pk_postinfo PRIMARY KEY (id)
);

ALTER TABLE postinfo
    ADD CONSTRAINT FK_POSTINFO_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);