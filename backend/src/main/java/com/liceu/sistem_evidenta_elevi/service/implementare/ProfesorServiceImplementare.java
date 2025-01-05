package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.mapper.ProfesorMapper;
import com.liceu.sistem_evidenta_elevi.repository.ProfesorRepository;
import com.liceu.sistem_evidenta_elevi.service.ProfesorService;
import com.liceu.sistem_evidenta_elevi.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementare a serviciului Profesor.
 * Contine metode pentru gestionarea operatiunilor legate de profesori.
 */
@Service
public class ProfesorServiceImplementare implements ProfesorService {

    private ProfesorRepository profesorRepository;
    private ProfesorMapper profesorMapper;
    private UserService userService;

    /**
     * Constructor pentru injectarea dependintelor.
     * Se utilizeaza @Lazy pentru a evita ciclul de dependente (ProfesorService - UserService).
     *
     * @param profesorRepository Repositorul pentru gestionarea operatiunilor cu profesori.
     * @param profesorMapper    Mapper-ul pentru conversia intre Profesor si ProfesorDTO.
     * @param userService       Serviciul pentru gestionarea operatiunilor cu utilizatori.
     */
    @Autowired
    public ProfesorServiceImplementare(ProfesorRepository profesorRepository, ProfesorMapper profesorMapper,
                                       @Lazy UserService userService) {
        this.profesorRepository = profesorRepository;
        this.profesorMapper = profesorMapper;
        this.userService = userService;
    }

    public ProfesorServiceImplementare() {}

    /**
     * Obtine toti profesorii.
     *
     * @return Lista tuturor profesorilor.
     */
    @Override
    public List<Profesor> getAllProfesori(){
        return profesorRepository.findAll();
    }

    /**
     * Obtine un profesor dupa ID.
     *
     * @param id ID-ul profesorului.
     * @return Profesorul corespunzator ID-ului.
     * @throws RuntimeException Daca profesorul nu este gasit.
     */
    @Override
    public Profesor getProfesorById(Integer id){
        return  profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesorul nu a fost gasit"));
    }

    /**
     * Actualizeaza un profesor pe baza unui ProfesorDTO.
     * De asemenea, se actualizeaza utilizatorul asociat.
     *
     * @param profesorDTO DTO-ul care contine noile date pentru profesor.
     * @return Profesorul actualizat.
     */
    @Transactional
    @Override
    public Profesor actualizareProfesor(ProfesorDTO profesorDTO){
        Profesor profesorActual = getProfesorById(profesorDTO.getIdProfesor());
        User user = userService.getUserById(profesorDTO.getIdUser());
        profesorMapper.updateEntityFromDTO(profesorDTO, profesorActual, user);
        return profesorRepository.save(profesorActual);
    }

    /**
     * Adauga un profesor in sistem pe baza unui ProfesorDTO si a unui utilizator.
     *
     * @param profesorDTO DTO-ul care contine datele pentru profesor.
     * @param user        Utilizatorul asociat profesorului.
     * @return Profesorul adaugat.
     */
    @Transactional
    @Override
    public Profesor adaugaProfesor(ProfesorDTO profesorDTO, User user){
        Profesor profesor = profesorMapper.toEntity(profesorDTO, user);
        return profesorRepository.save(profesor);
    }

    /**
     * Sterge un profesor din sistem pe baza ID-ului.
     *
     * @param idProfesor ID-ul profesorului de sters.
     */
    @Override
    public void stergeProfesor(Integer idProfesor){
        profesorRepository.deleteById(idProfesor);
    }
}
