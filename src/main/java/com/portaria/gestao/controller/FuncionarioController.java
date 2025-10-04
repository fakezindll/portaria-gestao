package com.portaria.gestao.controller;

import com.portaria.gestao.model.Funcionario;
import com.portaria.gestao.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Funcionario> cadastrar(@RequestBody Funcionario novoFuncionario) {

        if (novoFuncionario.getVeiculos() != null) {
            novoFuncionario.getVeiculos().forEach(veiculo -> veiculo.setFuncionario(novoFuncionario));
        }

        Funcionario salvo = funcionarioRepository.save(novoFuncionario);

        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {

        return funcionarioRepository.findById(id).map(funcionario -> new ResponseEntity<>(funcionario, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionarioDetalhes) {

        return funcionarioRepository.findById(id).map(funcionarioExistente -> {
            funcionarioExistente.setNome(funcionarioDetalhes.getNome());
            funcionarioExistente.setDocumento(funcionarioDetalhes.getDocumento());
            funcionarioExistente.setFotoUrl(funcionarioDetalhes.getFotoUrl());
            funcionarioExistente.setAtivo(funcionarioDetalhes.isAtivo());


            if (funcionarioDetalhes.getVeiculos() != null) {
                funcionarioExistente.getVeiculos().clear();
                funcionarioDetalhes.getVeiculos().forEach(veiculo -> {
                    veiculo.setFuncionario(funcionarioExistente);
                    funcionarioExistente.getVeiculos().add(veiculo);
                });
            }

            Funcionario atualizado = funcionarioRepository.save(funcionarioExistente);
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        return funcionarioRepository.findById(id).map(funcionario -> {
            funcionarioRepository.delete(funcionario);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}