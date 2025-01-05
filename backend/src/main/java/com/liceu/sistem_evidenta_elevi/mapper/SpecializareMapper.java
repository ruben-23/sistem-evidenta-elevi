package com.liceu.sistem_evidenta_elevi.mapper;

import com.liceu.sistem_evidenta_elevi.dto.SpecializareDTO;
import com.liceu.sistem_evidenta_elevi.entity.Specializare;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru maparea entitatilor Specializare in obiecte DTO de tip SpecializareDTO.
 *
 * Aceasta clasa este responsabila pentru conversia datelor din entitatea Specializare intr-un obiect DTO,
 * precum si pentru conversia inversa, adica din DTO in entitate. De asemenea, ofera functionalitatea
 * de actualizare a entitatilor Specializare cu datele din DTO.
 */
@Component
public class SpecializareMapper {

    /**
     * Converteste un obiect de tip Specializare intr-un obiect DTO de tip SpecializareDTO.
     *
     * @param specializare Entitatea Specializare care trebuie convertita intr-un DTO.
     * @return Un obiect SpecializareDTO populat cu datele din entitatea Specializare.
     */
    public SpecializareDTO toDTO(Specializare specializare) {
        SpecializareDTO specializareDTO = new SpecializareDTO();

        specializareDTO.setIdSpecializare(specializare.getIdSpecializare());
        specializareDTO.setNume(specializare.getNume());

        return specializareDTO;
    }

    /**
     * Converteste o lista de obiecte Specializare intr-o lista de obiecte SpecializareDTO.
     *
     * @param specializari Lista de entitati Specializare care trebuie convertita intr-o lista de DTO-uri.
     * @return O lista de obiecte SpecializareDTO populata cu datele din lista de entitati Specializare.
     */
    public List<SpecializareDTO> toDTOList(List<Specializare> specializari) {
        List<SpecializareDTO> specializareDTOs = new ArrayList<>();
        for (Specializare specializare : specializari) {
            specializareDTOs.add(toDTO(specializare));
        }
        return specializareDTOs;
    }

    /**
     * Converteste un obiect DTO de tip SpecializareDTO intr-o entitate de tip Specializare.
     *
     * @param specializareDTO DTO-ul Specializare care trebuie convertit intr-o entitate Specializare.
     * @return O entitate Specializare populata cu datele din DTO-ul SpecializareDTO.
     */
    public Specializare toEntity(SpecializareDTO specializareDTO) {
        Specializare specializare = new Specializare();
        specializare.setIdSpecializare(specializareDTO.getIdSpecializare());
        specializare.setNume(specializareDTO.getNume());

        return specializare;
    }

    /**
     * Actualizeaza o entitate Specializare cu datele dintr-un DTO de tip SpecializareDTO.
     *
     * @param specializareDTO DTO-ul care contine noile date pentru entitatea Specializare.
     * @param specializare Entitatea Specializare care va fi actualizata.
     */
    public void updateEntityFromDTO(SpecializareDTO specializareDTO, Specializare specializare) {
        specializare.setIdSpecializare(specializareDTO.getIdSpecializare());
        specializare.setNume(specializareDTO.getNume());
    }

}