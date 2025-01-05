package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.entity.User;

import java.util.List;

/**
 * Interfata serviciului pentru gestionarea operatiunilor legate de profesori.
 * Contine metode pentru operatiuni CRUD (adaugare, actualizare, stergere) si interogari ale profesorilor.
 */
public interface ProfesorService {

    /**
     * Obtine lista tuturor profesorilor.
     *
     * @return Lista tuturor profesorilor.
     */
    List<Profesor> getAllProfesori();

    /**
     * Obtine un profesor dupa ID-ul acestuia.
     *
     * @param id ID-ul profesorului.
     * @return Profesorul corespunzator ID-ului dat.
     */
    Profesor getProfesorById(Integer id);

    /**
     * Actualizeaza un profesor folosind un DTO (Data Transfer Object).
     *
     * @param profesorDTO DTO-ul care contine datele actualizate ale profesorului.
     * @return Profesorul actualizat.
     */
    Profesor actualizareProfesor(ProfesorDTO profesorDTO);

    /**
     * Adauga un nou profesor folosind un DTO (Data Transfer Object) si un obiect de tip User.
     *
     * @param profesorDTO DTO-ul care contine datele profesorului ce urmeaza a fi adaugat.
     * @param user Obiectul de tip User care reprezinta utilizatorul asociat profesorului.
     * @return Profesorul adaugat.
     */
    Profesor adaugaProfesor(ProfesorDTO profesorDTO, User user);

    /**
     * Sterge un profesor dupa ID-ul acestuia.
     *
     * @param idProfesor ID-ul profesorului care trebuie sters.
     */
    void stergeProfesor(Integer idProfesor);
}