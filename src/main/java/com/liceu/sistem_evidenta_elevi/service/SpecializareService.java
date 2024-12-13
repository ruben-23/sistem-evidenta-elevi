package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.SpecializareRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Specializare;

import java.util.List;

public interface SpecializareService {

    List<Specializare> getAllSpecializari();
    Specializare getSpecializareById(Integer id);
    Specializare actualizareSpecializare(SpecializareRequestDTO specializareRequest);
    Specializare adaugaSpecializare(SpecializareRequestDTO specializareRequest);
}