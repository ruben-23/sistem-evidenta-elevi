package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.SpecializareDTO;
import com.liceu.sistem_evidenta_elevi.entity.Specializare;
import com.liceu.sistem_evidenta_elevi.repository.SpecializareRepository;
import com.liceu.sistem_evidenta_elevi.service.SpecializareService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializareServiceImplementare implements SpecializareService {

    private final SpecializareRepository specializareRepository;

    @Autowired
    public SpecializareServiceImplementare(SpecializareRepository specializareRepository) {
        this.specializareRepository = specializareRepository;
    }

    @Override
    public List<Specializare> getAllSpecializari(){
        return specializareRepository.findAll();
    }

    @Override
    public Specializare getSpecializareById(Integer id){
        return specializareRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specializarea nu a fost gasita"));
    }

    @Transactional
    @Override
    public Specializare actualizareSpecializare(SpecializareDTO specializareDTO){
        Specializare specializareActuala = getSpecializareById(specializareDTO.getIdSpecializare());
        specializareActuala.setNume(specializareDTO.getNume());
        return specializareRepository.save(specializareActuala);
    }

    @Transactional
    @Override
    public Specializare adaugaSpecializare(SpecializareDTO specializareDTO){
        Specializare specializare = new Specializare();
        specializare.setNume(specializareDTO.getNume());
        return specializareRepository.save(specializare);
    }

    @Override
    public void stergeSpecializare(Integer idSpecializare){
        specializareRepository.deleteById(idSpecializare);
    }

}