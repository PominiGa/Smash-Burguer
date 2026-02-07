package com.example.Smash.repository;

import com.example.Smash.model.usuario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByDocumento(String documento);

    boolean existsByDocumento(String documento);
}
