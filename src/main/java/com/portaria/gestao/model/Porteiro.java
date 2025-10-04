package com.portaria.gestao.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "porteiros")
public class Porteiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}