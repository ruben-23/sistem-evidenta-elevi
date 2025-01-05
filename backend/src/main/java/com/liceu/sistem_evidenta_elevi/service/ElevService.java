package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.ElevDTO;
import com.liceu.sistem_evidenta_elevi.entity.*;

import java.util.List;

/**
 * Interfata serviciului pentru gestionarea operatiunilor legate de elevi.
 * Contine metode pentru operatiuni CRUD (adaugare, actualizare, stergere) si interogari ale informatiilor elevilor.
 */
public interface ElevService {

    /**
     * Obtine lista tuturor elevilor.
     *
     * @return Lista tuturor elevilor.
     */
    List<Elev> getAllElevi();

    /**
     * Obtine un elev dupa ID-ul acestuia.
     *
     * @param id ID-ul elevului.
     * @return Elevul corespunzator ID-ului dat.
     */
    Elev getElevById(Integer id);

    /**
     * Actualizeaza un elev folosind un DTO (Data Transfer Object).
     *
     * @param elevDTO DTO-ul care contine datele actualizate ale elevului.
     * @return Elevul actualizat.
     */
    Elev actualizareElev(ElevDTO elevDTO);

    /**
     * Adauga un nou elev folosind un DTO (Data Transfer Object).
     *
     * @param elevDTO DTO-ul care contine datele elevului ce urmeaza a fi adaugat.
     * @return Elevul adaugat.
     */
    Elev adaugaElev(ElevDTO elevDTO);

    /**
     * Sterge un elev dupa ID-ul acestuia.
     *
     * @param idElev ID-ul elevului care trebuie sters.
     */
    void stergeElev(Integer idElev);

    /**
     * Obtine lista notelor unui elev.
     *
     * @param idElev ID-ul elevului.
     * @return Lista notelor elevului.
     */
    List<Nota> getNoteElev(Integer idElev);

    /**
     * Obtine lista absentelor unui elev.
     *
     * @param idElev ID-ul elevului.
     * @return Lista absentelor elevului.
     */
    List<Absenta> getAbsenteElev(Integer idElev);

    /**
     * Obtine lista notelor unui elev pentru o anumita materie.
     *
     * @param idElev ID-ul elevului.
     * @param idMaterie ID-ul materiei.
     * @return Lista notelor elevului pentru materia respectiva.
     */
    List<Nota> getNoteElevMaterie(Integer idElev, Integer idMaterie);

    /**
     * Obtine lista absentelor unui elev pentru o anumita materie.
     *
     * @param idElev ID-ul elevului.
     * @param idMaterie ID-ul materiei.
     * @return Lista absentelor elevului pentru materia respectiva.
     */
    List<Absenta> getAbsenteElevMaterie(Integer idElev, Integer idMaterie);

    /**
     * Obtine lista burselor unui elev.
     *
     * @param idElev ID-ul elevului.
     * @return Lista burselor elevului.
     */
    List<Bursa> getBurseElev(Integer idElev);

    /**
     * Adauga o bursa unui elev.
     *
     * @param idElev ID-ul elevului.
     * @param idBursa ID-ul bursei.
     * @return Elevul actualizat cu bursa adaugata.
     */
    Elev adaugaBursaLaElev(Integer idElev, Integer idBursa);

    /**
     * Sterge o bursa de la un elev.
     *
     * @param idElev ID-ul elevului.
     * @param idBursa ID-ul bursei ce trebuie stearsa.
     */
    void stergeBursaLaElev(Integer idElev, Integer idBursa);
}