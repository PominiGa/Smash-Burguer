package com.example.Smash.service;

import com.example.Smash.exception.RecursoNaoEncontradoException;
import com.example.Smash.exception.RegraDeNegocioException;
import com.example.Smash.model.usuario.Cliente;
import com.example.Smash.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Cliente buscarPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Cliente não encontrado com id: " + id));
    }

    public Cliente criarCliente(Cliente cliente) {

        validarEmailUnico(cliente.getEmail());
        validarCamposObrigatorios(cliente);

        return repository.save(cliente);
    }

    public Cliente atualizarPorId(UUID id, Cliente dadosAtualizacao) {

        Cliente cliente = buscarPorId(id);

        validarAtualizacaoEmail(cliente, dadosAtualizacao.getEmail());

        atualizarCampos(cliente, dadosAtualizacao);

        return repository.save(cliente);
    }

    public void atualizarSenhaPorEmail(String email, String novaSenha) {

        Cliente cliente = repository.findByEmail(email)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Email não encontrado"));

        if (novaSenha == null || novaSenha.isBlank()) {
            throw new RegraDeNegocioException("Nova senha inválida.");
        }

        cliente.setSenha(novaSenha);

        repository.save(cliente);
    }

    public void deletarPorId(UUID id) {
        Cliente cliente = buscarPorId(id);
        repository.delete(cliente);
    }

    private void atualizarCampos(Cliente cliente, Cliente dados) {

        if (dados.getPrimeiroNome() != null) {
            cliente.setPrimeiroNome(dados.getPrimeiroNome());
        }

        if (dados.getSegundoNome() != null) {
            cliente.setSegundoNome(dados.getSegundoNome());
        }

        if (dados.getEmail() != null) {
            cliente.setEmail(dados.getEmail());
        }

        if (dados.getSenha() != null) {
            cliente.setSenha(dados.getSenha());
        }
    }

    private void validarEmailUnico(String email) {

        if (email == null || email.isBlank()) {
            throw new RegraDeNegocioException("Email é obrigatório.");
        }

        if (repository.existsByEmail(email)) {
            throw new RegraDeNegocioException("Email já cadastrado.");
        }
    }

    private void validarAtualizacaoEmail(Cliente cliente, String novoEmail) {

        if (novoEmail == null || novoEmail.isBlank()) {
            throw new RegraDeNegocioException("Email é obrigatório.");
        }

        boolean emailFoiAlterado = !cliente.getEmail().equals(novoEmail);

        if (emailFoiAlterado && repository.existsByEmail(novoEmail)) {
            throw new RegraDeNegocioException("Email já cadastrado.");
        }
    }

    private void validarCamposObrigatorios(Cliente cliente) {

        if (cliente.getPrimeiroNome() == null || cliente.getPrimeiroNome().isBlank()) {
            throw new RegraDeNegocioException("Primeiro nome é obrigatório.");
        }

        if (cliente.getSenha() == null || cliente.getSenha().isBlank()) {
            throw new RegraDeNegocioException("Senha é obrigatória.");
        }
    }
}
