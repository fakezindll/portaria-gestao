package com.portaria.gestao.repository;

import com.portaria.gestao.model.Porteiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PorteiroRepository extends JpaRepository<Porteiro, Long> {
    Porteiro findByUsername(String username);
}