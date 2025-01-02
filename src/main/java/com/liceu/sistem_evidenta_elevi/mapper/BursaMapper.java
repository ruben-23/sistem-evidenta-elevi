package com.liceu.sistem_evidenta_elevi.mapper;

import com.liceu.sistem_evidenta_elevi.dto.BursaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Bursa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru mapare entitatilor Bursa in DTO
 */

@Component
public class BursaMapper {

    public BursaDTO toDTO(Bursa bursa) {
        BursaDTO bursaDTO = new BursaDTO();

        bursaDTO.setIdBursa(bursa.getIdBursa());
        bursaDTO.setTip(bursa.getTip());
        bursaDTO.setSuma(bursa.getSuma());
        return bursaDTO;
    }

    public List<BursaDTO> toDTOList(List<Bursa> burse) {
        List<BursaDTO> bursaDTOs = new ArrayList<>();
        for (Bursa bursa : burse) {
            bursaDTOs.add(toDTO(bursa));
        }
        return bursaDTOs;
    }

    public Bursa toEntity(BursaDTO bursaDTO) {
        Bursa bursa = new Bursa();
        bursa.setIdBursa(bursaDTO.getIdBursa());
        bursa.setTip(bursaDTO.getTip());
        bursa.setSuma(bursaDTO.getSuma());

        return bursa;
    }

    public void updateEntityFromDTO(BursaDTO bursaDTO, Bursa bursa) {
        bursa.setIdBursa(bursaDTO.getIdBursa());
        bursa.setTip(bursaDTO.getTip());
        bursa.setSuma(bursaDTO.getSuma());
    }
}