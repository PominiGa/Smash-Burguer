package com.example.Smash.model.pagamento;

import com.example.Smash.model.comida.Pedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    private LocalDateTime dataPagamento;
}
