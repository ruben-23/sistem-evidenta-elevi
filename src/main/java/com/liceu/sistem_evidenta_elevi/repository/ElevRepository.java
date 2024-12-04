package com.liceu.sistem_evidenta_elevi.repository;

import com.liceu.sistem_evidenta_elevi.entity.Elev;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElevRepository extends JpaRepository<Elev, Integer> {
}
