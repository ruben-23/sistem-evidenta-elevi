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
 * Clasa pentru maparea entitatilor de tip {@link Absenta} la obiecte de tip {@link AbsentaDTO}
 * si invers.
 */
@Component
public class AbsentaMapper {

    private final ElevService elevService;
    private final MaterieService materieService;

    /**
     * Constructor pentru injectarea serviciilor necesare.
     *
     * @param elevService    serviciul pentru gestionarea elevilor.
     * @param materieService serviciul pentru gestionarea materiilor.
     */
    @Autowired
    public AbsentaMapper(ElevService elevService, MaterieService materieService) {
        this.elevService = elevService;
        this.materieService = materieService;
    }

    /**
     * Mapare de la entitatea {@link Absenta} la DTO.
     *
     * @param absenta entitatea {@link Absenta} care trebuie mapata.
     * @return obiectul {@link AbsentaDTO} rezultat.
     */
    public AbsentaDTO toDTO(Absenta absenta) {
        AbsentaDTO absentaDTO = new AbsentaDTO();

        absentaDTO.setIdAbsenta(absenta.getIdAbsenta());
        absentaDTO.setData(absenta.getData());
        absentaDTO.setModul(absenta.getModul());
        absentaDTO.setIdMaterie(absenta.getMaterie().getIdMaterie());
        absentaDTO.setIdElev(absenta.getElev().getIdElev());

        return absentaDTO;
    }

    /**
     * Mapare de la o lista de entitati {@link Absenta} la o lista de DTO-uri.
     *
     * @param absente lista de entitati {@link Absenta} care trebuie mapata.
     * @return lista de obiecte {@link AbsentaDTO} rezultate.
     */
    public List<AbsentaDTO> toDTOList(List<Absenta> absente) {
        List<AbsentaDTO> absentaDTOs = new ArrayList<>();
        for (Absenta absenta : absente) {
            absentaDTOs.add(toDTO(absenta));
        }
        return absentaDTOs;
    }

    /**
     * Mapare de la DTO-ul {@link AbsentaDTO} la entitatea {@link Absenta}.
     *
     * @param absentaDTO obiectul {@link AbsentaDTO} care trebuie mapat.
     * @return entitatea {@link Absenta} rezultata.
     */
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

    /**
     * Actualizeaza o entitate de tip {@link Absenta} pe baza unui DTO {@link AbsentaDTO}.
     *
     * @param absentaDTO obiectul {@link AbsentaDTO} cu noile date.
     * @param absenta    entitatea {@link Absenta} care trebuie actualizata.
     */
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