package com.liceu.sistem_evidenta_elevi.repository;

import com.liceu.sistem_evidenta_elevi.entity.Absenta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfata pentru operatiuni CRUD pe entitatea {@link Absenta}.
 * Aceasta extinde {@link JpaRepository}, care ofera metode predefinite pentru CRUD.
 */
public interface AbsentaRepository extends JpaRepository<Absenta, Integer> {
}
