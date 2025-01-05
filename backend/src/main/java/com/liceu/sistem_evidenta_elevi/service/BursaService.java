package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.BursaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Bursa;

import java.util.List;

/**
 * Interfata serviciului Bursa.
 * Contine metode pentru gestionarea operatiunilor legate de burse.
 */
public interface BursaService {

    /**
     * Obtine toate bursele din sistem.
     *
     * @return Lista tuturor burselor.
     */
    List<Bursa> getAllBurse();

    /**
     * Obtine o bursa dupa ID.
     *
     * @param id ID-ul burselor.
     * @return Bursa corespunzatoare ID-ului.
     * @throws RuntimeException Daca bursa nu a fost gasita.
     */
    Bursa getBursaById(Integer id);

    /**
     * Actualizeaza informatiile unei burse.
     *
     * @param bursaDTO DTO-ul care contine datele actualizate ale burselor.
     * @return Bursa actualizata.
     */
    Bursa actualizareBursa(BursaDTO bursaDTO);

    /**
     * Adauga o noua bursa in sistem.
     *
     * @param bursaDTO DTO-ul care contine datele burselor de adaugat.
     * @return Bursa adaugata.
     */
    Bursa adaugaBursa(BursaDTO bursaDTO);

    /**
     * Sterge o bursa din sistem pe baza ID-ului.
     *
     * @param idBursa ID-ul burselor de sters.
     */
    void stergeBursa(Integer idBursa);
}