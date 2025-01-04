
-- tabelul absente va avea o referinta catre tabelul materii
-- referinta catre tabelul clase_materii_profesori este anulata

-- anulare fk
ALTER TABLE absente
DROP CONSTRAINT fk_absente_clase_materii_profesori1;

-- stergere coloane ce compuneau fk
ALTER TABLE absente
DROP COLUMN id_materie,
    DROP COLUMN id_profesor,
    DROP COLUMN id_clasa;

-- adaugare coloana noua
ALTER TABLE absente
    ADD id_materie INT NOT NULL;

-- adaugare fk pentru coloana adaugata (referinta catre materii)
ALTER TABLE absente
    ADD CONSTRAINT fk_absente_materii1
        FOREIGN KEY (id_materie) REFERENCES materii(id_materie);

