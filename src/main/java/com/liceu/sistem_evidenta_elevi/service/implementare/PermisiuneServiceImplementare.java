package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.entity.Permisiune;
import com.liceu.sistem_evidenta_elevi.repository.PermisiuneRepository;
import com.liceu.sistem_evidenta_elevi.service.PermisiuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermisiuneServiceImplementare implements PermisiuneService {

    private PermisiuneRepository permisiuneRepository;

    @Autowired
    public PermisiuneServiceImplementare(PermisiuneRepository permisiuneRepository) {
        this.permisiuneRepository = permisiuneRepository;
    }

    @Override
    public Permisiune getPermisiuneById(Integer id) {
        Optional<Permisiune> permisiune = permisiuneRepository.findById(id);
        return permisiune.get();
    }

    @Override
    public List<Permisiune> getAllPermisiuni(){
        return permisiuneRepository.findAll();
    }

    @Override
    public Permisiune adaugaPermisiune(Permisiune permisiune) {
        if(permisiune == null){
            throw new IllegalArgumentException("Rolul nu poate fi nul");
        }
        return permisiuneRepository.save(permisiune);
    }

    public Permisiune actualizeazaPermisiune(Permisiune permisiune) {

        Permisiune permisiuneCurenta = permisiuneRepository.findById(permisiune.getIdPermisiune()).get();
        permisiuneCurenta.setNume(permisiune.getNume());
        return permisiuneRepository.save(permisiuneCurenta);

    }

}
