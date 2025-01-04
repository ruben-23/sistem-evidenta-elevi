
-- adaugare profesor
INSERT INTO users(username, parola, email, rol)
VALUES("sebastiansava", '$2a$10$TsV3Rrqh0hD8fJVLtM21Q.4ZSbQt7LExJTtjUnlfHA3dUU2gTVwte', "sebastiansava123@gmail.com", "ROLE_PROFESOR");
INSERT INTO profesori(id_user, nume, prenume, numar_telefon, adresa, CNP)
VALUES(2, "Sava", "Sebastian Sorin","0729900530",  "Strada Florilor, Nr. 4", "1680118243924");

-- adaugare secretara
INSERT INTO users(username, parola, email, rol)
VALUES("vasilescuioana",'$2a$10$AHMoqJmJ1sdITr54dMz76OVG7Rkc.4PB7VldvptAq9gqQEe16yj1K', "vasilescuioana@gmail.com", "ROLE_SECRETARA");
INSERT INTO secretare(id_user, nume, prenume, adresa, numar_telefon, CNP)
VALUES(3, "Vasilescu", "Ioana",  "Strada Cuza Voda, Nr. 35", "0775920333", "2720419243484");

-- adaugare clasa
INSERT INTO clase(nume, id_profesor, id_specializare)
VALUES("9B", 2, 2);

-- adaugare materii si profesori la clase
INSERT INTO clase_materii_profesori(id_clasa, id_profesor, id_materie)
VALUES  (2, 1, 1), -- prof 1 preda materia 1 in clasa 2
        (2, 1, 4), -- prof 1 preda materia 4 in clasa 2
        (1, 2, 2),  -- prof 4 preda materia 2 in clasa 1
        (1, 2, 3), -- prof 4 preda materia 3 in clasa 1
        (2, 2, 2), -- prof 4 preda materia 2 in clasa 2
        (2, 2, 3); -- prof 4 preda materia 3 in clasa 22, 3); -- prof 4 preda materia 3 in clasa 2