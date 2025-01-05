package com.liceu.sistem_evidenta_elevi.mapper;

import com.liceu.sistem_evidenta_elevi.dto.BursaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Bursa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care transforma entitatile Bursa in DTO-uri si invers.
 */
@Component
public class BursaMapper {

    /**
     * Converteste o entitate Bursa intr-un DTO BursaDTO.
     *
     * @param bursa Entitatea de convertit.
     * @return Obiectul BursaDTO rezultat.
     */
    public BursaDTO toDTO(Bursa bursa) {
        BursaDTO bursaDTO = new BursaDTO();
        bursaDTO.setIdBursa(bursa.getIdBursa());
        bursaDTO.setTip(bursa.getTip());
        bursaDTO.setSuma(bursa.getSuma());
        return bursaDTO;
    }

    /**
     * Converteste o lista de entitati Bursa intr-o lista de DTO-uri BursaDTO.
     *
     * @param burse Lista de entitati de convertit.
     * @return Lista de DTO-uri BursaDTO rezultate.
     */
    public List<BursaDTO> toDTOList(List<Bursa> burse) {
        List<BursaDTO> bursaDTOs = new ArrayList<>();
        for (Bursa bursa : burse) {
            bursaDTOs.add(toDTO(bursa));
        }
        return bursaDTOs;
    }

    /**
     * Converteste un DTO BursaDTO intr-o entitate Bursa.
     *
     * @param bursaDTO DTO-ul de convertit.
     * @return Entitatea Bursa rezultata.
     */
    public Bursa toEntity(BursaDTO bursaDTO) {
        Bursa bursa = new Bursa();
        bursa.setIdBursa(bursaDTO.getIdBursa());
        bursa.setTip(bursaDTO.getTip());
        bursa.setSuma(bursaDTO.getSuma());
        return bursa;
    }

    /**
     * Actualizeaza o entitate Bursa cu date dintr-un DTO BursaDTO.
     *
     * @param bursaDTO DTO-ul cu noile date.
     * @param bursa Entitatea de actualizat.
     */
    public void updateEntityFromDTO(BursaDTO bursaDTO, Bursa bursa) {
        bursa.setIdBursa(bursaDTO.getIdBursa());
        bursa.setTip(bursaDTO.getTip());
        bursa.setSuma(bursaDTO.getSuma());
    }
}
