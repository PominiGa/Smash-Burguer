package com.example.Smash.controller;

import com.example.Smash.model.comida.Pedido;
import com.example.Smash.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Pedido criarPedido(@RequestParam UUID clienteId, @RequestBody List<Long> comidasIds) {
        return pedidoService.criarPedido(clienteId, comidasIds);
    }

    @PutMapping("/{id}/pagar")
    public Pedido pagarPedido(@PathVariable Long id) {
        return pedidoService.pagarPedido(id);
    }
}
