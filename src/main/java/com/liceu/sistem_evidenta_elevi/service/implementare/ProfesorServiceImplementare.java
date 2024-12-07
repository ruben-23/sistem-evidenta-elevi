package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.repository.ProfesorRepository;
import com.liceu.sistem_evidenta_elevi.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Profesor> profesor = profesorRepository.findById(id);
        return profesor.get();
    }

    @Override
    public Profesor actualizareProfesor(Profesor profesor){
        Profesor profesorActual = profesorRepository.findById(profesor.getIdProfesor()).get();
        profesorActual.setNume(profesor.getNume());
        profesorActual.setPrenume(profesor.getPrenume());
        profesorActual.setAdresa(profesor.getAdresa());
        profesorActual.setCNP(profesor.getCNP());
        profesorActual.setNumarTelefon(profesor.getNumarTelefon());

        return profesorRepository.save(profesorActual);
    }

    @Override
    public Profesor adaugaProfesor(ProfesorRequestDTO profesorRequest){
        Profesor profesor = new Profesor();
        profesor.setNume(profesorRequest.getNume());
        profesor.setPrenume(profesorRequest.getPrenume());
        profesor.setAdresa(profesorRequest.getAdresa());
        profesor.setCNP(profesorRequest.getCNP());
        profesor.setNumarTelefon(profesorRequest.getNumarTelefon());

        return profesorRepository.save(profesor);
    }


}
