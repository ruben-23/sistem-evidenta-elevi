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
 * Clasa pentru maparea entitatilor de tip {@link Clasa} la obiecte de tip {@link ClasaDTO}
 * si invers.
 */
@Component
public class ClasaMapper {

    private final ProfesorService profesorService;
    private final SpecializareService specializareService;

    /**
     * Constructor pentru injectarea serviciilor necesare.
     *
     * @param profesorService serviciul pentru gestionarea profesorilor.
     * @param specializareService serviciul pentru gestionarea specializarilor.
     */
    @Autowired
    public ClasaMapper(ProfesorService profesorService, SpecializareService specializareService) {
        this.profesorService = profesorService;
        this.specializareService = specializareService;
    }

    /**
     * Mapare de la entitatea {@link Clasa} la DTO.
     *
     * @param clasa entitatea {@link Clasa} care trebuie mapata.
     * @return obiectul {@link ClasaDTO} rezultat.
     */
    public ClasaDTO toDTO(Clasa clasa) {
        ClasaDTO clasaDTO = new ClasaDTO();
        clasaDTO.setIdClasa(clasa.getIdClasa());
        clasaDTO.setNume(clasa.getNume());
        clasaDTO.setIdSpecializare(clasa.getSpecializare().getIdSpecializare());
        clasaDTO.setIdProfesor(clasa.getDiriginte().getIdProfesor());

        return clasaDTO;
    }

    /**
     * Mapare de la o lista de entitati {@link Clasa} la o lista de DTO-uri.
     *
     * @param clase lista de entitati {@link Clasa} care trebuie mapata.
     * @return lista de obiecte {@link ClasaDTO} rezultate.
     */
    public List<ClasaDTO> toDTOList(List<Clasa> clase) {
        List<ClasaDTO> clasaDTOs = new ArrayList<>();
        for (Clasa clasa : clase) {
            clasaDTOs.add(toDTO(clasa));
        }
        return clasaDTOs;
    }

    /**
     * Mapare de la DTO-ul {@link ClasaDTO} la entitatea {@link Clasa}.
     *
     * @param clasaDTO obiectul {@link ClasaDTO} care trebuie mapat.
     * @return entitatea {@link Clasa} rezultata.
     */
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

    /**
     * Actualizeaza o entitate de tip {@link Clasa} pe baza unui DTO {@link ClasaDTO}.
     *
     * @param clasaDTO obiectul {@link ClasaDTO} cu noile date.
     * @param clasa entitatea {@link Clasa} care trebuie actualizata.
     */
    public void updateEntityFromDTO(ClasaDTO clasaDTO, Clasa clasa) {
        Profesor diriginte = profesorService.getProfesorById(clasaDTO.getIdProfesor());
        Specializare specializare = specializareService.getSpecializareById(clasaDTO.getIdSpecializare());

        clasa.setNume(clasaDTO.getNume());
        clasa.setDiriginte(diriginte);
        clasa.setSpecializare(specializare);
    }
}
