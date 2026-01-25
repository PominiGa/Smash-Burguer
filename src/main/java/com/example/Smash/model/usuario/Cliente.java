package com.example.Smash.model.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    @JsonIgnore
    private String senha;
}
