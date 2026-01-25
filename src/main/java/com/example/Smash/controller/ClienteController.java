package com.example.Smash.controller;

import com.example.Smash.model.cliente.Cliente;
import com.example.Smash.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/clientes")
public class ClienteController {
    @Autowired
    public ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAll() {
        return clienteService.listarTodos();
    }

    @PostMapping
    public Cliente salvar(@RequestBody Cliente cliente) {
        return clienteService.criarCliente(cliente);
    }

    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable long id, @RequestBody Cliente cliente) {
        return clienteService.atualizarPorId(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable long id) {
        clienteService.deletarPorId(id);
    }
}
