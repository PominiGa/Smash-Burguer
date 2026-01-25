package com.example.Smash.controller;

import com.example.Smash.dto.AtualizarSenhaDTO;
import com.example.Smash.model.cliente.Cliente;
import com.example.Smash.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(name = "/clientes")
public class ClienteController {
    @Autowired
    public ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAll() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> listarCliente(@PathVariable UUID id) {
        return clienteService.listarPorId(id);
    }

    @PostMapping
    public Cliente salvar(@RequestBody Cliente cliente) {
        return clienteService.criarCliente(cliente);
    }

    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable UUID id, @RequestBody Cliente cliente) {
        return clienteService.atualizarPorId(id, cliente);
    }

    @PutMapping("/senha/{email}")
    public ResponseEntity<Void> atualizarSenha(
            @PathVariable String email,
            @RequestBody AtualizarSenhaDTO dto) {

        clienteService.atualizarSenhaPorEmail(email, dto.getSenha());
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        clienteService.deletarPorId(id);
    }
}
