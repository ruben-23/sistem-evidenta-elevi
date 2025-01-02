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

@Service
public class SecretaraServiceImplementare implements SecretaraService {

    private final SecretaraRepository secretaraRepository;
    private final UserService userService;
    private final SecretaraMapper secretaraMapper;

    // folosire lazy pentru userService pentru a evita ciclu de dependente (secretaraService-userService)
    @Autowired
    public SecretaraServiceImplementare(SecretaraRepository secretaraRepository, SecretaraMapper secretaraMapper,
                                        @Lazy UserService userService) {
        this.secretaraRepository = secretaraRepository;
        this.userService = userService;
        this.secretaraMapper = secretaraMapper;
    }

    @Override
    public List<Secretara> getAllSecretare(){
        return secretaraRepository.findAll();
    }

    @Override
    public Secretara getSecretaraById(Integer idSecretara){
        return secretaraRepository.findById(idSecretara)
                .orElseThrow(() -> new RuntimeException("Secretara nu a fost gasita"));
    }

    @Transactional
    @Override
    public Secretara actualizareSecretara(SecretaraDTO secretaraDTO){
        Secretara secretara = getSecretaraById(secretaraDTO.getIdSecretara());
        User user = userService.getUserById(secretaraDTO.getIdUser());
        secretaraMapper.updateEntityFromDTO(secretaraDTO, secretara, user);
        return secretara;
    }

    @Transactional
    @Override
    public Secretara adaugaSecretara(SecretaraDTO secretaraDTO, User user){
        Secretara secretara = secretaraMapper.toEntity(secretaraDTO, user);
        return secretaraRepository.save(secretara);
    }

    @Override
    public void stergeSecretara(Integer idSecretara){
        secretaraRepository.deleteById(idSecretara);
    }

}
