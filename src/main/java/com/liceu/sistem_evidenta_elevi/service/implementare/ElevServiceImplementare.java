package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.ElevDTO;
import com.liceu.sistem_evidenta_elevi.entity.Absenta;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.entity.Nota;
import com.liceu.sistem_evidenta_elevi.mapper.ElevMapper;
import com.liceu.sistem_evidenta_elevi.repository.ElevRepository;
import com.liceu.sistem_evidenta_elevi.service.ElevService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElevServiceImplementare implements ElevService {

    private final ElevRepository elevRepository;
    private final ElevMapper elevMapper;

    @Autowired
    public ElevServiceImplementare(ElevRepository elevRepository, ElevMapper elevMapper) {
        this.elevRepository = elevRepository;
        this.elevMapper = elevMapper;
    }

    @Override
    public List<Elev> getAllElevi(){
        return elevRepository.findAll();
    }

    @Override
    public Elev getElevById(Integer id){
        return elevRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Elevul nu a fost gasit"));
    }

    @Transactional
    @Override
    public Elev actualizareElev(ElevDTO elevDTO){
        Elev elevActual = getElevById(elevDTO.getIdElev());
        elevMapper.updateEntityFromDTO(elevDTO, elevActual);
        return elevRepository.save(elevActual);
    }

    @Transactional
    @Override
    public Elev adaugaElev(ElevDTO elevDTO){
        Elev elev = elevMapper.toEntity(elevDTO);
        return elevRepository.save(elev);
    }

    @Override
    public void stergeElev(Integer idElev){
       elevRepository.deleteById(idElev);
    }

    @Override
    public List<Nota> getNoteElevMaterie(Integer idElev, Integer idMaterie){
        Elev elev = getElevById(idElev);
        return elev.getNote().stream()
                .filter(nota -> nota.getMaterie().getIdMaterie().equals(idMaterie))
                .collect(Collectors.toList());
    }

    @Override
    public List<Absenta> getAbsenteElevMaterie(Integer idElev, Integer idMaterie){
        Elev elev = getElevById(idElev);
        return elev.getAbsente()
                .stream()
                .filter(absenta -> absenta.getMaterie().getIdMaterie().equals(idMaterie))
                .collect(Collectors.toList());
    }


}
