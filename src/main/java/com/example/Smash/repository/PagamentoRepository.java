package com.example.Smash.repository;

import com.example.Smash.model.pagamento.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
