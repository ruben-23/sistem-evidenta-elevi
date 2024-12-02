package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.repository.ElevRepository;
import com.liceu.sistem_evidenta_elevi.service.ElevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElevServiceImplementare implements ElevService {

    private ElevRepository elevRepository;

    public ElevServiceImplementare() {}

    @Autowired
    public ElevServiceImplementare(ElevRepository elevRepository) {
        this.elevRepository = elevRepository;
    }

    @Override
    public List<Elev> getAllElevi(){
        return elevRepository.findAll();
    }

    @Override
    public Elev getElevById(int id){
        Optional<Elev> elev = elevRepository.findById(id);
        return elev.get();
    }

    @Override
    public Elev actualizareElev(Elev elev){
        Elev elevActual = elevRepository.findById(elev.getIdElev()).get();
        elevActual.setNumeElev(elev.getNumeElev());
        elevActual.setPrenumeElev(elev.getPrenumeElev());
        elevActual.setCNP(elev.getCNP());
        elevActual.setSexElev(elev.getSexElev());
        elevActual.setNumarTelefonElev(elev.getNumarTelefonElev());
        elevActual.setAdresaElev(elev.getAdresaElev());
        elevActual.setDataNasteriiElev(elev.getDataNasteriiElev());
        return elevRepository.save(elevActual);

    }

    @Override
    public Elev adaugaElev(Elev elev){
        return elevRepository.save(elev);
    }

}
