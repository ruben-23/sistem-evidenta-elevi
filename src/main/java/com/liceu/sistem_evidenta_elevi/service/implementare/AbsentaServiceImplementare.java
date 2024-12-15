package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.AbsentaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Absenta;
import com.liceu.sistem_evidenta_elevi.mapper.AbsentaMapper;
import com.liceu.sistem_evidenta_elevi.repository.AbsentaRepository;
import com.liceu.sistem_evidenta_elevi.service.AbsentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsentaServiceImplementare implements AbsentaService {

    private final AbsentaRepository absentaRepository;
    private AbsentaMapper absentaMapper;

    @Autowired
    public AbsentaServiceImplementare(AbsentaRepository absentaRepository, AbsentaMapper absentaMapper) {
        this.absentaRepository = absentaRepository;
        this.absentaMapper = absentaMapper;
    }

    @Override
    public List<Absenta> getAllAbsente(){
        return absentaRepository.findAll();
    }

    @Override
    public Absenta getAbsentaById(Integer id){
        return absentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absenta nu a fost gasita"));
    }

    @Override
    public Absenta actualizareAbsenta(AbsentaDTO absentaDTO){
        Absenta absentaActuala = getAbsentaById(absentaDTO.getIdAbsenta());
        absentaMapper.updateEntityFromDTO(absentaDTO, absentaActuala);
        return absentaRepository.save(absentaActuala);
    }

    @Override
    public Absenta adaugaAbsenta(AbsentaDTO absentaDTO){
        Absenta absenta = absentaMapper.toEntity(absentaDTO);
        return absentaRepository.save(absenta);
    }

    @Override
    public void stergeAbsenta(Integer idAbsenta) {
        absentaRepository.deleteById(idAbsenta);
    }

}
