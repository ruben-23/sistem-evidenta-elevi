package com.liceu.sistem_evidenta_elevi.mapper;

import com.liceu.sistem_evidenta_elevi.dto.SecretaraDTO;
import com.liceu.sistem_evidenta_elevi.entity.Secretara;
import com.liceu.sistem_evidenta_elevi.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecretaraMapper {

    public SecretaraDTO toDTO(Secretara secretara) {
        SecretaraDTO secretaraDTO = new SecretaraDTO();

        secretaraDTO.setIdSecretara(secretara.getIdSecretara());
        secretaraDTO.setNume(secretara.getNume());
        secretaraDTO.setPrenume(secretara.getPrenume());
        secretaraDTO.setCNP(secretara.getCNP());
        secretaraDTO.setNumarTelefon(secretara.getNumarTelefon());
        secretaraDTO.setAdresa(secretara.getAdresa());

        return secretaraDTO;
    }

    public List<SecretaraDTO> toDTOList(List<Secretara> secretare) {
        List<SecretaraDTO> secretaraDTOs = new ArrayList<>();
        for (Secretara secretara : secretare) {
            secretaraDTOs.add(toDTO(secretara));
        }
        return secretaraDTOs;
    }

    public Secretara toEntity(SecretaraDTO secretaraDTO, User user) {

        Secretara secretara = new Secretara();
        secretara.setIdSecretara(secretaraDTO.getIdSecretara());
        secretara.setNume(secretaraDTO.getNume());
        secretara.setPrenume(secretaraDTO.getPrenume());
        secretara.setCNP(secretaraDTO.getCNP());
        secretara.setNumarTelefon(secretaraDTO.getNumarTelefon());
        secretara.setAdresa(secretaraDTO.getAdresa());
        secretara.setUser(user);

        return secretara;
    }

    public void updateEntityFromDTO(SecretaraDTO secretaraDTO, Secretara secretara, User user) {

        secretara.setNume(secretaraDTO.getNume());
        secretara.setPrenume(secretaraDTO.getPrenume());
        secretara.setCNP(secretaraDTO.getCNP());
        secretara.setNumarTelefon(secretaraDTO.getNumarTelefon());
        secretara.setAdresa(secretaraDTO.getAdresa());
        secretara.setUser(user);

    }

}