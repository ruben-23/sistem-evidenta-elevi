package com.liceu.sistem_evidenta_elevi.mapper;


import com.liceu.sistem_evidenta_elevi.dto.SpecializareDTO;
import com.liceu.sistem_evidenta_elevi.entity.Specializare;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru mapare entitatilor Specializare in DTO
 */

@Component
public class SpecializareMapper {

    public SpecializareDTO toDTO(Specializare specializare) {
        SpecializareDTO specializareDTO = new SpecializareDTO();

        specializareDTO.setIdSpecializare(specializare.getIdSpecializare());
        specializareDTO.setNume(specializare.getNume());

        return specializareDTO;
    }

    public List<SpecializareDTO> toDTOList(List<Specializare> specializari) {
        List<SpecializareDTO> specializareDTOs = new ArrayList<>();
        for (Specializare specializare : specializari) {
            specializareDTOs.add(toDTO(specializare));
        }
        return specializareDTOs;
    }

    public Specializare toEntity(SpecializareDTO specializareDTO) {
        Specializare specializare = new Specializare();
        specializare.setIdSpecializare(specializareDTO.getIdSpecializare());
        specializare.setNume(specializareDTO.getNume());

        return specializare;
    }

    public void updateEntityFromDTO(SpecializareDTO specializareDTO, Specializare specializare) {
        specializare.setIdSpecializare(specializareDTO.getIdSpecializare());
        specializare.setNume(specializareDTO.getNume());
    }

}