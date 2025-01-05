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
 * Mapper pentru conversia entitatilor Elev în obiecte DTO de tip ElevDTO.
 *
 * Aceasta clasa este responsabila pentru maparea datelor din entitatea Elev intr-un obiect DTO,
 * precum si pentru conversia inversa, adica din DTO in entitate.
 * De asemenea, ofera functionalitatea de actualizare a entitatilor Elev cu datele din DTO.
 */
@Component
public class ElevMapper {

    private ClasaService clasaService;

    /**
     * Constructorul clasei ElevMapper.
     *
     * @param clasaService Serviciul care permite obtinerea unei clase pe baza ID-ului.
     */
    @Autowired
    public ElevMapper(ClasaService clasaService) {
        this.clasaService = clasaService;
    }

    /**
     * Converteste un obiect de tip Elev intr-un obiect DTO de tip ElevDTO.
     *
     * @param elev Entitatea Elev care trebuie convertita intr-un DTO.
     * @return Un obiect ElevDTO populat cu datele din entitatea Elev.
     */
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

    /**
     * Converteste o lista de obiecte Elev intr-o lista de obiecte ElevDTO.
     *
     * @param elevi Lista de entitati Elev care trebuie convertita intr-o lista de DTO-uri.
     * @return O lista de obiecte ElevDTO populata cu datele din lista de entitati Elev.
     */
    public List<ElevDTO> toDTOList(List<Elev> elevi) {
        List<ElevDTO> elevDTOs = new ArrayList<>();
        for (Elev elev : elevi) {
            elevDTOs.add(toDTO(elev));
        }
        return elevDTOs;
    }

    /**
     * Converteste un obiect DTO de tip ElevDTO intr-o entitate de tip Elev.
     *
     * @param elevDTO DTO-ul Elev care trebuie convertit într-o entitate Elev.
     * @return O entitate Elev populata cu datele din DTO-ul ElevDTO.
     */
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

    /**
     * Actualizeaza o entitate Elev cu datele dintr-un DTO de tip ElevDTO.
     *
     * @param elevDTO DTO-ul care contine noile date pentru entitatea Elev.
     * @param elev Entitatea Elev care va fi actualizata.
     */
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
