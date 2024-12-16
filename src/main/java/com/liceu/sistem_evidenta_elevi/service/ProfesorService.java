package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.entity.User;

import java.util.List;

public interface ProfesorService {
    List<Profesor> getAllProfesori();
    Profesor getProfesorById(Integer id);
    Profesor actualizareProfesor(ProfesorDTO profesorDTO);
    Profesor adaugaProfesor(ProfesorDTO profesorDTO, User user);
    void stergeProfesor(Integer idProfesor);
}
