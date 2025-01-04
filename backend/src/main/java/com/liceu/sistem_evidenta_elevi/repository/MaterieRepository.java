package com.liceu.sistem_evidenta_elevi.repository;

import com.liceu.sistem_evidenta_elevi.entity.Materie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterieRepository extends JpaRepository<Materie, Integer> {
}