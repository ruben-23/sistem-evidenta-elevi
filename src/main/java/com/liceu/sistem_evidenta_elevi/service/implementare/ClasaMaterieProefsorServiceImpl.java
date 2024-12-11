package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.entity.*;
import com.liceu.sistem_evidenta_elevi.repository.ClasaMaterieProfesorRepository;
import com.liceu.sistem_evidenta_elevi.service.ClasaMaterieProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasaMaterieProefsorServiceImpl implements ClasaMaterieProfesorService {

    private final ClasaMaterieProfesorRepository clasaMaterieProfesorRepository;

    @Autowired
    public ClasaMaterieProefsorServiceImpl(ClasaMaterieProfesorRepository clasaMaterieProfesorRepository) {
        this.clasaMaterieProfesorRepository = clasaMaterieProfesorRepository;
    }

    @Override
    public ClasaMaterieProfesor getClasaMaterieProfesorById(ClasaMaterieProfesorId id){
        return clasaMaterieProfesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClasaMaterieProfesor nu a fost gasita"));
    }

    @Override
    public ClasaMaterieProfesor adaugaMaterieSiProfesorLaClasa(Clasa clasa, Profesor profesor, Materie materie) {

        ClasaMaterieProfesor clasaMaterieProfesor = new ClasaMaterieProfesor();
        clasaMaterieProfesor.setClasa(clasa);
        clasaMaterieProfesor.setProfesor(profesor);
        clasaMaterieProfesor.setMaterie(materie);

        return clasaMaterieProfesorRepository.save(clasaMaterieProfesor);
    }

    @Override
    public ClasaMaterieProfesor stergereMaterieSiProfesorDinClasa(Integer idClasa, Integer idProfesor, Integer idMaterie) {
        // refacere  id pentru entitate
        ClasaMaterieProfesorId id = new ClasaMaterieProfesorId(idClasa, idProfesor, idMaterie);
        ClasaMaterieProfesor entitate = getClasaMaterieProfesorById(id);
        clasaMaterieProfesorRepository.delete(entitate);
        return entitate;
    }

    @Override
    public List<Materie> getMateriiDinClasa(Integer idClasa){
        return clasaMaterieProfesorRepository.findMateriiByClasaId(idClasa);
    }

    @Override
    public List<Materie> getMateriiPredateDeProfesorInClasa(Integer idProfesor, Integer idClasa){
        return clasaMaterieProfesorRepository.findMateriiByProfesorAndClasa(idProfesor, idClasa);
    }

    @Override
    public List<Clasa> getClaseProfesor(Integer idProfesor){
        return clasaMaterieProfesorRepository.findClaseByProfesorId(idProfesor);
    }

    @Override
    public List<Profesor> getProfesoriDinClasa(Integer idClasa){
        return clasaMaterieProfesorRepository.findProfesoriByClasaId(idClasa);
    }
}
