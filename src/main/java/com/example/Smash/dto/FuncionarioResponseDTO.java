package com.example.Smash.dto;

import java.math.BigDecimal;

public class FuncionarioResponseDTO {

    private Long id;
    private String primeiroNome;
    private String segundoNome;
    private BigDecimal salario;

    public FuncionarioResponseDTO(Long id, String primeiroNome, String segundoNome, BigDecimal salario) {
        this.id = id;
        this.primeiroNome = primeiroNome;
        this.segundoNome = segundoNome;
        this.salario = salario;
    }

    public Long getId() { return id; }
    public String getPrimeiroNome() { return primeiroNome; }
    public String getSegundoNome() { return segundoNome; }
    public BigDecimal getSalario() { return salario; }
}
