package com.liceu.sistem_evidenta_elevi.mapper;

import com.liceu.sistem_evidenta_elevi.dto.ClasaMaterieProfesorDTO;
import com.liceu.sistem_evidenta_elevi.entity.ClasaMaterieProfesor;
import org.springframework.stereotype.Component;

/**
 * Mapper pentru conversia entitatilor de tip ClasaMaterieProfesor in obiecte DTO de tip ClasaMaterieProfesorDTO.
 *
 * Aceasta clasa este responsabila pentru maparea datelor din entitatea ClasaMaterieProfesor intr-un obiect DTO
 * care poate fi folosit pentru a transmite informatii intre straturi, in special pentru raspunsuri API.
 * Conversia include doar ID-urile obiectelor asociate: Materie, Profesor si Clasa.
 */
@Component
public class ClasaMaterieProfesorMapper {

    /**
     * Converteste o entitate ClasaMaterieProfesor intr-un obiect DTO de tip ClasaMaterieProfesorDTO.
     *
     * @param entity Entitatea ClasaMaterieProfesor care trebuie convertita intr-un DTO.
     * @return Un obiect de tip ClasaMaterieProfesorDTO populat cu datele extrase din entitatea furnizata.
     *         DTO-ul contine ID-urile obiectelor asociate Materie, Profesor si Clasa.
     */
    public ClasaMaterieProfesorDTO toDTO(ClasaMaterieProfesor entity) {
        ClasaMaterieProfesorDTO clasaMaterieProfesorDTO = new ClasaMaterieProfesorDTO();

        clasaMaterieProfesorDTO.setIdMaterie(entity.getMaterie().getIdMaterie());
        clasaMaterieProfesorDTO.setIdProfesor(entity.getProfesor().getIdProfesor());
        clasaMaterieProfesorDTO.setIdClasa(entity.getClasa().getIdClasa());

        return clasaMaterieProfesorDTO;
    }

}
