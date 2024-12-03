package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.entity.Rol;
import com.liceu.sistem_evidenta_elevi.repository.RolRepository;
import com.liceu.sistem_evidenta_elevi.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImplementare implements RolService {

    private RolRepository rolRepository;

    @Autowired
    public RolServiceImplementare(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public Rol getRolByNume(String nume){
        return rolRepository.findByNume(nume);
    }

    @Override
    public List<Rol> getAllRoluri(){
        return rolRepository.findAll();
    }

    @Override
    public  Rol adaugaRol(Rol rol){

        if(rol == null){
            throw new IllegalArgumentException("Rolul nu poate fi nul");
        }
        return rolRepository.save(rol);
    }

    @Override
    public Rol actualizeazaRol(Rol rol) {
        Rol rolCurent = rolRepository.findById(rol.getIdRol()).get();
        rolCurent.setNume(rol.getNume());
        return rolRepository.save(rolCurent);
    }
}
