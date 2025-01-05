package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.MaterieDTO;
import com.liceu.sistem_evidenta_elevi.entity.Materie;

import java.util.List;

/**
 * Interfata serviciului pentru gestionarea operatiunilor legate de materii.
 * Contine metode pentru operatiuni CRUD (adaugare, actualizare, stergere) si interogari ale materiei.
 */
public interface MaterieService {

    /**
     * Obtine lista tuturor materiilor.
     *
     * @return Lista tuturor materiilor.
     */
    List<Materie> getAllMaterii();

    /**
     * Obtine o materie dupa ID-ul acesteia.
     *
     * @param id ID-ul materiei.
     * @return Materia corespunzatoare ID-ului dat.
     */
    Materie getMaterieById(Integer id);

    /**
     * Actualizeaza o materie folosind un DTO (Data Transfer Object).
     *
     * @param materieDTO DTO-ul care contine datele actualizate ale materiei.
     * @return Materia actualizata.
     */
    Materie actualizareMaterie(MaterieDTO materieDTO);

    /**
     * Adauga o materie folosind un DTO (Data Transfer Object).
     *
     * @param materieDTO DTO-ul care contine datele materiei ce urmeaza a fi adaugata.
     * @return Materia adaugata.
     */
    Materie adaugaMaterie(MaterieDTO materieDTO);

    /**
     * Sterge o materie dupa ID-ul acesteia.
     *
     * @param idMaterie ID-ul materiei care trebuie stearsa.
     */
    void stergeMaterie(Integer idMaterie);
}