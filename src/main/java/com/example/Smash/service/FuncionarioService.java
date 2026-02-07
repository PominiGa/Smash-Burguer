package com.example.Smash.service;

import com.example.Smash.model.usuario.Funcionario;
import com.example.Smash.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public Funcionario criarFuncionario(Funcionario funcionario) {

        if (funcionarioRepository.existsByDocumento(funcionario.getDocumento())) {
            throw new RuntimeException("Documento já cadastrado");
        }

        validarSalario(funcionario.getSalario());

        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizarFuncionario(Long id, Funcionario dados) {
        Funcionario funcionario = buscarPorId(id);

        funcionario.setPrimeiroNome(dados.getPrimeiroNome());
        funcionario.setSegundoNome(dados.getSegundoNome());
        funcionario.setSalario(dados.getSalario());

        validarSalario(dados.getSalario());

        return funcionarioRepository.save(funcionario);
    }

    public void deletarFuncionario(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado");
        }
        funcionarioRepository.deleteById(id);
    }

    private void validarSalario(BigDecimal salario) {
        if (salario == null || salario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Salário deve ser maior que zero");
        }
    }
}
