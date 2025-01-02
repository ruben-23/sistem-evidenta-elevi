package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.entity.*;
import com.liceu.sistem_evidenta_elevi.repository.ClasaMaterieProfesorRepository;
import com.liceu.sistem_evidenta_elevi.service.ClasaMaterieProfesorService;
import com.liceu.sistem_evidenta_elevi.service.ClasaService;
import com.liceu.sistem_evidenta_elevi.service.MaterieService;
import com.liceu.sistem_evidenta_elevi.service.ProfesorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasaMaterieProfesorServiceImpl implements ClasaMaterieProfesorService {

    private final ClasaMaterieProfesorRepository repository;
    private final ClasaService clasaService;
    private final ProfesorService profesorService;
    private final MaterieService materieService;

    @Autowired
    public ClasaMaterieProfesorServiceImpl(ClasaMaterieProfesorRepository repository,
                                           ClasaService clasaService, ProfesorService profesorService,
                                           MaterieService materieService) {
        this.repository = repository;
        this.clasaService = clasaService;
        this.profesorService = profesorService;
        this.materieService = materieService;
    }

    @Override
    public ClasaMaterieProfesor getClasaMaterieProfesorById(ClasaMaterieProfesorId id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClasaMaterieProfesor nu a fost gasita"));
    }

    @Transactional
    @Override
    public ClasaMaterieProfesor adaugaMaterieSiProfesorLaClasa(Integer idClasa, Integer idProfesor, Integer idMaterie) {
        Clasa clasa = clasaService.getClasaById(idClasa);
        Profesor profesor = profesorService.getProfesorById(idProfesor);
        Materie materie = materieService.getMaterieById(idMaterie);

        ClasaMaterieProfesor clasaMaterieProfesor = new ClasaMaterieProfesor();
        clasaMaterieProfesor.setClasa(clasa);
        clasaMaterieProfesor.setProfesor(profesor);
        clasaMaterieProfesor.setMaterie(materie);

        return repository.save(clasaMaterieProfesor);
    }

    @Transactional
    @Override
    public void stergereMaterieSiProfesorDinClasa(Integer idClasa, Integer idProfesor, Integer idMaterie) {
        // refacere  id pentru entitate
        ClasaMaterieProfesorId id = new ClasaMaterieProfesorId(idClasa, idProfesor, idMaterie);
        ClasaMaterieProfesor entitate = getClasaMaterieProfesorById(id);
        repository.delete(entitate);
    }

    @Override
    public List<Materie> getMateriiDinClasa(Integer idClasa){
        return repository.findMateriiByIdClasa(idClasa);
    }

    // materiile predate de un profesor intr-o clasa
    @Override
    public List<Materie> getMateriiPredateDeProfesorInClasa(Integer idProfesor, Integer idClasa){
        return repository.findMateriiByProfesorAndClasa(idProfesor, idClasa);
    }

    // clasele in care preda un profesor
    @Override
    public List<Clasa> getClaseProfesor(Integer idProfesor){
        return repository.findClaseByProfesorId(idProfesor);
    }

    // profesorii care predau intr-o clasa
    @Override
    public List<Profesor> getProfesoriDinClasa(Integer idClasa){
        return repository.findProfesoriByClasaId(idClasa);
    }
}
