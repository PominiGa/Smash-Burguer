package com.example.Smash.repository;

import com.example.Smash.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByEmail(String email);

    boolean existsById(UUID id);

    Optional<Cliente> findById(UUID id);

    void deleteById(UUID id);
}
