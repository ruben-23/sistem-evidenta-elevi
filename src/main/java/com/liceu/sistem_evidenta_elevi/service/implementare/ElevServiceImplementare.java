package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.ElevDTO;
import com.liceu.sistem_evidenta_elevi.entity.Clasa;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.repository.ElevRepository;
import com.liceu.sistem_evidenta_elevi.service.ElevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Elev getElevById(Integer id){
        return elevRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Elevul nu a fost gasit"));
    }

    @Override
    public Elev actualizareElev(ElevDTO elevDTO){
        Elev elevActual = getElevById(elevDTO.getIdElev());
        elevActual.setNume(elevDTO.getNume());
        elevActual.setPrenume(elevDTO.getPrenume());
        elevActual.setCNP(elevDTO.getCNP());
        elevActual.setSex(elevDTO.getSex());
        elevActual.setNumarTelefon(elevDTO.getNumarTelefon());
        elevActual.setAdresa(elevDTO.getAdresa());
        elevActual.setDataNasterii(elevDTO.getDataNasterii());

        return elevRepository.save(elevActual);
    }

    @Override
    public Elev adaugaElev(Clasa clasa, ElevDTO elevDTO){

        // creare elev
        Elev elev = new Elev();
        elev.setNume(elevDTO.getNume());
        elev.setPrenume(elevDTO.getPrenume());
        elev.setCNP(elevDTO.getCNP());
        elev.setNumarTelefon(elevDTO.getNumarTelefon());
        elev.setAdresa(elevDTO.getAdresa());
        elev.setDataNasterii(elevDTO.getDataNasterii());

        // creare legatura cu clasa din care face parte
        elev.setClasa(clasa);

        return elevRepository.save(elev);
    }

    @Override
    public void stergeElev(Integer idElev){
       elevRepository.deleteById(idElev);
    }
}
