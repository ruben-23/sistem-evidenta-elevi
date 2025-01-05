package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.SpecializareDTO;
import com.liceu.sistem_evidenta_elevi.entity.Specializare;
import com.liceu.sistem_evidenta_elevi.repository.SpecializareRepository;
import com.liceu.sistem_evidenta_elevi.service.SpecializareService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementare a serviciului Specializare.
 * Contine metode pentru gestionarea operatiunilor legate de specializari.
 */
@Service
public class SpecializareServiceImplementare implements SpecializareService {

    private final SpecializareRepository specializareRepository;

    /**
     * Constructor pentru injectarea dependintei specializareRepository.
     *
     * @param specializareRepository Repositorul pentru gestionarea operatiunilor cu specializari.
     */
    @Autowired
    public SpecializareServiceImplementare(SpecializareRepository specializareRepository) {
        this.specializareRepository = specializareRepository;
    }

    /**
     * Obtine toate specializarile.
     *
     * @return Lista tuturor specializarilor.
     */
    @Override
    public List<Specializare> getAllSpecializari(){
        return specializareRepository.findAll();
    }

    /**
     * Obtine o specializare dupa ID.
     *
     * @param id ID-ul specializarii.
     * @return Specializarea corespunzatoare ID-ului.
     * @throws RuntimeException Daca specializarea nu este gasita.
     */
    @Override
    public Specializare getSpecializareById(Integer id){
        return specializareRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specializarea nu a fost gasita"));
    }

    /**
     * Actualizeaza o specializare pe baza unui SpecializareDTO.
     *
     * @param specializareDTO DTO-ul care contine noile date pentru specializare.
     * @return Specializarea actualizata.
     */
    @Transactional
    @Override
    public Specializare actualizareSpecializare(SpecializareDTO specializareDTO){
        Specializare specializareActuala = getSpecializareById(specializareDTO.getIdSpecializare());
        specializareActuala.setNume(specializareDTO.getNume());
        return specializareRepository.save(specializareActuala);
    }

    /**
     * Adauga o specializare in sistem pe baza unui SpecializareDTO.
     *
     * @param specializareDTO DTO-ul care contine datele pentru specializare.
     * @return Specializarea adaugata.
     */
    @Transactional
    @Override
    public Specializare adaugaSpecializare(SpecializareDTO specializareDTO){
        Specializare specializare = new Specializare();
        specializare.setNume(specializareDTO.getNume());
        return specializareRepository.save(specializare);
    }

    /**
     * Sterge o specializare din sistem pe baza ID-ului.
     *
     * @param idSpecializare ID-ul specializarii de sters.
     */
    @Override
    public void stergeSpecializare(Integer idSpecializare){
        specializareRepository.deleteById(idSpecializare);
    }

}