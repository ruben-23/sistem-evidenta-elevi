package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.AbsentaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Absenta;
import com.liceu.sistem_evidenta_elevi.mapper.AbsentaMapper;
import com.liceu.sistem_evidenta_elevi.repository.AbsentaRepository;
import com.liceu.sistem_evidenta_elevi.service.AbsentaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementarea serviciului {@link AbsentaService}, responsabila pentru gestionarea logicii
 * referitoare la entitatea {@link Absenta}.
 */
@Service
public class AbsentaServiceImplementare implements AbsentaService {

    private final AbsentaRepository absentaRepository;
    private final AbsentaMapper absentaMapper;

    /**
     * Constructor pentru injectarea dependentelor.
     *
     * @param absentaRepository instanta {@link AbsentaRepository} pentru accesul la baza de date.
     * @param absentaMapper     instanta {@link AbsentaMapper} pentru conversia intre entitati si DTO-uri.
     */
    @Autowired
    public AbsentaServiceImplementare(AbsentaRepository absentaRepository, AbsentaMapper absentaMapper) {
        this.absentaRepository = absentaRepository;
        this.absentaMapper = absentaMapper;
    }

    /**
     * Obtine toate absentele din baza de date.
     *
     * @return o lista cu toate entitatile {@link Absenta}.
     */
    @Override
    public List<Absenta> getAllAbsente(){
        return absentaRepository.findAll();
    }

    /**
     * Gaseste o absenta dupa ID.
     *
     * @param id ID-ul absentei cautate.
     * @return entitatea {@link Absenta} corespunzatoare.
     * @throws RuntimeException daca absenta nu este gasita.
     */
    @Override
    public Absenta getAbsentaById(Integer id){
        return absentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absenta nu a fost gasita"));
    }

    /**
     * Actualizeaza o absenta existenta pe baza unui DTO.
     *
     * @param absentaDTO obiectul {@link AbsentaDTO} cu noile date.
     * @return entitatea actualizata {@link Absenta}.
     */
    @Transactional
    @Override
    public Absenta actualizareAbsenta(AbsentaDTO absentaDTO){
        Absenta absentaActuala = getAbsentaById(absentaDTO.getIdAbsenta());
        absentaMapper.updateEntityFromDTO(absentaDTO, absentaActuala);
        return absentaRepository.save(absentaActuala);
    }

    /**
     * Adauga o noua absenta in baza de date.
     *
     * @param absentaDTO obiectul {@link AbsentaDTO} care contine detaliile noii absente.
     * @return entitatea salvata {@link Absenta}.
     */
    @Transactional
    @Override
    public Absenta adaugaAbsenta(AbsentaDTO absentaDTO){
        Absenta absenta = absentaMapper.toEntity(absentaDTO);
        return absentaRepository.save(absenta);
    }

    /**
     * Sterge o absenta din baza de date pe baza ID-ului.
     *
     * @param idAbsenta ID-ul absentei care trebuie stearsa.
     */
    @Override
    public void stergeAbsenta(Integer idAbsenta) {
        absentaRepository.deleteById(idAbsenta);
    }

}
