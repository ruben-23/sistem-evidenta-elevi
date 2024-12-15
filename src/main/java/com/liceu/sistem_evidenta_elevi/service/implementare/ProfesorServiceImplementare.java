package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.repository.ProfesorRepository;
import com.liceu.sistem_evidenta_elevi.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImplementare implements ProfesorService {

    private ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorServiceImplementare(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
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
        // returnare profesor cu id ul dorit
        Profesor profesorActual = getProfesorById(profesorDTO.getIdProfesor());

        profesorActual.setNume(profesorDTO.getNume());
        profesorActual.setPrenume(profesorDTO.getPrenume());
        profesorActual.setAdresa(profesorDTO.getAdresa());
        profesorActual.setCNP(profesorDTO.getCNP());
        profesorActual.setNumarTelefon(profesorDTO.getNumarTelefon());

        return profesorRepository.save(profesorActual);
    }

    @Override
    public Profesor adaugaProfesor(ProfesorDTO profesorDTO){
        Profesor profesor = new Profesor();
        profesor.setNume(profesorDTO.getNume());
        profesor.setPrenume(profesorDTO.getPrenume());
        profesor.setAdresa(profesorDTO.getAdresa());
        profesor.setCNP(profesorDTO.getCNP());
        profesor.setNumarTelefon(profesorDTO.getNumarTelefon());

        return profesorRepository.save(profesor);
    }


}
