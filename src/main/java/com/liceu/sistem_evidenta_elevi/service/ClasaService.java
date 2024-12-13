package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.*;
import com.liceu.sistem_evidenta_elevi.entity.*;

import java.util.List;
import java.util.Set;

public interface ClasaService {

    // CRUD pentru clasa
    List<Clasa> getAllClase();
    Clasa getClasaById(Integer id);
    Clasa adaugaClasa(ClasaRequestDTO clasaRequest);
    Clasa actualizareClasa(ClasaRequestDTO clasa);
    void stergeClasa(Integer idClasa);

    // Gestionare elev
    Set<Elev> getEleviByClasa(Integer idClasa);
    Elev getElevById(Integer id);
    Elev adaugaElevInClasa(ElevRequestDTO elevRequest);
    Elev actualizareElev(ElevRequestDTO elevRequest);
    void stergeElev(Integer idElev);

    // Gestionare note
    List<Nota> getNoteElevMaterie(Integer idElev, Integer idMaterie);
    Nota adaugaNotaElev(NotaRequestDTO notaRequest);
    Nota actualizareNotaElev(NotaRequestDTO notaRequest);
    void stergeNotaElev(Integer idNota);

    // Gestionare absente
    List<Absenta> getAbsenteElevMaterie(Integer idElev, Integer idMaterie);
    Absenta adaugaAbsentaElev(AbsentaRequestDTO absentaRequest);
    Absenta actualizareAbsentaElev(AbsentaRequestDTO absentaRequest);
    void stergeAbsentaElev(Integer idAbsenta);

    ClasaMaterieProfesor adaugaMaterieSiProfesorLaClasa(Integer idClasa, Integer idProfesor, Integer idMaterie);
    ClasaMaterieProfesor stergereMaterieSiProfesorDinClasa(Integer idClasa, Integer idProfesor, Integer idMaterie);

    List<Materie> getMateriiDinClasa(Integer idClasa);

    // materiile predate de un profesor intr-o clasa
    List<Materie> getMateriiPredateDeProfesorInClasa(Integer idClasa, Integer idProfesor);

    // clasele in care preda un profesor
    List<Clasa> getClaseProfesor(Integer idProfesor);

    // profesorii care predau intr-o clasa
    List<Profesor> getProfesoriDinClasa(Integer idClasa);

}
