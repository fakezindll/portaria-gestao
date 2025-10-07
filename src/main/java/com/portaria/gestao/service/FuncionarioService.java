package com.portaria.gestao.service;

import com.portaria.gestao.dto.FuncionarioRequest;
import com.portaria.gestao.dto.FuncionarioResponse;
import com.portaria.gestao.dto.VeiculoResponse;
import com.portaria.gestao.model.Funcionario;
import com.portaria.gestao.model.Veiculo;
import com.portaria.gestao.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioResponse criarFuncionario(FuncionarioRequest request) {

        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome(request.getNome());
        novoFuncionario.setDocumento(request.getDocumento());
        novoFuncionario.setFotoUrl(request.getFotoUrl());

        if (request.getVeiculos() != null) {
            novoFuncionario.setVeiculos(request.getVeiculos().stream().map(reqVeiculo -> {
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(reqVeiculo.getPlaca());
                veiculo.setModelo(reqVeiculo.getModelo());
                veiculo.setFuncionario(novoFuncionario);
                return veiculo;
            }).collect(Collectors.toList()));
        }

        Funcionario funcionarioSalvo = funcionarioRepository.save(novoFuncionario);

        return toResponse(funcionarioSalvo);
    }

    private FuncionarioResponse toResponse(Funcionario funcionario) {
        FuncionarioResponse response = new FuncionarioResponse();

        response.setId(funcionario.getId());
        response.setNome(funcionario.getNome());
        response.setDocumento(funcionario.getDocumento());
        response.setFotoUrl(funcionario.getFotoUrl());
        response.setAtivo(funcionario.isAtivo());

        response.setVeiculos(funcionario.getVeiculos().stream().map(v -> {
            VeiculoResponse vResponse = new VeiculoResponse();
            vResponse.setId(v.getId());
            vResponse.setPlaca(v.getPlaca());
            vResponse.setModelo(v.getModelo());
            return vResponse;
        }).collect(Collectors.toList()));

        return response;
    }
}