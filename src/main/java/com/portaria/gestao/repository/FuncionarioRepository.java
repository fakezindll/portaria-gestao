package com.portaria.gestao.repository;

import com.portaria.gestao.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Funcionario findByDocumento(String documento);
}