package com.portaria.gestao.service;

import com.portaria.gestao.model.Porteiro;
import com.portaria.gestao.repository.PorteiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PorteiroDetailsService implements UserDetailsService {

    @Autowired
    private PorteiroRepository porteiroRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Porteiro porteiro = porteiroRepository.findByUsername(username);

        if (porteiro == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        return User.builder().username(porteiro.getUsername()).password(porteiro.getPassword()).roles("PORTEIRO").build();
    }
}