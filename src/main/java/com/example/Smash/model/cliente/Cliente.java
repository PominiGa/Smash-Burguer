package com.example.Smash.model.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long id;

    private String primeiroNome;
    private String segundoNome;

    @Column(unique = true)
    private String documento;

    @Column(unique = true)
    private String email;
    private String cep;
    private String numeroCasa;

    @Column(unique = true)
    private String telefone;
}
