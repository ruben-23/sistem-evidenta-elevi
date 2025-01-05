package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.SecretaraDTO;
import com.liceu.sistem_evidenta_elevi.entity.Secretara;
import com.liceu.sistem_evidenta_elevi.entity.User;

import java.util.List;

/**
 * Interfata serviciului pentru gestionarea operatiunilor legate de secretare.
 * Contine metode pentru operatiuni CRUD (adaugare, actualizare, stergere) si interogari ale secretarelor.
 */
public interface SecretaraService {

    /**
     * Obtine lista tuturor secretarelor.
     *
     * @return Lista tuturor secretarelor.
     */
    List<Secretara> getAllSecretare();

    /**
     * Obtine o secretara dupa ID-ul acesteia.
     *
     * @param id ID-ul secretarei.
     * @return Secretara corespunzatoare ID-ului dat.
     */
    Secretara getSecretaraById(Integer id);

    /**
     * Actualizeaza o secretara folosind un DTO (Data Transfer Object).
     *
     * @param secretaraDTO DTO-ul care contine datele actualizate ale secretarei.
     * @return Secretara actualizata.
     */
    Secretara actualizareSecretara(SecretaraDTO secretaraDTO);

    /**
     * Adauga o noua secretara folosind un DTO (Data Transfer Object) si un obiect de tip User.
     *
     * @param secretaraDTO DTO-ul care contine datele secretarei ce urmeaza a fi adaugata.
     * @param user Obiectul de tip User care reprezinta utilizatorul asociat secretarei.
     * @return Secretara adaugata.
     */
    Secretara adaugaSecretara(SecretaraDTO secretaraDTO, User user);

    /**
     * Sterge o secretara dupa ID-ul acesteia.
     *
     * @param idSecretara ID-ul secretarei care trebuie stearsa.
     */
    void stergeSecretara(Integer idSecretara);
}