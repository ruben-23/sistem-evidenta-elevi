
-- adaugare coloana modul pentru note si absente
ALTER TABLE note
    ADD modul VARCHAR(45) NOT NULL;

ALTER TABLE absente
    ADD modul VARCHAR(45) NOT NULL;

-- adaugare valori in coloana modul pentru notele existente
UPDATE absente
SET modul = "Modul 1"
WHERE id_absenta = 1;

UPDATE absente
SET modul = "Modul 2"
WHERE id_absenta = 2;

UPDATE absente
SET modul = "Modul 3"
WHERE id_absenta = 3;

UPDATE absente
SET modul = "Modul 4"
WHERE id_absenta = 4;

-- adaugare valori in coloana modul pentru notele existente
UPDATE note
SET modul = "Modul 2"
WHERE id_nota = 1;

UPDATE note
SET modul = "Modul 2"
WHERE id_nota = 2;

UPDATE note
SET modul = "Modul 4"
WHERE id_nota = 3;

UPDATE note
SET modul = "Modul 3"
WHERE id_nota = 4;