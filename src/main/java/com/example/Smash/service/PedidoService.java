package com.example.Smash.service;

import com.example.Smash.exception.RecursoNaoEncontradoException;
import com.example.Smash.exception.RegraDeNegocioException;
import com.example.Smash.model.comida.Comida;
import com.example.Smash.model.comida.Pedido;
import com.example.Smash.model.pagamento.StatusPagamento;
import com.example.Smash.model.usuario.Cliente;
import com.example.Smash.repository.ClienteRepository;
import com.example.Smash.repository.ComidaRepository;
import com.example.Smash.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ComidaRepository comidaRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         ComidaRepository comidaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.comidaRepository = comidaRepository;
    }

    public Pedido criarPedido(UUID clienteId, List<Long> comidasIds) {

        if (comidasIds == null || comidasIds.isEmpty()) {
            throw new RegraDeNegocioException("Pedido deve conter ao menos uma comida.");
        }

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Cliente não encontrado"));

        List<Comida> comidas = comidaRepository.findAllById(comidasIds);

        if (comidas.size() != comidasIds.size()) {
            throw new RegraDeNegocioException("Uma ou mais comidas não foram encontradas.");
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setComidas(comidas);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatusPagamento(StatusPagamento.PENDENTE);

        pedido.setValorTotal(calcularValorTotal(comidas));

        return pedidoRepository.save(pedido);
    }

    public Pedido pagarPedido(Long pedidoId) {

        Pedido pedido = buscarPedidoPorId(pedidoId);

        if (pedido.getStatusPagamento() == StatusPagamento.PAGO) {
            throw new RegraDeNegocioException("Pedido já foi pago.");
        }

        pedido.setStatusPagamento(StatusPagamento.PAGO);

        return pedidoRepository.save(pedido);
    }

    public void validarPedidoPago(Long pedidoId) {
        Pedido pedido = buscarPedidoPorId(pedidoId);

        if (pedido.getStatusPagamento() != StatusPagamento.PAGO) {
            throw new RegraDeNegocioException("Pedido não está pago.");
        }
    }

    private Pedido buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Pedido não encontrado"));
    }

    private BigDecimal calcularValorTotal(List<Comida> comidas) {
        return comidas.stream()
                .map(Comida::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
