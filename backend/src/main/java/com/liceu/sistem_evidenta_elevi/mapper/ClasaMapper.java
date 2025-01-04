package com.liceu.sistem_evidenta_elevi.mapper;

import com.liceu.sistem_evidenta_elevi.dto.ClasaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Clasa;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.entity.Specializare;
import com.liceu.sistem_evidenta_elevi.service.ProfesorService;
import com.liceu.sistem_evidenta_elevi.service.SpecializareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru mapare entitatilor clasa in DTO
 */
@Component
public class ClasaMapper {

    private final ProfesorService profesorService;
    private final SpecializareService specializareService;

    @Autowired
    ClasaMapper(ProfesorService profesorService, SpecializareService specializareService) {
        this.profesorService = profesorService;
        this.specializareService = specializareService;
    }

    public ClasaDTO toDTO(Clasa clasa) {
        ClasaDTO clasaDTO = new ClasaDTO();
        clasaDTO.setIdClasa(clasa.getIdClasa());
        clasaDTO.setNume(clasa.getNume());
        clasaDTO.setIdSpecializare(clasa.getSpecializare().getIdSpecializare());
        clasaDTO.setIdProfesor(clasa.getDiriginte().getIdProfesor());

        return clasaDTO;
    }

    public List<ClasaDTO> toDTOList(List<Clasa> clase) {
        List<ClasaDTO> clasaDTOs = new ArrayList<>();
        for (Clasa clasa : clase) {
            clasaDTOs.add(toDTO(clasa));
        }
        return clasaDTOs;
    }

    public Clasa toEntity(ClasaDTO clasaDTO) {

        Profesor diriginte = profesorService.getProfesorById(clasaDTO.getIdProfesor());
        Specializare specializare = specializareService.getSpecializareById(clasaDTO.getIdSpecializare());

        Clasa clasa = new Clasa();
        clasa.setIdClasa(clasaDTO.getIdClasa());
        clasa.setNume(clasaDTO.getNume());
        clasa.setDiriginte(diriginte);
        clasa.setSpecializare(specializare);

        return clasa;
    }

    public void updateEntityFromDTO(ClasaDTO clasaDTO, Clasa clasa) {

        Profesor diriginte = profesorService.getProfesorById(clasaDTO.getIdProfesor());
        Specializare specializare = specializareService.getSpecializareById(clasaDTO.getIdSpecializare());

        clasa.setNume(clasaDTO.getNume());
        clasa.setDiriginte(diriginte);
        clasa.setSpecializare(specializare);

    }

}
