
-- redenumire tabel pentru a avea forma de plural

START TRANSACTION;

ALTER TABLE secretara
RENAME TO secretare;

-- schimbare constrangere pentru coloana id_user din
-- tabelul secretare pentru a accepta valori nule

ALTER TABLE secretare
MODIFY id_user INT NULL;

COMMIT;