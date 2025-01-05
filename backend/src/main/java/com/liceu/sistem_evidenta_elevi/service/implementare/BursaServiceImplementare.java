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

/**
 * Implementare a serviciului Bursa.
 * Contine metode pentru gestionarea burselor: adaugare, actualizare, stergere si obtinere.
 */
@Service
public class BursaServiceImplementare implements BursaService {

    private final BursaRepository bursaRepository;
    private final BursaMapper bursaMapper;

    /**
     * Constructor pentru injectarea dependintelor.
     *
     * @param bursaRepository Repositorul pentru gestionarea operatiunilor cu Bursa.
     * @param bursaMapper     Mapper-ul pentru conversia dintre entitate si DTO.
     */
    @Autowired
    public BursaServiceImplementare(BursaRepository bursaRepository, BursaMapper bursaMapper) {
        this.bursaRepository = bursaRepository;
        this.bursaMapper = bursaMapper;
    }

    /**
     * Obtine toate bursele.
     *
     * @return Lista de burse.
     */
    @Override
    public List<Bursa> getAllBurse(){
        return bursaRepository.findAll();
    }

    /**
     * Obtine o bursa pe baza ID-ului.
     *
     * @param id ID-ul bursei.
     * @return Bursa corespunzatoare ID-ului.
     * @throws RuntimeException Daca bursa nu este gasita.
     */
    @Override
    public Bursa getBursaById(Integer id){
        return bursaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bursa nu a fost gasita"));
    }

    /**
     * Actualizeaza o bursa pe baza unui DTO.
     *
     * @param bursaDTO DTO-ul care contine informatiile actualizate ale bursei.
     * @return Bursa actualizata.
     */
    @Transactional
    @Override
    public Bursa actualizareBursa(BursaDTO bursaDTO){
        Bursa bursa = getBursaById(bursaDTO.getIdBursa());
        bursaMapper.updateEntityFromDTO(bursaDTO, bursa);
        return bursaRepository.save(bursa);
    }

    /**
     * Adauga o noua bursa pe baza unui DTO.
     *
     * @param bursaDTO DTO-ul care contine informatiile bursei noi.
     * @return Bursa adaugata.
     */
    @Transactional
    @Override
    public Bursa adaugaBursa(BursaDTO bursaDTO){
        Bursa bursa = bursaMapper.toEntity(bursaDTO);
        return bursaRepository.save(bursa);
    }

    /**
     * Sterge o bursa pe baza ID-ului.
     *
     * @param idBursa ID-ul bursei de sters.
     */
    @Override
    public void stergeBursa(Integer idBursa){
        bursaRepository.deleteById(idBursa);
    }
}