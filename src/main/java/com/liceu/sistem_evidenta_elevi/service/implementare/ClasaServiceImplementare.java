package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.AbsentaDTO;
import com.liceu.sistem_evidenta_elevi.dto.ClasaDTO;
import com.liceu.sistem_evidenta_elevi.dto.ElevDTO;
import com.liceu.sistem_evidenta_elevi.dto.NotaDTO;
import com.liceu.sistem_evidenta_elevi.entity.*;
import com.liceu.sistem_evidenta_elevi.mapper.ClasaMapper;
import com.liceu.sistem_evidenta_elevi.repository.ClasaRepository;
import com.liceu.sistem_evidenta_elevi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private ClasaMapper clasaMapper;

    @Autowired
    public ClasaServiceImplementare(ClasaRepository clasaRepository, ElevService elevService,
                                     ProfesorService profesorService, SpecializareService specializareService,
                                    NotaService notaService, AbsentaService absentaService, MaterieService materieService,
                                    ClasaMaterieProfesorService clasaMaterieProfesorService, ClasaMapper clasaMapper) {
        this.clasaRepository = clasaRepository;
        this.elevService = elevService;
        this.profesorService = profesorService;
        this.specializareService = specializareService;
        this.materieService = materieService;
        this.notaService = notaService;
        this.absentaService = absentaService;
        this.clasaMaterieProfesorService = clasaMaterieProfesorService;
        this.clasaMapper = clasaMapper;
    }

    @Override
    public List<ClasaDTO> getAllClase(){
        List<Clasa> clase = clasaRepository.findAll();
        return clasaMapper.toDTOList(clase);
    }

    @Override
    public ClasaDTO getClasaById(Integer id){
        Clasa clasa = clasaRepository.findById(id).
                orElseThrow(()->new RuntimeException("Clasa nu a fost gasita"));

        return clasaMapper.toDTO(clasa);
    }

    @Override
    public ClasaDTO actualizareClasa(ClasaDTO clasaDTO){
        Clasa clasaActuala = clasaMapper.toEntity(getClasaById(clasaDTO.getIdClasa()));
        clasaActuala.setNume(clasaDTO.getNume());
        return clasaMapper.toDTO(clasaRepository.save(clasaActuala));
    }

    @Override
    public ClasaDTO adaugaClasa(ClasaDTO clasaDTO){
        Clasa clasa = clasaMapper.toEntity(clasaDTO);
        return clasaMapper.toDTO(clasaRepository.save(clasa));
    }

    @Override
    public void stergeClasa(Integer idClasa){
        clasaRepository.deleteById(idClasa);
    }

    @Override
    public Set<Elev> getEleviByClasa(Integer clasaId) {
        Clasa clasa = clasaMapper.toEntity(getClasaById(clasaId));
        return clasa.getElevi();
    }

    @Override
    public Elev getElevById(Integer id){
        return elevService.getElevById(id);
    }

    @Override
    public Elev adaugaElevInClasa(ElevDTO elevDTO){
        Clasa clasa = clasaMapper.toEntity(getClasaById(elevDTO.getIdClasa()));
        return elevService.adaugaElev(clasa, elevDTO);
    }

    @Override
    public Elev actualizareElev(ElevDTO elevDTO){
        return elevService.actualizareElev(elevDTO);
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
    public Nota adaugaNotaElev(NotaDTO notaDTO){
        return notaService.adaugaNota(notaDTO);
    }

    @Override
    public Nota actualizareNotaElev(NotaDTO notaDTO){
        return notaService.actualizareNota(notaDTO);
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
    public Absenta adaugaAbsentaElev(AbsentaDTO absentaDTO){
        return absentaService.adaugaAbsenta(absentaDTO);
    }

    @Override
    public Absenta actualizareAbsentaElev(AbsentaDTO absentaDTO){
        return absentaService.actualizareAbsenta(absentaDTO);
    }

    @Override
    public void stergeAbsentaElev(Integer idAbsenta){
        absentaService.stergeAbsenta(idAbsenta);
    }

    @Override
    public List<Materie> getMateriiDinClasa(Integer idClasa){
        return clasaMaterieProfesorService.getMateriiDinClasa(idClasa);
    }

    @Override
    public ClasaMaterieProfesor adaugaMaterieSiProfesorLaClasa(Integer idClasa, Integer idProfesor, Integer idMaterie) {
        Clasa clasa = clasaMapper.toEntity(getClasaById(idClasa));
        Profesor profesor = profesorService.getProfesorById(idProfesor);
        Materie materie = materieService.getMaterieById(idMaterie);
        return clasaMaterieProfesorService.adaugaMaterieSiProfesorLaClasa(clasa, profesor, materie);
    }
    public ClasaMaterieProfesor stergereMaterieSiProfesorDinClasa(Integer idClasa, Integer idProfesor, Integer idMaterie) {
        return clasaMaterieProfesorService.stergereMaterieSiProfesorDinClasa(idClasa, idProfesor, idMaterie);
    }

    @Override
    // materiile predate de un profesor intr-o clasa
    public List<Materie> getMateriiPredateDeProfesorInClasa(Integer idClasa, Integer idProfesor){
        return clasaMaterieProfesorService.getMateriiPredateDeProfesorInClasa(idClasa, idProfesor);
    }

    @Override
    // clasele in care preda un profesor
    public List<Clasa> getClaseProfesor(Integer idProfesor){
        return clasaMaterieProfesorService.getClaseProfesor(idProfesor);
    }

    @Override
    // profesorii care predau intr-o clasa
    public List<Profesor> getProfesoriDinClasa(Integer idClasa){
        return clasaMaterieProfesorService.getProfesoriDinClasa(idClasa);
    }

}
