package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.SecretaraDTO;
import com.liceu.sistem_evidenta_elevi.entity.Secretara;
import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.mapper.SecretaraMapper;
import com.liceu.sistem_evidenta_elevi.repository.SecretaraRepository;
import com.liceu.sistem_evidenta_elevi.service.SecretaraService;
import com.liceu.sistem_evidenta_elevi.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementare a serviciului Secretara.
 * Contine metode pentru gestionarea operatiunilor legate de secretare.
 */
@Service
public class SecretaraServiceImplementare implements SecretaraService {

    private final SecretaraRepository secretaraRepository;
    private final UserService userService;
    private final SecretaraMapper secretaraMapper;

    /**
     * Constructor pentru injectarea dependintelor.
     * Se utilizeaza @Lazy pentru a evita ciclul de dependente (SecretaraService - UserService).
     *
     * @param secretaraRepository Repositorul pentru gestionarea operatiunilor cu secretare.
     * @param secretaraMapper    Mapper-ul pentru conversia intre Secretara si SecretaraDTO.
     * @param userService        Serviciul pentru gestionarea operatiunilor cu utilizatori.
     */
    @Autowired
    public SecretaraServiceImplementare(SecretaraRepository secretaraRepository, SecretaraMapper secretaraMapper,
                                        @Lazy UserService userService) {
        this.secretaraRepository = secretaraRepository;
        this.userService = userService;
        this.secretaraMapper = secretaraMapper;
    }

    /**
     * Obtine toate secretarele.
     *
     * @return Lista tuturor secretarelor.
     */
    @Override
    public List<Secretara> getAllSecretare(){
        return secretaraRepository.findAll();
    }

    /**
     * Obtine o secretara dupa ID.
     *
     * @param idSecretara ID-ul secretarei.
     * @return Secretara corespunzatoare ID-ului.
     * @throws RuntimeException Daca secretara nu este gasita.
     */
    @Override
    public Secretara getSecretaraById(Integer idSecretara){
        return secretaraRepository.findById(idSecretara)
                .orElseThrow(() -> new RuntimeException("Secretara nu a fost gasita"));
    }

    /**
     * Actualizeaza o secretara pe baza unui SecretaraDTO.
     * De asemenea, se actualizeaza utilizatorul asociat.
     *
     * @param secretaraDTO DTO-ul care contine noile date pentru secretara.
     * @return Secretara actualizata.
     */
    @Transactional
    @Override
    public Secretara actualizareSecretara(SecretaraDTO secretaraDTO){
        Secretara secretara = getSecretaraById(secretaraDTO.getIdSecretara());
        User user = userService.getUserById(secretaraDTO.getIdUser());
        secretaraMapper.updateEntityFromDTO(secretaraDTO, secretara, user);
        return secretaraRepository.save(secretara);
    }

    /**
     * Adauga o secretara in sistem pe baza unui SecretaraDTO si a unui utilizator.
     *
     * @param secretaraDTO DTO-ul care contine datele pentru secretara.
     * @param user         Utilizatorul asociat secretarei.
     * @return Secretara adaugata.
     */
    @Transactional
    @Override
    public Secretara adaugaSecretara(SecretaraDTO secretaraDTO, User user){
        Secretara secretara = secretaraMapper.toEntity(secretaraDTO, user);
        return secretaraRepository.save(secretara);
    }

    /**
     * Sterge o secretara din sistem pe baza ID-ului.
     *
     * @param idSecretara ID-ul secretarei de sters.
     */
    @Override
    public void stergeSecretara(Integer idSecretara){
        secretaraRepository.deleteById(idSecretara);
    }
}
