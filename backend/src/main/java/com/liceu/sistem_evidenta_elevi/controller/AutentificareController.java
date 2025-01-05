package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.UserRequestDTO;
import com.liceu.sistem_evidenta_elevi.dto.UserResponseDTO;
import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.mapper.UserMapper;
import com.liceu.sistem_evidenta_elevi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Controller pentru gestionarea autentificarea utilizatorilor.
 * Aceasta clasa gestioneaza autentificarea utilizatorilor si returneaza informatii
 * despre utilizatorii autentificati.
 */
@RestController
@RequestMapping("/liceu/autentificare")
public class AutentificareController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Constructor pentru injectarea dependentelor.
     *
     * @param authenticationManager Managerul de autentificare al Spring Security.
     * @param userService Serviciul pentru gestionarea utilizatorilor.
     * @param userMapper Mapper pentru conversia dintre entitati si DTO-uri.
     */
    @Autowired
    public AutentificareController(AuthenticationManager authenticationManager,UserService userService,
                                   UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Endpoint pentru autentificarea utilizatorilor.
     * Aceasta metoda primeste username-ul si parola utilizatorului, efectueaza autentificarea
     * si returneaza informatiile utilizatorului autentificat.
     *
     * @param userRequest Obiect {@link UserRequestDTO} care contine numele de utilizator si parola.
     * @return Un obiect {@link ResponseEntity} care contine un {@link UserResponseDTO}
     * cu informatiile utilizatorului autentificat, sau un raspuns HTTP 401 daca autentificarea esueaza.
     */
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO userRequest) {
        try {
            // autentificare user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequest.getUsername(),
                            userRequest.getParola()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // obtine user autentificat(principal)
            org.springframework.security.core.userdetails.User principal =
                    (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

            // ontine user din baza de date pe baza username-ului
            String username = principal.getUsername();
            User userAutentificat = userService.getUserByUsername(username);

            // returnare informatii user in format DTO
            return ResponseEntity.ok(userMapper.toResponseDTO(userAutentificat));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}
