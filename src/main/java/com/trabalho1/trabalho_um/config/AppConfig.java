package com.trabalho1.trabalho_um.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.trabalho1.trabalho_um.model.Vault;

@Configuration
public class AppConfig {

    @Bean
    @SessionScope
    public Vault vault() {
        return new Vault(); // Instância do Vault específica para cada sessão
    }
}
