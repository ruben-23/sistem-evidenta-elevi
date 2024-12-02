
-- modifica coloana id_clasa pentru a accepta valori nule
-- peentru a adauga elevi care inca nu apartin unei clase

ALTER TABLE elevi
    ALTER COLUMN id_clasa DROP NOT NULL;