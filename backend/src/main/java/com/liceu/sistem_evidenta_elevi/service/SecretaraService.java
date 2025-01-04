package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.SecretaraDTO;
import com.liceu.sistem_evidenta_elevi.entity.Secretara;
import com.liceu.sistem_evidenta_elevi.entity.User;

import java.util.List;

public interface SecretaraService {
    List<Secretara> getAllSecretare();
    Secretara getSecretaraById(Integer id);
    Secretara actualizareSecretara(SecretaraDTO secretaraDTO);
    Secretara adaugaSecretara(SecretaraDTO secretaraDTO, User user);
    void stergeSecretara(Integer idSecretara);
}
