package com.liceu.sistem_evidenta_elevi.config;
import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementare custom a interfetei {@link UserDetailsService}.
 * Aceasta clasa este responsabila pentru incarcarea detaliilor unui utilizator
 * din baza de date pe baza username-ului. Este utilizata de mecanismul
 * de autentificare al Spring Security.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructor pentru injectarea dependentelor.
     *
     * @param userRepository instanta {@link UserRepository} pentru accesul la datele utilizatorilor.
     */
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Incarca un utilizator din baza de date pe baza username-ului.
     *
     * @param username numele de utilizator al utilizatorului care trebuie incarcat.
     * @return un obiect {@link UserDetails} care contine informatiile utilizatorului.
     * @throws UsernameNotFoundException daca utilizatorul cu username-ul specificat nu este gasit.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Userul  nu a fost gasit");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getParola(),
                AuthorityUtils.createAuthorityList(user.getRol().name())
        );
    }
}