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

/**
 * Implementare a serviciului ClasaMaterieProfesor.
 * Contine metode pentru gestionarea relatiei dintre clase, profesori si materii.
 */
@Service
public class ClasaMaterieProfesorServiceImpl implements ClasaMaterieProfesorService {

    private final ClasaMaterieProfesorRepository repository;
    private final ClasaService clasaService;
    private final ProfesorService profesorService;
    private final MaterieService materieService;

    /**
     * Constructor pentru injectarea dependintelor.
     *
     * @param repository      Repositorul pentru gestionarea operatiunilor cu ClasaMaterieProfesor.
     * @param clasaService    Serviciul pentru gestionarea claselor.
     * @param profesorService Serviciul pentru gestionarea profesorilor.
     * @param materieService  Serviciul pentru gestionarea materiilor.
     */
    @Autowired
    public ClasaMaterieProfesorServiceImpl(ClasaMaterieProfesorRepository repository,
                                           ClasaService clasaService, ProfesorService profesorService,
                                           MaterieService materieService) {
        this.repository = repository;
        this.clasaService = clasaService;
        this.profesorService = profesorService;
        this.materieService = materieService;
    }

    /**
     * Obtine o entitate ClasaMaterieProfesor pe baza unui ID composite.
     *
     * @param id ID-ul compus al entitatii ClasaMaterieProfesor.
     * @return Entitatea ClasaMaterieProfesor corespunzatoare ID-ului.
     * @throws RuntimeException Daca entitatea nu este gasita.
     */
    @Override
    public ClasaMaterieProfesor getClasaMaterieProfesorById(ClasaMaterieProfesorId id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClasaMaterieProfesor nu a fost gasita"));
    }

    /**
     * Adauga o materie si un profesor la o clasa.
     *
     * @param idClasa   ID-ul clasei.
     * @param idProfesor ID-ul profesorului.
     * @param idMaterie  ID-ul materiei.
     * @return Entitatea ClasaMaterieProfesor adaugata.
     */
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

    /**
     * Sterge o materie si un profesor dintr-o clasa.
     *
     * @param idClasa   ID-ul clasei.
     * @param idProfesor ID-ul profesorului.
     * @param idMaterie  ID-ul materiei.
     */
    @Transactional
    @Override
    public void stergereMaterieSiProfesorDinClasa(Integer idClasa, Integer idProfesor, Integer idMaterie) {
        // refacere id pentru entitate
        ClasaMaterieProfesorId id = new ClasaMaterieProfesorId(idClasa, idProfesor, idMaterie);
        ClasaMaterieProfesor entitate = getClasaMaterieProfesorById(id);
        repository.delete(entitate);
    }

    /**
     * Obtine lista materiilor dintr-o clasa.
     *
     * @param idClasa ID-ul clasei.
     * @return Lista materiilor din clasa respectiva.
     */
    @Override
    public List<Materie> getMateriiDinClasa(Integer idClasa){
        return repository.findMateriiByIdClasa(idClasa);
    }

    /**
     * Obtine materiile predate de un profesor intr-o clasa.
     *
     * @param idProfesor ID-ul profesorului.
     * @param idClasa    ID-ul clasei.
     * @return Lista materiilor predate de profesor in clasa respectiva.
     */
    @Override
    public List<Materie> getMateriiPredateDeProfesorInClasa(Integer idProfesor, Integer idClasa){
        return repository.findMateriiByProfesorAndClasa(idProfesor, idClasa);
    }

    /**
     * Obtine clasele in care preda un anumit profesor.
     *
     * @param idProfesor ID-ul profesorului.
     * @return Lista claselor in care preda profesorul respectiv.
     */
    @Override
    public List<Clasa> getClaseProfesor(Integer idProfesor){
        return repository.findClaseByProfesorId(idProfesor);
    }

    /**
     * Obtine profesorii care predau intr-o anumita clasa.
     *
     * @param idClasa ID-ul clasei.
     * @return Lista profesorilor care predau in clasa respectiva.
     */
    @Override
    public List<Profesor> getProfesoriDinClasa(Integer idClasa){
        return repository.findProfesoriByClasaId(idClasa);
    }
}