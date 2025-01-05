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

    /**
     * Constructorul clasei NotaMapper.
     *
     * @param elevService Serviciul care permite obtinerea unui elev pe baza ID-ului.
     * @param materieService Serviciul care permite obtinerea unei materii pe baza ID-ului.
     */
    @Autowired
    public NotaMapper(ElevService elevService, MaterieService materieService) {
        this.elevService = elevService;
        this.materieService = materieService;
    }

    /**
     * Converteste un obiect de tip Nota intr-un obiect DTO de tip NotaDTO.
     *
     * @param nota Entitatea Nota care trebuie convertită într-un DTO.
     * @return Un obiect NotaDTO populat cu datele din entitatea Nota.
     */
    public NotaDTO toDTO(Nota nota) {
        NotaDTO notaDTO = new NotaDTO();

        notaDTO.setIdNota(nota.getIdNota());
        notaDTO.setData(nota.getData());
        notaDTO.setValoare(nota.getValoare());
        notaDTO.setModul(nota.getModul());
        notaDTO.setIdMaterie(nota.getMaterie().getIdMaterie());
        notaDTO.setIdElev(nota.getElev().getIdElev());

        return notaDTO;
    }

    /**
     * Convertește o lista de obiecte Nota intr-o lista de obiecte NotaDTO.
     *
     * @param note Lista de entitati Nota care trebuie convertita intr-o listă de DTO-uri.
     * @return O lista de obiecte NotaDTO populata cu datele din lista de entitati Nota.
     */
    public List<NotaDTO> toDTOList(List<Nota> note) {
        List<NotaDTO> notaDTOs = new ArrayList<>();
        for (Nota nota : note) {
            notaDTOs.add(toDTO(nota));
        }
        return notaDTOs;
    }

    /**
     * Converteste un obiect DTO de tip NotaDTO intr-o entitate de tip Nota.
     *
     * @param notaDTO DTO-ul Nota care trebuie convertit intr-o entitate Nota.
     * @return O entitate Nota populata cu datele din DTO-ul NotaDTO.
     */
    public Nota toEntity(NotaDTO notaDTO) {

        Elev elev = elevService.getElevById(notaDTO.getIdElev());
        Materie materie = materieService.getMaterieById(notaDTO.getIdMaterie());

        Nota nota = new Nota();
        nota.setIdNota(notaDTO.getIdNota());
        nota.setData(notaDTO.getData());
        nota.setValoare(notaDTO.getValoare());
        nota.setModul(notaDTO.getModul());
        nota.setElev(elev);
        nota.setMaterie(materie);

        return nota;
    }

    /**
     * Actualizează o entitate Nota cu datele dintr-un DTO de tip NotaDTO.
     *
     * @param notaDTO DTO-ul care contine noile date pentru entitatea Nota.
     * @param nota Entitatea Nota care va fi actualizata.
     */
    public void updateEntityFromDTO(NotaDTO notaDTO, Nota nota) {

        Elev elev = elevService.getElevById(notaDTO.getIdElev());
        Materie materie = materieService.getMaterieById(notaDTO.getIdMaterie());

        nota.setIdNota(notaDTO.getIdNota());
        nota.setData(notaDTO.getData());
        nota.setValoare(notaDTO.getValoare());
        nota.setModul(notaDTO.getModul());
        nota.setElev(elev);
        nota.setMaterie(materie);

    }
}