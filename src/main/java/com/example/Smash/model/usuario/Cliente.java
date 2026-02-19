package com.example.Smash.model.usuario;

import com.example.Smash.model.comida.Pedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
}