package com.liceu.sistem_evidenta_elevi.repository;

import com.liceu.sistem_evidenta_elevi.entity.Secretara;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pentru entitatea Secretara.
 * Aceasta interfata extinde JpaRepository si furnizeaza operatiuni standard de CRUD pentru entitatea Secretara.
 */
@Repository
public interface SecretaraRepository extends JpaRepository<Secretara, Integer> {
}