package com.liceu.sistem_evidenta_elevi.mapper;

import com.liceu.sistem_evidenta_elevi.dto.ClasaMaterieProfesorDTO;
import com.liceu.sistem_evidenta_elevi.entity.ClasaMaterieProfesor;
import org.springframework.stereotype.Component;

@Component
public class ClasaMaterieProfesorMapper {

    public ClasaMaterieProfesorDTO toDTO(ClasaMaterieProfesor entity) {
       ClasaMaterieProfesorDTO clasaMaterieProfesorDTO = new ClasaMaterieProfesorDTO();

        clasaMaterieProfesorDTO.setIdMaterie(entity.getMaterie().getIdMaterie());
        clasaMaterieProfesorDTO.setIdProfesor(entity.getProfesor().getIdProfesor());
        clasaMaterieProfesorDTO.setIdClasa(entity.getClasa().getIdClasa());

        return clasaMaterieProfesorDTO;
    }

}
