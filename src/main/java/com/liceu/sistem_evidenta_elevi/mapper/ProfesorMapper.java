package com.liceu.sistem_evidenta_elevi.mapper;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorDTO;
import com.liceu.sistem_evidenta_elevi.entity.*;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru mapare entitatilor Profesor in DTO
 */

@Component
public class ProfesorMapper {

    private UserService userService;

    @Autowired
    public ProfesorMapper(UserService userService) {
        this.userService = userService;
    }

    public ProfesorDTO toDTO(Profesor profesor) {
        ProfesorDTO profesorDTO = new ProfesorDTO();

        profesorDTO.setIdProfesor(profesor.getIdProfesor());
        profesorDTO.setNume(profesor.getNume());
        profesorDTO.setPrenume(profesor.getPrenume());
        profesorDTO.setCNP(profesor.getCNP());
        profesorDTO.setNumarTelefon(profesor.getNumarTelefon());
        profesorDTO.setAdresa(profesor.getAdresa());

        return profesorDTO;
    }

    public List<ProfesorDTO> toDTOList(List<Profesor> profesori) {
        List<ProfesorDTO> profesorDTOs = new ArrayList<>();
        for (Profesor profesor : profesori) {
            profesorDTOs.add(toDTO(profesor));
        }
        return profesorDTOs;
    }

    public Profesor toEntity(ProfesorDTO profesorDTO) {

        User user = userService.getUserById(profesorDTO.getIdProfesor());

        Profesor profesor = new Profesor();
        profesor.setIdProfesor(profesorDTO.getIdProfesor());
        profesor.setNume(profesorDTO.getNume());
        profesor.setPrenume(profesorDTO.getPrenume());
        profesor.setCNP(profesorDTO.getCNP());
        profesor.setNumarTelefon(profesorDTO.getNumarTelefon());
        profesor.setAdresa(profesorDTO.getAdresa());
        profesor.setUser(user);

        return profesor;
    }

    public void updateEntityFromDTO(ProfesorDTO profesorDTO, Profesor profesor) {

        User user = userService.getUserById(profesorDTO.getIdProfesor());

        profesor.setNume(profesorDTO.getNume());
        profesor.setPrenume(profesorDTO.getPrenume());
        profesor.setCNP(profesorDTO.getCNP());
        profesor.setNumarTelefon(profesorDTO.getNumarTelefon());
        profesor.setAdresa(profesorDTO.getAdresa());
        profesor.setUser(user);

    }

}