package com.liceu.sistem_evidenta_elevi.repository;

import com.liceu.sistem_evidenta_elevi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pentru entitatea User.
 * Aceasta interfata extinde JpaRepository si furnizeaza operatiuni standard de CRUD pentru entitatea User.
 * De asemenea, ofera o metoda suplimentara pentru a gasi un User dupa username.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Gaseste un User pe baza username-ului.
     *
     * @param username Numele de utilizator al User-ului.
     * @return User-ul corespunzator username-ului.
     */
    User findByUsername(String username);
}