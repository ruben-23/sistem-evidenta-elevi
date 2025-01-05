package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.UserRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.User;

import java.util.List;

/**
 * Interfata serviciului pentru gestionarea operatiunilor legate de utilizatori.
 * Contine metode pentru operatiuni CRUD (adaugare, actualizare, stergere) si interogari ale utilizatorilor.
 */
public interface UserService {

    /**
     * Obtine lista tuturor utilizatorilor.
     *
     * @return Lista tuturor utilizatorilor.
     */
    List<User> getAllUseri();

    /**
     * Obtine un utilizator dupa ID-ul acestuia.
     *
     * @param id ID-ul utilizatorului.
     * @return Utilizatorul corespunzator ID-ului dat.
     */
    User getUserById(Integer id);

    /**
     * Obtine un utilizator dupa numele de utilizator (username).
     *
     * @param username Numele de utilizator.
     * @return Utilizatorul corespunzator numelui de utilizator.
     */
    User getUserByUsername(String username);

    /**
     * Adauga un nou utilizator folosind un DTO (Data Transfer Object).
     *
     * @param userRequest DTO-ul care contine datele utilizatorului ce urmeaza a fi adaugat.
     * @return Utilizatorul adaugat.
     */
    User adaugaUser(UserRequestDTO userRequest);

    /**
     * Actualizeaza un utilizator folosind un DTO (Data Transfer Object).
     *
     * @param user DTO-ul care contine datele actualizate ale utilizatorului.
     * @return Utilizatorul actualizat.
     */
    User actualizeazaUser(UserRequestDTO user);

    /**
     * Sterge un utilizator dupa ID-ul acestuia.
     *
     * @param idUser ID-ul utilizatorului care trebuie sters.
     */
    void stergeUser(Integer idUser);
}