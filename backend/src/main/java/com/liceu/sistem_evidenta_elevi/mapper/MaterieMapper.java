package com.liceu.sistem_evidenta_elevi.mapper;

import com.liceu.sistem_evidenta_elevi.dto.MaterieDTO;
import com.liceu.sistem_evidenta_elevi.entity.Materie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru maparea entitatilor de tip Materie in obiecte DTO de tip MaterieDTO.
 * Aceasta clasa ofera metode pentru:
 * 1. Conversia unei entitati Materie intr-un obiect DTO.
 * 2. Conversia unei liste de entitati Materie intr-o lista de obiecte DTO.
 * 3. Conversia unui obiect DTO MaterieDTO intr-o entitate Materie.
 * 4. Actualizarea unei entitati Materie cu datele provenite dintr-un obiect DTO.
 */
@Component
public class MaterieMapper {

    /**
     * Converteste o entitate de tip Materie intr-un obiect DTO de tip MaterieDTO.
     *
     * @param materie Entitatea Materie care va fi convertita intr-un obiect DTO.
     * @return Un obiect MaterieDTO populat cu datele din entitatea Materie.
     */
    public MaterieDTO toDTO(Materie materie) {
        MaterieDTO materieDTO = new MaterieDTO();

        // Se seteaza datele din entitatea Materie in obiectul MaterieDTO
        materieDTO.setIdMaterie(materie.getIdMaterie());
        materieDTO.setNume(materie.getNume());

        return materieDTO;
    }

    /**
     * Converteste o lista de entitati Materie intr-o lista de obiecte DTO MaterieDTO.
     *
     * @param materii Lista de entitati Materie care trebuie convertita intr-o lista de DTO-uri.
     * @return O lista de obiecte MaterieDTO populata cu datele din lista de entitati Materie.
     */
    public List<MaterieDTO> toDTOList(List<Materie> materii) {
        List<MaterieDTO> materieDTOs = new ArrayList<>();
        for (Materie materie : materii) {
            materieDTOs.add(toDTO(materie));
        }
        return materieDTOs;
    }

    /**
     * Converteste un obiect DTO de tip MaterieDTO intr-o entitate de tip Materie.
     *
     * @param materieDTO DTO-ul care va fi convertit intr-o entitate Materie.
     * @return O entitate Materie populata cu datele din DTO-ul MaterieDTO.
     */
    public Materie toEntity(MaterieDTO materieDTO) {
        Materie materie = new Materie();
        materie.setIdMaterie(materieDTO.getIdMaterie());
        materie.setNume(materieDTO.getNume());

        return materie;
    }

    /**
     * Actualizeaza o entitate Materie cu datele dintr-un obiect DTO MaterieDTO.
     *
     * @param materieDTO DTO-ul care contine noile date pentru entitatea Materie.
     * @param materie Entitatea Materie care va fi actualizată.
     */
    public void updateEntityFromDTO(MaterieDTO materieDTO, Materie materie) {
        materie.setIdMaterie(materieDTO.getIdMaterie());
        materie.setNume(materieDTO.getNume());
    }
}