package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.MaterieDTO;
import com.liceu.sistem_evidenta_elevi.entity.Materie;
import com.liceu.sistem_evidenta_elevi.repository.MaterieRepository;
import com.liceu.sistem_evidenta_elevi.service.MaterieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterieServiceImplementare implements MaterieService {

    private MaterieRepository materieRepository;

    @Autowired
    public MaterieServiceImplementare(MaterieRepository materieRepository) {
        this.materieRepository = materieRepository;
    }

    @Override
    public List<Materie> getAllMaterii(){
        return materieRepository.findAll();
    }

    @Override
    public Materie getMaterieById(Integer id){
        return materieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia nu a fost gasita"));
    }

    @Override
    public Materie actualizareMaterie(MaterieDTO materieDTO){
        Materie materieActuala = getMaterieById(materieDTO.getIdMaterie());
        materieActuala.setNume(materieDTO.getNume());
        return materieRepository.save(materieActuala);
    }

    @Override
    public Materie adaugaMaterie(MaterieDTO materieDTO){
        Materie materie = new Materie();
        materie.setNume(materieDTO.getNume());
        return materieRepository.save(materie);
}


}