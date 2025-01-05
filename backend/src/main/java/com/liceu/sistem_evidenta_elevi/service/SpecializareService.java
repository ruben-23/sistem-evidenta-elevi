package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.SpecializareDTO;
import com.liceu.sistem_evidenta_elevi.entity.Specializare;

import java.util.List;

/**
 * Interfata serviciului pentru gestionarea operatiunilor legate de specializari.
 * Contine metode pentru operatiuni CRUD (adaugare, actualizare, stergere) si interogari ale specializarilor.
 */
public interface SpecializareService {

    /**
     * Obtine lista tuturor specializarilor.
     *
     * @return Lista tuturor specializarilor.
     */
    List<Specializare> getAllSpecializari();

    /**
     * Obtine o specializare dupa ID-ul acesteia.
     *
     * @param id ID-ul specializarii.
     * @return Specializarea corespunzatoare ID-ului dat.
     */
    Specializare getSpecializareById(Integer id);

    /**
     * Actualizeaza o specializare folosind un DTO (Data Transfer Object).
     *
     * @param specializareDTO DTO-ul care contine datele actualizate ale specializarii.
     * @return Specializarea actualizata.
     */
    Specializare actualizareSpecializare(SpecializareDTO specializareDTO);

    /**
     * Adauga o noua specializare folosind un DTO (Data Transfer Object).
     *
     * @param specializareDTO DTO-ul care contine datele specializarii ce urmeaza a fi adaugata.
     * @return Specializarea adaugata.
     */
    Specializare adaugaSpecializare(SpecializareDTO specializareDTO);

    /**
     * Sterge o specializare dupa ID-ul acesteia.
     *
     * @param idSpecializare ID-ul specializarii care trebuie stearsa.
     */
    void stergeSpecializare(Integer idSpecializare);
}