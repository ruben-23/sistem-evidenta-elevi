package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.MaterieDTO;
import com.liceu.sistem_evidenta_elevi.entity.Materie;
import com.liceu.sistem_evidenta_elevi.mapper.MaterieMapper;
import com.liceu.sistem_evidenta_elevi.repository.MaterieRepository;
import com.liceu.sistem_evidenta_elevi.service.MaterieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterieServiceImplementare implements MaterieService {

    private MaterieRepository materieRepository;
    private MaterieMapper materieMapper;

    @Autowired
    public MaterieServiceImplementare(MaterieRepository materieRepository, MaterieMapper materieMapper) {
        this.materieRepository = materieRepository;
        this.materieMapper = materieMapper;
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
        materieMapper.updateEntityFromDTO(materieDTO, materieActuala);
        return materieRepository.save(materieActuala);
    }

    @Override
    public Materie adaugaMaterie(MaterieDTO materieDTO){
        Materie materie = materieMapper.toEntity(materieDTO);
        return materieRepository.save(materie);
}


}