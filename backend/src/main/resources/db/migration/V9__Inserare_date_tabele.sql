
-- inserare specializari
INSERT INTO specializari (nume)
VALUES  ('Tehnician de telecomunicații'),
        ('Tehnician Electronist'),
        ('Tehnician în automatizări'),
        ('Tehnician operator tehnică de calcul');

-- inserare user
INSERT INTO users (username, parola, email, rol)
VALUES ('ionpopescu', '$2a$10$B2FgDeTXNc7v5v2PUuKAduEWJepmaItMOUbD/vRFD2U0c2FUk9N2i', 'ionpopescu@gmail.com', 'ROLE_PROFESOR');

-- inserare profesor
INSERT INTO profesori (id_user, nume, prenume, numar_telefon, adresa, CNP)
VALUES (1, 'Ion', 'Popescu', '0795595060', 'Splaiul Horea 2A, Baia Mare, Maramures', '6080210225328');

-- inserare clasa
INSERT INTO clase (nume, id_profesor, id_specializare)
VALUES ('10A', 1, 1);

-- inserare materii
INSERT INTO materii (nume)
VALUES  ('Matematică'),
        ('Română'),
        ('Biologie'),
        ('Fizică');

-- inserare materii care sunt predate in clasa de un anumit profesor
INSERT INTO clase_materii_profesori (id_clasa, id_profesor, id_materie)
VALUES  (1, 1, 1),
        (1, 1, 4);

-- inserare elevi
INSERT INTO elevi (nume, prenume, CNP, adresa, numar_telefon, sex, data_nasterii, id_clasa)
VALUES 	('Ciobanu', 'Codruț', '5080709246143', 'Strada Victoriei 22, Baia Mare, Maramures', '0708238518', 'M', '2008-09-07', 1),
          ('Gabriela', 'Florescu', '6080513240680', 'Strada Stefan cel Mare 54, Baia Mare, Maramures', '0768544996', 'F', '2008-05-04', 1);

-- inserare note
INSERT INTO note (nota, data, id_elev, id_materie)
VALUES  (8, '2024-10-03', 1, 1),
        (10, '2024-10-24', 1, 4),
        (10, '2024-12-10', 2, 1),
        (9, '2024-11-03', 2, 4);

-- inserare absente
INSERT INTO absente (data, id_elev, id_materie)
VALUES  ('2024-09-27', 1, 1),
        ('2024-10-12', 1, 4),
        ('2024-11-01', 2, 1),
        ('2024-12-15', 2, 4);