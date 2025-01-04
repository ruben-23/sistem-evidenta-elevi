package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.SpecializareDTO;
import com.liceu.sistem_evidenta_elevi.entity.Specializare;

import java.util.List;

public interface SpecializareService {
    List<Specializare> getAllSpecializari();
    Specializare getSpecializareById(Integer id);
    Specializare actualizareSpecializare(SpecializareDTO specializareDTO);
    Specializare adaugaSpecializare(SpecializareDTO specializareDTO);
    void stergeSpecializare(Integer idSpecializare);
}