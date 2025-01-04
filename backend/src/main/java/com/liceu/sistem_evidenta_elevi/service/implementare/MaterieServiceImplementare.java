package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.MaterieDTO;
import com.liceu.sistem_evidenta_elevi.entity.Materie;
import com.liceu.sistem_evidenta_elevi.mapper.MaterieMapper;
import com.liceu.sistem_evidenta_elevi.repository.MaterieRepository;
import com.liceu.sistem_evidenta_elevi.service.MaterieService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterieServiceImplementare implements MaterieService {

    private final MaterieRepository materieRepository;
    private final MaterieMapper materieMapper;

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

    @Transactional
    @Override
    public Materie actualizareMaterie(MaterieDTO materieDTO){
        Materie materieActuala = getMaterieById(materieDTO.getIdMaterie());
        materieMapper.updateEntityFromDTO(materieDTO, materieActuala);
        return materieRepository.save(materieActuala);
    }

    @Transactional
    @Override
    public Materie adaugaMaterie(MaterieDTO materieDTO){
        Materie materie = materieMapper.toEntity(materieDTO);
        return materieRepository.save(materie);
    }

    @Override
    public void stergeMaterie(Integer idMaterie){
        materieRepository.deleteById(idMaterie);
    }


}