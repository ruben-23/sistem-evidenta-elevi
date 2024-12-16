package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.entity.*;

import java.util.List;

public interface ClasaMaterieProfesorService {

    ClasaMaterieProfesor getClasaMaterieProfesorById(ClasaMaterieProfesorId id);
    ClasaMaterieProfesor adaugaMaterieSiProfesorLaClasa(Integer idClasa, Integer idProfesor, Integer idMaterie);
    void stergereMaterieSiProfesorDinClasa(Integer idClasa, Integer idProfesor, Integer idMaterie);

    List<Materie> getMateriiDinClasa(Integer idClasa);
    List<Materie> getMateriiPredateDeProfesorInClasa(Integer idProfesor, Integer idClasa);
    List<Clasa> getClaseProfesor(Integer idProfesor);
    List<Profesor> getProfesoriDinClasa(Integer idClasa);
}
