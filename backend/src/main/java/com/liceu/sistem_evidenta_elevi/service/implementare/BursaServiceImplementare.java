package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.BursaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Bursa;
import com.liceu.sistem_evidenta_elevi.mapper.BursaMapper;
import com.liceu.sistem_evidenta_elevi.repository.BursaRepository;
import com.liceu.sistem_evidenta_elevi.service.BursaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BursaServiceImplementare implements BursaService {

    private final BursaRepository bursaRepository;
    private final BursaMapper bursaMapper;

    @Autowired
    public BursaServiceImplementare(BursaRepository bursaRepository, BursaMapper bursaMapper) {
        this.bursaRepository = bursaRepository;
        this.bursaMapper = bursaMapper;
    }

    @Override
    public List<Bursa> getAllBurse(){
        return bursaRepository.findAll();
    }

    @Override
    public Bursa getBursaById(Integer id){
        return bursaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bursa nu a fost gasita"));
    }

    @Transactional
    @Override
    public Bursa actualizareBursa(BursaDTO bursaDTO){
        Bursa bursa = getBursaById(bursaDTO.getIdBursa());
        bursaMapper.updateEntityFromDTO(bursaDTO, bursa);
        return bursaRepository.save(bursa);
    }

    @Transactional
    @Override
    public Bursa adaugaBursa(BursaDTO bursaDTO){
        Bursa bursa = bursaMapper.toEntity(bursaDTO);
        return bursaRepository.save(bursa);
    }

    @Override
    public void stergeBursa(Integer idBursa){
        bursaRepository.deleteById(idBursa);
    }
}
