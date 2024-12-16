package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.SecretaraDTO;
import com.liceu.sistem_evidenta_elevi.entity.Secretara;

import java.util.List;

public interface SecretaraService {
    List<Secretara> getAllSecretare();
    Secretara getSecretaraById(Integer id);
    Secretara actualizareSecretara(SecretaraDTO secretaraDTO);
    Secretara adaugaSecretara(SecretaraDTO secretaraDTO);
    void stergeSecretara(Integer idSecretara);
}
