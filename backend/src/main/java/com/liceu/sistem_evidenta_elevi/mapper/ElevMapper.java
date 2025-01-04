package com.liceu.sistem_evidenta_elevi.mapper;


import com.liceu.sistem_evidenta_elevi.dto.ElevDTO;
import com.liceu.sistem_evidenta_elevi.entity.*;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.service.ClasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru mapare entitatilor Elev in DTO
 */

@Component
public class ElevMapper {

    private ClasaService clasaService;

    @Autowired
    public ElevMapper(ClasaService clasaService) {
        this.clasaService = clasaService;
    }

    public ElevDTO toDTO(Elev elev) {
        ElevDTO elevDTO = new ElevDTO();
        elevDTO.setIdElev(elev.getIdElev());
        elevDTO.setNume(elev.getNume());
        elevDTO.setPrenume(elev.getPrenume());
        elevDTO.setCNP(elev.getCNP());
        elevDTO.setSex(elev.getSex());
        elevDTO.setNumarTelefon(elev.getNumarTelefon());
        elevDTO.setAdresa(elev.getAdresa());
        elevDTO.setDataNasterii(elev.getDataNasterii());
        elevDTO.setIdClasa(elev.getClasa().getIdClasa());

        return elevDTO;
    }

    public List<ElevDTO> toDTOList(List<Elev> elevi) {
        List<ElevDTO> elevDTOs = new ArrayList<>();
        for (Elev elev : elevi) {
            elevDTOs.add(toDTO(elev));
        }
        return elevDTOs;
    }

    public Elev toEntity(ElevDTO elevDTO) {

        Clasa clasa = clasaService.getClasaById(elevDTO.getIdClasa());

        Elev elev = new Elev();
        elev.setIdElev(elevDTO.getIdElev());
        elev.setNume(elevDTO.getNume());
        elev.setPrenume(elevDTO.getPrenume());
        elev.setCNP(elevDTO.getCNP());
        elev.setSex(elevDTO.getSex());
        elev.setNumarTelefon(elevDTO.getNumarTelefon());
        elev.setAdresa(elevDTO.getAdresa());
        elev.setDataNasterii(elevDTO.getDataNasterii());
        elev.setClasa(clasa);

        return elev;
    }

    public void updateEntityFromDTO(ElevDTO elevDTO, Elev elev) {

        Clasa clasa = clasaService.getClasaById(elevDTO.getIdClasa());

        elev.setNume(elevDTO.getNume());
        elev.setPrenume(elevDTO.getPrenume());
        elev.setCNP(elevDTO.getCNP());
        elev.setSex(elevDTO.getSex());
        elev.setNumarTelefon(elevDTO.getNumarTelefon());
        elev.setAdresa(elevDTO.getAdresa());
        elev.setDataNasterii(elevDTO.getDataNasterii());
        elev.setClasa(clasa);

    }

}