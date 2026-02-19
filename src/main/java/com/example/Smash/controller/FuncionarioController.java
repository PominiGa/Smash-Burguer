package com.example.Smash.controller;

import com.example.Smash.dto.FuncionarioResponseDTO;
import com.example.Smash.model.usuario.Funcionario;
import com.example.Smash.service.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin(origins = "http://localhost:5173")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listarTodos() {

        List<FuncionarioResponseDTO> lista = funcionarioService.listarTodos()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorId(@PathVariable Long id) {

        Funcionario funcionario = funcionarioService.buscarPorId(id);

        return ResponseEntity.ok(toDTO(funcionario));
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> criarFuncionario(@RequestBody Funcionario funcionario) {

        Funcionario criado = funcionarioService.criarFuncionario(funcionario);

        URI location = URI.create("/api/funcionarios/" + criado.getId());

        return ResponseEntity.created(location).body(toDTO(criado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizarFuncionario(
            @PathVariable Long id,
            @RequestBody Funcionario funcionario) {

        Funcionario atualizado = funcionarioService.atualizarFuncionario(id, funcionario);

        return ResponseEntity.ok(toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {

        funcionarioService.deletarFuncionario(id);

        return ResponseEntity.noContent().build();
    }

    /* =========================
       MAPPER
       ========================= */

    private FuncionarioResponseDTO toDTO(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
                funcionario.getId(),
                funcionario.getPrimeiroNome(),
                funcionario.getSegundoNome(),
                funcionario.getSalario()
        );
    }
}
