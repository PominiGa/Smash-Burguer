package com.example.Smash.service;

import com.example.Smash.model.usuario.Funcionario;
import com.example.Smash.repository.FuncionarioRepository;
import com.example.Smash.exception.RecursoNaoEncontradoException;
import com.example.Smash.exception.RegraDeNegocioException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Funcionário não encontrado com id: " + id));
    }

    public Funcionario criarFuncionario(Funcionario funcionario) {

        validarDocumentoUnico(funcionario.getDocumento());
        validarSalario(funcionario.getSalario());

        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizarFuncionario(Long id, Funcionario dadosAtualizacao) {

        Funcionario funcionario = buscarPorId(id);

        validarAtualizacaoDocumento(funcionario, dadosAtualizacao.getDocumento());
        validarSalario(dadosAtualizacao.getSalario());

        funcionario.setPrimeiroNome(dadosAtualizacao.getPrimeiroNome());
        funcionario.setSegundoNome(dadosAtualizacao.getSegundoNome());
        funcionario.setSalario(dadosAtualizacao.getSalario());
        funcionario.setDocumento(dadosAtualizacao.getDocumento());

        return funcionarioRepository.save(funcionario);
    }

    public void deletarFuncionario(Long id) {
        Funcionario funcionario = buscarPorId(id);
        funcionarioRepository.delete(funcionario);
    }


    private void validarDocumentoUnico(String documento) {
        if (funcionarioRepository.existsByDocumento(documento)) {
            throw new RegraDeNegocioException("Documento já cadastrado.");
        }
    }

    private void validarAtualizacaoDocumento(Funcionario funcionario, String novoDocumento) {

        boolean documentoFoiAlterado = !funcionario.getDocumento().equals(novoDocumento);

        if (documentoFoiAlterado && funcionarioRepository.existsByDocumento(novoDocumento)) {
            throw new RegraDeNegocioException("Documento já cadastrado.");
        }
    }

    private void validarSalario(BigDecimal salario) {
        if (salario == null || salario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Salário deve ser maior que zero.");
        }
    }
}
