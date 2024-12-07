package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;

import java.util.List;

public interface ProfesorService {

    List<Profesor> getAllProfesori();
    Profesor getProfesorById(Integer id);
    Profesor actualizareProfesor(ProfesorRequestDTO profesorRequest);
    Profesor adaugaProfesor(ProfesorRequestDTO profesorRequest);
    
}
