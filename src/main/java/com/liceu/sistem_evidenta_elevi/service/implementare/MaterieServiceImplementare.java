package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.MaterieRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Materie;
import com.liceu.sistem_evidenta_elevi.repository.MaterieRepository;
import com.liceu.sistem_evidenta_elevi.service.MaterieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Materie actualizareMaterie(MaterieRequestDTO materieRequest){
        Materie materieActuala = getMaterieById(materieRequest.getIdMaterie());
        materieActuala.setNume(materieRequest.getNume());
        return materieRepository.save(materieActuala);
    }

    @Override
    public Materie adaugaMaterie(MaterieRequestDTO materieRequest){
        Materie materie = new Materie();
        materie.setNume(materieRequest.getNume());
        return materieRepository.save(materie);
}


}