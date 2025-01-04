
-- inserare elevi
INSERT INTO elevi (nume, prenume, CNP, adresa, numar_telefon, sex, data_nasterii, id_clasa)
VALUES 	('Popescuu', 'George', '5080609242468', 'Str. Bucovinei nr. 16, Baia Mare, Maramures', '0753924332', 'masculin', '2008-06-09', 2),
          ('Antonescu', 'Iulia', '6080513240680', 'B-dul. Decebal nr. 9B, Baia Mare, Maramures', '0715978611', 'feminin', '2008-04-02', 2);

INSERT INTO note (nota, data, id_elev, id_materie, modul)
VALUES  (6, '2024-10-13', 3, 2, 'Modul 1'),
        (9, '2024-10-04', 3, 4,'Modul 2'),
        (5, '2024-12-15', 4, 1, 'Modul 3'),
        (10, '2024-11-24', 4, 3, 'Modul 4');

-- inserare absente
INSERT INTO absente (data, id_elev, id_materie, modul)
VALUES  ('2024-09-16', 3, 1, 'Modul 3'),
        ('2024-10-02', 3, 4, 'Modul 1'),
        ('2024-11-23', 4, 1, 'Modul 4'),
        ('2024-12-10', 4, 4, 'Modul 2');