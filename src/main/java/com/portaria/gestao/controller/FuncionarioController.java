package com.portaria.gestao.controller;

import com.portaria.gestao.dto.FuncionarioRequest;
import com.portaria.gestao.dto.FuncionarioResponse;
import com.portaria.gestao.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<FuncionarioResponse> criarFuncionario(@RequestBody FuncionarioRequest request) {
        FuncionarioResponse response = funcionarioService.criarFuncionario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponse>> listarFuncionarios() {
        // Você precisará implementar o método listarTodos() no seu Service
        // return ResponseEntity.ok(funcionarioService.listarTodos());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> buscarPorId(@PathVariable Long id) {
        // Você precisará implementar o método buscarPorId() no seu Service
        // return funcionarioService.buscarPorId(id)
        //     .map(ResponseEntity::ok)
        //     .orElseGet(() -> ResponseEntity.notFound().build());
        return ResponseEntity.ok().build(); // Temporário
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioRequest request) {
        // return funcionarioService.atualizarFuncionario(id, request)
        //     .map(ResponseEntity::ok)
        //     .orElseGet(() -> ResponseEntity.notFound().build());
        return ResponseEntity.ok().build(); // Temporário
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        // funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}