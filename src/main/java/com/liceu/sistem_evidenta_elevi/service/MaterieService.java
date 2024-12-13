package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.MaterieRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Materie;

import java.util.List;

public interface MaterieService {

    List<Materie> getAllMaterii();
    Materie getMaterieById(Integer id);
    Materie actualizareMaterie(MaterieRequestDTO materie);
    Materie adaugaMaterie(MaterieRequestDTO materieRequest);

}