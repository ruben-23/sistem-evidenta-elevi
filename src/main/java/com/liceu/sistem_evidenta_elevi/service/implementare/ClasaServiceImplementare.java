package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.AbsentaRequestDTO;
import com.liceu.sistem_evidenta_elevi.dto.ClasaRequestDTO;
import com.liceu.sistem_evidenta_elevi.dto.ElevRequestDTO;
import com.liceu.sistem_evidenta_elevi.dto.NotaRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.*;
import com.liceu.sistem_evidenta_elevi.repository.ClasaRepository;
import com.liceu.sistem_evidenta_elevi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClasaServiceImplementare implements ClasaService {

    private final ClasaRepository clasaRepository;
    private final ElevService elevService;
    private final MaterieService materieService;
    private final NotaService notaService;
    private final ProfesorService profesorService;
    private final SpecializareService specializareService;
    private final AbsentaService absentaService;
    private final ClasaMaterieProfesorService clasaMaterieProfesorService ;

    @Autowired
    public ClasaServiceImplementare(ClasaRepository clasaRepository, ElevService elevService,
                                     ProfesorService profesorService, SpecializareService specializareService,
                                    NotaService notaService, AbsentaService absentaService, MaterieService materieService,
                                    ClasaMaterieProfesorService clasaMaterieProfesorService) {
        this.clasaRepository = clasaRepository;
        this.elevService = elevService;
        this.profesorService = profesorService;
        this.specializareService = specializareService;
        this.materieService = materieService;
        this.notaService = notaService;
        this.absentaService = absentaService;
        this.clasaMaterieProfesorService = clasaMaterieProfesorService;
    }

    @Override
    public List<Clasa> getAllClase(){
        return clasaRepository.findAll();
    }

    @Override
    public Clasa getClasaById(Integer id){
        return clasaRepository.findById(id).
                orElseThrow(()->new RuntimeException("Clasa nu a fost gasita"));
    }

    @Override
    public Clasa actualizareClasa(ClasaRequestDTO clasaRequest){
        Clasa clasaActuala = getClasaById(clasaRequest.getIdClasa());
        clasaActuala.setNume(clasaRequest.getNume());
        return clasaRepository.save(clasaActuala);
    }

    @Override
    public Clasa adaugaClasa(ClasaRequestDTO clasaRequest){

        Profesor diriginte = profesorService.getProfesorById(clasaRequest.getIdProfesor());
        Specializare specializare = specializareService.getSpecializareById(clasaRequest.getIdSpecializare());

        Clasa clasa = new Clasa();
        clasa.setNume(clasaRequest.getNume());
        clasa.setSpecializare(specializare);
        clasa.setDiriginte(diriginte);

        return clasaRepository.save(clasa);
    }

    @Override
    public void stergeClasa(Integer idClasa){
        clasaRepository.delete(getClasaById(idClasa));
    }

    @Override
    public Set<Elev> getEleviByClasa(Integer clasaId) {
        Clasa clasa = getClasaById(clasaId);
        return clasa.getElevi();
    }

    @Override
    public Elev getElevById(Integer id){
        return elevService.getElevById(id);
    }

    @Override
    public Elev adaugaElevInClasa(ElevRequestDTO elevRequest){
        Clasa clasa = getClasaById(elevRequest.getIdClasa());
        return elevService.adaugaElev(clasa, elevRequest);
    }

    @Override
    public Elev actualizareElev(ElevRequestDTO elevRequest){
        return elevService.actualizareElev(elevRequest);
    }

    @Override
    public void stergeElev(Integer idElev){
        elevService.stergeElev(idElev);
    }

    @Override
    public List<Nota> getNoteElevMaterie(Integer idElev, Integer idMaterie){
        Elev elev = getElevById(idElev);
        return elev.getNote().stream()
                .filter(nota -> nota.getMaterie().getIdMaterie().equals(idMaterie))
                .collect(Collectors.toList());
    }

    @Override
    public Nota adaugaNotaElev(NotaRequestDTO notaRequest){
        return notaService.adaugaNota(notaRequest);
    }

    @Override
    public Nota actualizareNotaElev(NotaRequestDTO notaRequest){
        return notaService.actualizareNota(notaRequest);
    }

    @Override
    public void stergeNotaElev(Integer idNota){
        notaService.stergeNota(idNota);
    }

    @Override
    public List<Absenta> getAbsenteElevMaterie(Integer idElev, Integer idMaterie){

        Elev elev = getElevById(idElev);
        return elev.getAbsente()
                .stream()
                .filter(absenta -> absenta.getMaterie().getIdMaterie().equals(idMaterie))
                .collect(Collectors.toList());
    }

    @Override
    public Absenta adaugaAbsentaElev(AbsentaRequestDTO absentaRequest){
        return absentaService.adaugaAbsenta(absentaRequest);
    }

    @Override
    public Absenta actualizareAbsentaElev(AbsentaRequestDTO absentaRequest){
        return absentaService.actualizareAbsenta(absentaRequest);
    }

    @Override
    public void stergeAbsentaElev(Integer idAbsenta){
        absentaService.stergeAbsenta(idAbsenta);
    }

    @Override
    public List<Materie> getMateriiDinClasa(Integer idClasa){
        return clasaMaterieProfesorService.getMateriiDinClasa(idClasa);
    }

    public ClasaMaterieProfesor adaugaMaterieSiProfesorLaClasa(Integer idClasa, Integer idProfesor, Integer idMaterie) {
        Clasa clasa = getClasaById(idClasa);
        Profesor profesor = profesorService.getProfesorById(idProfesor);
        Materie materie = materieService.getMaterieById(idMaterie);
        return clasaMaterieProfesorService.adaugaMaterieSiProfesorLaClasa(clasa, profesor, materie);
    }
    public ClasaMaterieProfesor stergereMaterieSiProfesorDinClasa(Integer idClasa, Integer idProfesor, Integer idMaterie) {
        return clasaMaterieProfesorService.stergereMaterieSiProfesorDinClasa(idClasa, idProfesor, idMaterie);
    }

    // materiile predate de un profesor intr-o clasa
    public List<Materie> getMateriiPredateDeProfesorInClasa(Integer idClasa, Integer idProfesor){
        return clasaMaterieProfesorService.getMateriiPredateDeProfesorInClasa(idClasa, idProfesor);
    }

    // clasele in care preda un profesor
    public List<Clasa> getClaseProfesor(Integer idProfesor){
        return clasaMaterieProfesorService.getClaseProfesor(idProfesor);
    }

    // profesorii care predau intr-o clasa
    public List<Profesor> getProfesoriDinClasa(Integer idClasa){
        return clasaMaterieProfesorService.getProfesoriDinClasa(idClasa);
    }

}
