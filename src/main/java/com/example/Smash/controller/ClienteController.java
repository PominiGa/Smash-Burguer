package com.example.Smash.controller;

import com.example.Smash.dto.AtualizarSenhaDTO;
import com.example.Smash.model.usuario.Cliente;
import com.example.Smash.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {

        Cliente criado = clienteService.criarCliente(cliente);

        URI location = URI.create("/clientes/" + criado.getId());

        return ResponseEntity.created(location).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(
            @PathVariable UUID id,
            @RequestBody Cliente cliente) {

        return ResponseEntity.ok(clienteService.atualizarPorId(id, cliente));
    }

    @PutMapping("/{email}/senha")
    public ResponseEntity<Void> atualizarSenha(
            @PathVariable String email,
            @RequestBody AtualizarSenhaDTO dto) {

        clienteService.atualizarSenhaPorEmail(email, dto.getSenha());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {

        clienteService.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}