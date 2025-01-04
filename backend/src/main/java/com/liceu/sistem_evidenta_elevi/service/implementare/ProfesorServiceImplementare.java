package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.mapper.ProfesorMapper;
import com.liceu.sistem_evidenta_elevi.repository.ProfesorRepository;
import com.liceu.sistem_evidenta_elevi.service.ProfesorService;
import com.liceu.sistem_evidenta_elevi.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImplementare implements ProfesorService {

    private ProfesorRepository profesorRepository;
    private ProfesorMapper profesorMapper;
    private UserService userService;

    // folosire lazy pentru userService pentru a evita ciclu de dependente (profesorService-userService)
    @Autowired
    public ProfesorServiceImplementare(ProfesorRepository profesorRepository, ProfesorMapper profesorMapper,
                                       @Lazy UserService userService) {
        this.profesorRepository = profesorRepository;
        this.profesorMapper = profesorMapper;
        this.userService = userService;
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

    @Transactional
    @Override
    public Profesor actualizareProfesor(ProfesorDTO profesorDTO){
        // returnare profesor si user cu id ul din dto
        Profesor profesorActual = getProfesorById(profesorDTO.getIdProfesor());
        User user = userService.getUserById(profesorDTO.getIdUser());
        profesorMapper.updateEntityFromDTO(profesorDTO, profesorActual, user);
        return profesorRepository.save(profesorActual);
    }

    @Transactional
    @Override
    public Profesor adaugaProfesor(ProfesorDTO profesorDTO, User user){
        Profesor profesor = profesorMapper.toEntity(profesorDTO, user);
        return profesorRepository.save(profesor);
    }

    @Override
    public void stergeProfesor(Integer idProfesor){
        profesorRepository.deleteById(idProfesor);
    }

}
