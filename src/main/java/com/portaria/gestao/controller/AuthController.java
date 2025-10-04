package com.portaria.gestao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/success")
    public String loginSuccess() {
        return "Login bem-sucedido! Bem-vindo, Porteiro.";
    }

    @PostMapping("/login")
    public void login() {
    }
}