package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.SpecializareRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Specializare;
import com.liceu.sistem_evidenta_elevi.repository.SpecializareRepository;
import com.liceu.sistem_evidenta_elevi.service.SpecializareService;
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

    @Override
    public Specializare actualizareSpecializare(SpecializareRequestDTO specializareRequest){
        Specializare specializareActuala = getSpecializareById(specializareRequest.getIdSpecializare());
        specializareActuala.setNume(specializareRequest.getNume());
        return specializareRepository.save(specializareActuala);
    }

    @Override
    public Specializare adaugaSpecializare(SpecializareRequestDTO specializareRequest){
        Specializare specializare = new Specializare();
        specializare.setNume(specializareRequest.getNume());
        return specializareRepository.save(specializare);
}

}