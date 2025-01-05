package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.AbsentaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Absenta;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interfata care defineste metodele serviciului pentru gestionarea entitatilor de tip {@link Absenta}.
 */
@Service
public interface AbsentaService {

    /**
     * Obtine toate absentele inregistrate.
     *
     * @return o lista de entitati {@link Absenta}.
     */
    List<Absenta> getAllAbsente();

    /**
     * Obtine o absenta pe baza ID-ului.
     *
     * @param id ID-ul absentei.
     * @return entitatea {@link Absenta}.
     */
    Absenta getAbsentaById(Integer id);

    /**
     * Actualizeaza o absenta existenta pe baza unui {@link AbsentaDTO}.
     *
     * @param absentaDTO obiectul {@link AbsentaDTO} cu noile date.
     * @return entitatea {@link Absenta} actualizata.
     */
    Absenta actualizareAbsenta(AbsentaDTO absentaDTO);

    /**
     * Adauga o noua absenta pe baza unui {@link AbsentaDTO}.
     *
     * @param absentaDTO obiectul {@link AbsentaDTO} care contine detaliile noii absente.
     * @return entitatea {@link Absenta} adaugata.
     */
    Absenta adaugaAbsenta(AbsentaDTO absentaDTO);

    /**
     * Sterge o absenta pe baza ID-ului.
     *
     * @param idAbsenta ID-ul absentei care trebuie stearsa.
     */
    void stergeAbsenta(Integer idAbsenta);


}
