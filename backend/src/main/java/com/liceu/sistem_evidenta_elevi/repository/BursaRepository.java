package com.liceu.sistem_evidenta_elevi.repository;

import com.liceu.sistem_evidenta_elevi.entity.Bursa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pentru entitatea Bursa.
 *
 * Aceasta interfata extinde JpaRepository si furnizeaza operatiuni standard pentru gestionarea entitatii Bursa
 * in baza de date, cum ar fi salvarea, stergerea si cautarea burselor.
 */
public interface BursaRepository extends JpaRepository<Bursa, Integer> {
}
