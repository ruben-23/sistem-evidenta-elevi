package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.entity.*;

import java.util.List;

/**
 * Interfata serviciului pentru gestionarea relatiilor dintre clase, materii si profesori.
 * Contine metodele necesare pentru operatiuni CRUD si interogari ale relatiei dintre clase, profesori si materii.
 */
public interface ClasaMaterieProfesorService {

    /**
     * Obtine o relatie Clasa-Materie-Profesor pe baza unui ID compus.
     *
     * @param id ID-ul compus (idClasa, idProfesor, idMaterie) al relatiei.
     * @return Relatia Clasa-Materie-Profesor.
     */
    ClasaMaterieProfesor getClasaMaterieProfesorById(ClasaMaterieProfesorId id);

    /**
     * Adauga o materie si un profesor la o clasa.
     *
     * @param idClasa ID-ul clasei.
     * @param idProfesor ID-ul profesorului.
     * @param idMaterie ID-ul materiei.
     * @return Obiectul ClasaMaterieProfesor adaugat.
     */
    ClasaMaterieProfesor adaugaMaterieSiProfesorLaClasa(Integer idClasa, Integer idProfesor, Integer idMaterie);

    /**
     * Sterge o materie si un profesor dintr-o clasa.
     *
     * @param idClasa ID-ul clasei.
     * @param idProfesor ID-ul profesorului.
     * @param idMaterie ID-ul materiei.
     */
    void stergereMaterieSiProfesorDinClasa(Integer idClasa, Integer idProfesor, Integer idMaterie);

    /**
     * Obtine lista materiilor dintr-o clasa.
     *
     * @param idClasa ID-ul clasei.
     * @return Lista materiilor din clasa respectiva.
     */
    List<Materie> getMateriiDinClasa(Integer idClasa);

    /**
     * Obtine lista materiilor predante de un profesor intr-o clasa.
     *
     * @param idProfesor ID-ul profesorului.
     * @param idClasa ID-ul clasei.
     * @return Lista materiilor predante de profesor in clasa respectiva.
     */
    List<Materie> getMateriiPredateDeProfesorInClasa(Integer idProfesor, Integer idClasa);

    /**
     * Obtine lista claselor in care predau profesori.
     *
     * @param idProfesor ID-ul profesorului.
     * @return Lista claselor in care predau profesorii.
     */
    List<Clasa> getClaseProfesor(Integer idProfesor);

    /**
     * Obtine lista profesorilor dintr-o clasa.
     *
     * @param idClasa ID-ul clasei.
     * @return Lista profesorilor care predau in clasa respectiva.
     */
    List<Profesor> getProfesoriDinClasa(Integer idClasa);
}