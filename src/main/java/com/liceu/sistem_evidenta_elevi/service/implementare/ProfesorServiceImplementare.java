package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.mapper.ProfesorMapper;
import com.liceu.sistem_evidenta_elevi.repository.ProfesorRepository;
import com.liceu.sistem_evidenta_elevi.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImplementare implements ProfesorService {

    private ProfesorRepository profesorRepository;
    private ProfesorMapper profesorMapper;

    @Autowired
    public ProfesorServiceImplementare(ProfesorRepository profesorRepository, ProfesorMapper profesorMapper) {
        this.profesorRepository = profesorRepository;
        this.profesorMapper = profesorMapper;
    }
    public ProfesorServiceImplementare() {}

    @Override
    public List<Profesor> getAllProfesori(){
        return profesorRepository.findAll();
    }

    @Override
    public Profesor getProfesorById(Integer id){
        return  profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesorul nu a fost gasit"));
    }

    @Override
    public Profesor actualizareProfesor(ProfesorDTO profesorDTO){
        // returnare profesor cu id ul din dto
        Profesor profesorActual = getProfesorById(profesorDTO.getIdProfesor());
        profesorMapper.updateEntityFromDTO(profesorDTO, profesorActual);
        return profesorRepository.save(profesorActual);
    }

    @Override
    public Profesor adaugaProfesor(ProfesorDTO profesorDTO){
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        return profesorRepository.save(profesor);
    }

    @Override
    public void stergeProfesor(Integer idProfesor){
        profesorRepository.deleteById(idProfesor);
    }

}
