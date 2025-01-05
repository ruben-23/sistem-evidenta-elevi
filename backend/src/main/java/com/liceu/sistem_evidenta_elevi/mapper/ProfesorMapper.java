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

    /**
     * Converteste un obiect de tip Profesor intr-un obiect DTO de tip ProfesorDTO.
     *
     * @param profesor Entitatea Profesor care trebuie convertită intr-un DTO.
     * @return Un obiect ProfesorDTO populat cu datele din entitatea Profesor.
     */
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

    /**
     * Convertește o listă de obiecte Profesor într-o listă de obiecte ProfesorDTO.
     *
     * @param profesori Lista de entitati Profesor care trebuie convertita intr-o lista de DTO-uri.
     * @return O lista de obiecte ProfesorDTO populata cu datele din lista de entitati Profesor.
     */
    public List<ProfesorDTO> toDTOList(List<Profesor> profesori) {
        List<ProfesorDTO> profesorDTOs = new ArrayList<>();
        for (Profesor profesor : profesori) {
            profesorDTOs.add(toDTO(profesor));
        }
        return profesorDTOs;
    }

    /**
     * Converteste un obiect DTO de tip ProfesorDTO intr-o entitate de tip Profesor.
     *
     * @param profesorDTO DTO-ul Profesor care trebuie convertit într-o entitate Profesor.
     * @param user Obiectul User asociat.
     * @return O entitate Profesor populata cu datele din DTO-ul ProfesorDTO.
     */
    public Profesor toEntity(ProfesorDTO profesorDTO, User user) {

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

    /**
     * Actualizeaza o entitate Profesor cu datele dintr-un DTO de tip ProfesorDTO.
     *
     * @param profesorDTO DTO-ul care contine noile date pentru entitatea Profesor.
     * @param profesor Entitatea Profesor care va fi actualizata.
     * @param user Obiectul User asociat.
     */
    public void updateEntityFromDTO(ProfesorDTO profesorDTO, Profesor profesor, User user) {

        profesor.setNume(profesorDTO.getNume());
        profesor.setPrenume(profesorDTO.getPrenume());
        profesor.setCNP(profesorDTO.getCNP());
        profesor.setNumarTelefon(profesorDTO.getNumarTelefon());
        profesor.setAdresa(profesorDTO.getAdresa());
        profesor.setUser(user);

    }

}