package com.portaria.gestao.controller;

import com.portaria.gestao.model.Funcionario;
import com.portaria.gestao.model.RegistroPortaria;
import com.portaria.gestao.repository.FuncionarioRepository;
import com.portaria.gestao.repository.RegistroPortariaRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/portaria")
public class PortariaController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private RegistroPortariaRepository registroRepository;

    @Data
    static class RegistroRequest {
        private String identificador;
    }

    @PostMapping("/entrada")
    public ResponseEntity<RegistroPortaria> registrarEntrada(@RequestBody RegistroRequest request) {

        Funcionario funcionario = funcionarioRepository.findByDocumento(request.getIdentificador());

        if (funcionario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        RegistroPortaria registroAtivo = registroRepository.findFirstByFuncionarioAndHoraSaidaIsNullOrderByHoraEntradaDesc(funcionario);

        if (registroAtivo != null) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        RegistroPortaria novoRegistro = new RegistroPortaria();
        novoRegistro.setFuncionario(funcionario);
        novoRegistro.setHoraEntrada(LocalDateTime.now());

        RegistroPortaria salvo = registroRepository.save(novoRegistro);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @PutMapping("/saida")
    public ResponseEntity<RegistroPortaria> registrarSaida(@RequestBody RegistroRequest request) {

        Funcionario funcionario = funcionarioRepository.findByDocumento(request.getIdentificador());

        if (funcionario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        RegistroPortaria registroAtivo = registroRepository.findFirstByFuncionarioAndHoraSaidaIsNullOrderByHoraEntradaDesc(funcionario);

        if (registroAtivo == null) {

            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }


        registroAtivo.setHoraSaida(LocalDateTime.now());
        RegistroPortaria atualizado = registroRepository.save(registroAtivo);

        return new ResponseEntity<>(atualizado, HttpStatus.OK);
    }
}