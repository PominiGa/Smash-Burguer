package com.example.Smash.controller;

import com.example.Smash.dto.PedidoRequestDTO;
import com.example.Smash.model.comida.Pedido;
import com.example.Smash.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:5173")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoRequestDTO request) {

        Pedido pedido = pedidoService.criarPedido(
                request.getClienteId(),
                request.getComidasIds()
        );

        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<Pedido> pagarPedido(@PathVariable Long id) {

        Pedido pedido = pedidoService.pagarPedido(id);

        return ResponseEntity.ok(pedido);
    }
}
