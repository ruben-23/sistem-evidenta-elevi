
-- tabelul note va avea o referinta catre tabelul materii
-- referinta catre tabelul clase_materii_profesori este anulata

-- anulare fk
ALTER TABLE note
DROP CONSTRAINT fk_note_clase_materii_profesori1;

-- stergere coloane ce compuneau fk
ALTER TABLE note
DROP COLUMN id_materie,
    DROP COLUMN id_profesor,
    DROP COLUMN id_clasa;

-- adaugare coloana noua
ALTER TABLE note
    ADD id_materie INT NOT NULL;

-- adaugare fk pentru coloana adaugata (referinta catre materii)
ALTER TABLE note
    ADD CONSTRAINT fk_note_materii1
        FOREIGN KEY (id_materie) REFERENCES materii(id_materie);

