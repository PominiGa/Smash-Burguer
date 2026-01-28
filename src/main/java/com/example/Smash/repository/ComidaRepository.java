package com.example.Smash.repository;

import com.example.Smash.model.comida.Comida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComidaRepository extends JpaRepository<Comida, Long> {
}
