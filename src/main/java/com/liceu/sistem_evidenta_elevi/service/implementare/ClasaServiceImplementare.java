package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.ClasaDTO;

import com.liceu.sistem_evidenta_elevi.entity.*;
import com.liceu.sistem_evidenta_elevi.mapper.ClasaMapper;
import com.liceu.sistem_evidenta_elevi.repository.ClasaRepository;
import com.liceu.sistem_evidenta_elevi.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasaServiceImplementare implements ClasaService {

    private final ClasaRepository clasaRepository;
    private final ClasaMapper clasaMapper;

    @Autowired
    public ClasaServiceImplementare(ClasaRepository clasaRepository, ClasaMapper clasaMapper) {
        this.clasaRepository = clasaRepository;
        this.clasaMapper = clasaMapper;

    }

    @Override
    public List<Clasa> getAllClase(){
        return clasaRepository.findAll();
    }

    @Override
    public Clasa getClasaById(Integer id){
        return  clasaRepository.findById(id).
                orElseThrow(()->new RuntimeException("Clasa nu a fost gasita"));
    }

    @Transactional
    @Override
    public Clasa actualizareClasa(ClasaDTO clasaDTO){
        Clasa clasaActuala = getClasaById(clasaDTO.getIdClasa());
        clasaMapper.updateEntityFromDTO(clasaDTO, clasaActuala);
        return clasaRepository.save(clasaActuala);
    }

    @Transactional
    @Override
    public Clasa adaugaClasa(ClasaDTO clasaDTO){
        Clasa clasa = clasaMapper.toEntity(clasaDTO);
        return clasaRepository.save(clasa);
    }

    @Override
    public void stergeClasa(Integer idClasa){
        clasaRepository.delete(getClasaById(idClasa));
    }

    @Override
    public List<Elev> getEleviByClasa(Integer clasaId) {
        Clasa clasa = getClasaById(clasaId);
        return clasa.getElevi();
    }

}
