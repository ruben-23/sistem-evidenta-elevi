package com.liceu.sistem_evidenta_elevi.mapper;


import com.liceu.sistem_evidenta_elevi.dto.NotaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.entity.Materie;
import com.liceu.sistem_evidenta_elevi.entity.Nota;
import com.liceu.sistem_evidenta_elevi.service.ElevService;
import com.liceu.sistem_evidenta_elevi.service.MaterieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru mapare entitatilor Nota in DTO
 */

@Component
public class NotaMapper {

    private ElevService elevService;
    private MaterieService materieService;

    @Autowired
    public NotaMapper(ElevService elevService, MaterieService materieService) {
        this.elevService = elevService;
        this.materieService = materieService;
    }

    public NotaDTO toDTO(Nota nota) {
        NotaDTO notaDTO = new NotaDTO();

        notaDTO.setIdNota(nota.getIdNota());
        notaDTO.setData(nota.getData());
        notaDTO.setValoare(nota.getValoare());
        notaDTO.setIdMaterie(nota.getMaterie().getIdMaterie());
        notaDTO.setIdElev(nota.getElev().getIdElev());

        return notaDTO;
    }

    public List<NotaDTO> toDTOList(List<Nota> note) {
        List<NotaDTO> notaDTOs = new ArrayList<>();
        for (Nota nota : note) {
            notaDTOs.add(toDTO(nota));
        }
        return notaDTOs;
    }

    public Nota toEntity(NotaDTO notaDTO) {

        Elev elev = elevService.getElevById(notaDTO.getIdElev());
        Materie materie = materieService.getMaterieById(notaDTO.getIdMaterie());

        Nota nota = new Nota();
        nota.setIdNota(notaDTO.getIdNota());
        nota.setData(notaDTO.getData());
        nota.setValoare(notaDTO.getValoare());
        nota.setElev(elev);
        nota.setMaterie(materie);

        return nota;
    }

    public void updateEntityFromDTO(NotaDTO notaDTO, Nota nota) {

        Elev elev = elevService.getElevById(notaDTO.getIdElev());
        Materie materie = materieService.getMaterieById(notaDTO.getIdMaterie());

        nota.setIdNota(notaDTO.getIdNota());
        nota.setData(notaDTO.getData());
        nota.setValoare(notaDTO.getValoare());
        nota.setElev(elev);
        nota.setMaterie(materie);

    }
}