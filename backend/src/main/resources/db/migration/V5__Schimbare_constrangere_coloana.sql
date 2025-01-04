
-- schimbare constrangere pentru coloana id_user din tabelul profesori pentru a accepta valori nule

ALTER TABLE profesori
MODIFY COLUMN id_user INT NULL;