package com.example.Smash.model.comida;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comidas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private double valor;
    private String descricao;
}
