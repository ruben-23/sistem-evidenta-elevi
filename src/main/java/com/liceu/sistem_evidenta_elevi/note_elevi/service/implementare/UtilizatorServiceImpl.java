package com.liceu.sistem_evidenta_elevi.note_elevi.service.implementare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilizatorServiceImpl implements UtilizatorService {

    private final UtilizatorRepository userRepository;

    @Autowired
    public UtilizatorServiceImpl(UtilizatorRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Utilizator> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
