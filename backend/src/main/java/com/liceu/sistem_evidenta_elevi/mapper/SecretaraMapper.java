package com.liceu.sistem_evidenta_elevi.mapper;

import com.liceu.sistem_evidenta_elevi.dto.SecretaraDTO;
import com.liceu.sistem_evidenta_elevi.entity.Secretara;
import com.liceu.sistem_evidenta_elevi.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru maparea entitatilor Secretara in obiecte DTO de tip SecretaraDTO.
 *
 * Aceasta clasa este responsabila pentru conversia datelor din entitatea Secretara intr-un obiect DTO,
 * precum si pentru conversia inversa, adica din DTO in entitate. De asemenea, ofera functionalitatea
 * de actualizare a entitatilor Secretara cu datele din DTO.
 */
@Component
public class SecretaraMapper {

    /**
     * Converteste un obiect de tip Secretara intr-un obiect DTO de tip SecretaraDTO.
     *
     * @param secretara Entitatea Secretara care trebuie convertita intr-un DTO.
     * @return Un obiect SecretaraDTO populat cu datele din entitatea Secretara.
     */
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

    /**
     * Converteste o lista de obiecte Secretara intr-o lista de obiecte SecretaraDTO.
     *
     * @param secretare Lista de entitati Secretara care trebuie convertita intr-o lista de DTO-uri.
     * @return O lista de obiecte SecretaraDTO populata cu datele din lista de entitati Secretara.
     */
    public List<SecretaraDTO> toDTOList(List<Secretara> secretare) {
        List<SecretaraDTO> secretaraDTOs = new ArrayList<>();
        for (Secretara secretara : secretare) {
            secretaraDTOs.add(toDTO(secretara));
        }
        return secretaraDTOs;
    }

    /**
     * Converteste un obiect DTO de tip SecretaraDTO intr-o entitate de tip Secretara.
     *
     * @param secretaraDTO DTO-ul Secretara care trebuie convertit intr-o entitate Secretara.
     * @param user Utilizatorul asociat cu secretara.
     * @return O entitate Secretara populata cu datele din DTO-ul SecretaraDTO.
     */
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

    /**
     * Actualizeaza o entitate Secretara cu datele dintr-un DTO de tip SecretaraDTO.
     *
     * @param secretaraDTO DTO-ul care contine noile date pentru entitatea Secretara.
     * @param secretara Entitatea Secretara care va fi actualizata.
     * @param user Utilizatorul asociat cu secretara.
     */
    public void updateEntityFromDTO(SecretaraDTO secretaraDTO, Secretara secretara, User user) {

        secretara.setNume(secretaraDTO.getNume());
        secretara.setPrenume(secretaraDTO.getPrenume());
        secretara.setCNP(secretaraDTO.getCNP());
        secretara.setNumarTelefon(secretaraDTO.getNumarTelefon());
        secretara.setAdresa(secretaraDTO.getAdresa());
        secretara.setUser(user);

    }
}