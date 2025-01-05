package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.NotaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Nota;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interfata serviciului pentru gestionarea operatiunilor legate de note.
 * Contine metode pentru operatiuni CRUD (adaugare, actualizare, stergere) si interogari ale notelor.
 */
@Service
public interface NotaService {

    /**
     * Obtine lista tuturor notelor.
     *
     * @return Lista tuturor notelor.
     */
    List<Nota> getAllNote();

    /**
     * Obtine o nota dupa ID-ul acesteia.
     *
     * @param id ID-ul notei.
     * @return Nota corespunzatoare ID-ului dat.
     */
    Nota getNotaById(Integer id);

    /**
     * Actualizeaza o nota folosind un DTO (Data Transfer Object).
     *
     * @param notaDTO DTO-ul care contine datele actualizate ale notei.
     * @return Nota actualizata.
     */
    Nota actualizareNota(NotaDTO notaDTO);

    /**
     * Adauga o noua nota folosind un DTO (Data Transfer Object).
     *
     * @param notaDTO DTO-ul care contine datele notei ce urmeaza a fi adaugata.
     * @return Nota adaugata.
     */
    Nota adaugaNota(NotaDTO notaDTO);

    /**
     * Sterge o nota dupa ID-ul acesteia.
     *
     * @param idNota ID-ul notei care trebuie stearsa.
     */
    void stergeNota(Integer idNota);
}