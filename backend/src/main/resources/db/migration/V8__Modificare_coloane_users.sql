
-- modificare dimensiune pentru coloana parola
-- adaugare constrangere not null pentru coloana rol

ALTER TABLE users
MODIFY parola VARCHAR(255),
MODIFY rol VARCHAR(50) NOT NULL;
