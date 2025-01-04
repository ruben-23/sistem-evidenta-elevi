package com.liceu.sistem_evidenta_elevi.mapper;


import com.liceu.sistem_evidenta_elevi.dto.AbsentaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Absenta;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.entity.Materie;
import com.liceu.sistem_evidenta_elevi.service.ElevService;
import com.liceu.sistem_evidenta_elevi.service.MaterieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru mapare entitatilor Absenta in DTO
 */

@Component
public class AbsentaMapper {

    private final ElevService elevService;
    private final MaterieService materieService;

    @Autowired
    public AbsentaMapper(ElevService elevService, MaterieService materieService) {
        this.elevService = elevService;
        this.materieService = materieService;
    }

    public AbsentaDTO toDTO(Absenta absenta) {
        AbsentaDTO absentaDTO = new AbsentaDTO();

        absentaDTO.setIdAbsenta(absenta.getIdAbsenta());
        absentaDTO.setData(absenta.getData());
        absentaDTO.setModul(absenta.getModul());
        absentaDTO.setIdMaterie(absenta.getMaterie().getIdMaterie());
        absentaDTO.setIdElev(absenta.getElev().getIdElev());

        return absentaDTO;
    }

    public List<AbsentaDTO> toDTOList(List<Absenta> note) {
        List<AbsentaDTO> absentaDTOs = new ArrayList<>();
        for (Absenta absenta : note) {
            absentaDTOs.add(toDTO(absenta));
        }
        return absentaDTOs;
    }

    public Absenta toEntity(AbsentaDTO absentaDTO) {

        Elev elev = elevService.getElevById(absentaDTO.getIdElev());
        Materie materie = materieService.getMaterieById(absentaDTO.getIdMaterie());

        Absenta absenta = new Absenta();
        absenta.setIdAbsenta(absentaDTO.getIdAbsenta());
        absenta.setData(absentaDTO.getData());
        absenta.setModul(absentaDTO.getModul());
        absenta.setElev(elev);
        absenta.setMaterie(materie);

        return absenta;
    }

    public void updateEntityFromDTO(AbsentaDTO absentaDTO, Absenta absenta) {

        Elev elev = elevService.getElevById(absentaDTO.getIdElev());
        Materie materie = materieService.getMaterieById(absentaDTO.getIdMaterie());

        absenta.setIdAbsenta(absentaDTO.getIdAbsenta());
        absenta.setData(absentaDTO.getData());
        absenta.setModul(absentaDTO.getModul());
        absenta.setElev(elev);
        absenta.setMaterie(materie);

    }
}