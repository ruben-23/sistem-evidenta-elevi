package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.*;
import com.liceu.sistem_evidenta_elevi.entity.*;

import java.util.List;

/**
 * Interfata serviciului pentru gestionarea operatiunilor legate de clase.
 * Contine metodele pentru operatiuni CRUD (adaugare, actualizare, stergere) si interogari ale claselor si elevilor acestora.
 */
public interface ClasaService {

    /**
     * Obtine lista tuturor claselor.
     *
     * @return Lista tuturor claselor.
     */
    List<Clasa> getAllClase();

    /**
     * Obtine o clasa dupa ID-ul acesteia.
     *
     * @param id ID-ul clasei.
     * @return Clasa corespunzatoare ID-ului dat.
     */
    Clasa getClasaById(Integer id);

    /**
     * Adauga o noua clasa folosind un DTO (Data Transfer Object).
     *
     * @param clasaDTO DTO-ul care contine datele clasei ce urmeaza a fi adaugate.
     * @return Clasa adaugata.
     */
    Clasa adaugaClasa(ClasaDTO clasaDTO);

    /**
     * Actualizeaza o clasa existenta folosind un DTO (Data Transfer Object).
     *
     * @param clasa DTO-ul care contine datele actualizate ale clasei.
     * @return Clasa actualizata.
     */
    Clasa actualizareClasa(ClasaDTO clasa);

    /**
     * Sterge o clasa dupa ID-ul acesteia.
     *
     * @param idClasa ID-ul clasei care trebuie stearsa.
     */
    void stergeClasa(Integer idClasa);

    /**
     * Obtine lista elevilor care apartin unei anumite clase.
     *
     * @param idClasa ID-ul clasei.
     * @return Lista elevilor care fac parte din clasa respectiva.
     */
    List<Elev> getEleviByClasa(Integer idClasa);
}
