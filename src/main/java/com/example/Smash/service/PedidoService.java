package com.example.Smash.service;

import com.example.Smash.model.comida.Comida;
import com.example.Smash.model.comida.Pedido;
import com.example.Smash.model.usuario.Cliente;
import com.example.Smash.repository.ClienteRepository;
import com.example.Smash.repository.ComidaRepository;
import com.example.Smash.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    public PedidoRepository pedidoRepository;

    @Autowired
    public ClienteRepository clienteRepository;

    @Autowired
    public ComidaRepository comidaRepository;

    public Pedido criarPedido(UUID clienteId, List<Long> comidasIds) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException(("Cliente não encontrado")));

        List<Comida> comidas = comidaRepository.findAllById(comidasIds);

        if (comidas.isEmpty()) {
            throw new RuntimeException("Nenhuma comida encontrada");
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setComidas(comidas);

        return pedidoRepository.save(pedido);
    }

}
