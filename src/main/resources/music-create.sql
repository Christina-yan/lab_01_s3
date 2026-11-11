CREATE SCHEMA IF NOT EXISTS study;
DROP TABLE IF EXISTS study.music;
CREATE TABLE study.music (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
INSERT INTO study.music (name) VALUES ('Yesterday');
INSERT INTO study.music (name) VALUES ('Bohemian Rhapsody');
INSERT INTO study.music (name) VALUES ('Imagine');
INSERT INTO study.music (name) VALUES ('Smells Like Teen Spirit');
INSERT INTO study.music (name) VALUES ('Billie Jean');