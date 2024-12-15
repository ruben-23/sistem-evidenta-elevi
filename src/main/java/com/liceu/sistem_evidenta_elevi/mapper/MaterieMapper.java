package com.liceu.sistem_evidenta_elevi.mapper;

import com.liceu.sistem_evidenta_elevi.dto.MaterieDTO;
import com.liceu.sistem_evidenta_elevi.entity.Materie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru mapare entitatilor Materie in DTO
 */

@Component
public class MaterieMapper {

    public MaterieDTO toDTO(Materie materie) {
        MaterieDTO materieDTO = new MaterieDTO();

        materieDTO.setIdMaterie(materie.getIdMaterie());
        materieDTO.setNume(materie.getNume());

        return materieDTO;
    }

    public List<MaterieDTO> toDTOList(List<Materie> materii) {
        List<MaterieDTO> materieDTOs = new ArrayList<>();
        for (Materie materie : materii) {
            materieDTOs.add(toDTO(materie));
        }
        return materieDTOs;
    }

    public Materie toEntity(MaterieDTO materieDTO) {
        Materie materie = new Materie();
        materie.setIdMaterie(materieDTO.getIdMaterie());
        materie.setNume(materieDTO.getNume());

        return materie;
    }

    public void updateEntityFromDTO(MaterieDTO materieDTO, Materie materie) {
        materie.setIdMaterie(materieDTO.getIdMaterie());
        materie.setNume(materieDTO.getNume());
    }
}