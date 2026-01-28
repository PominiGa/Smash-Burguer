package com.example.Smash.repository;

import com.example.Smash.model.comida.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
