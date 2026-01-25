package com.example.Smash.service;

import com.example.Smash.model.cliente.Cliente;
import com.example.Smash.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    public ClienteRepository repository;

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Optional<Cliente> listarPorId(long id) {
        if (repository.existsById(id)) {
            return repository.findById(id);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    public Cliente criarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente atualizarPorId(long id, Cliente cliente) {
        if (repository.existsById(id)) {
            cliente.setId(id);
            return repository.save(cliente);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    public void deletarPorId(long id) {
        repository.deleteById(id);
    }

    public void atualizarSenhaPorEmail(String email, String novaSenha) {
        Cliente cliente = repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Email não encontrado"));

        cliente.setSenha(novaSenha);
        repository.save(cliente);
    }



    public Optional<Cliente> findById(long id) {
        return repository.findById(id);
    }
}
