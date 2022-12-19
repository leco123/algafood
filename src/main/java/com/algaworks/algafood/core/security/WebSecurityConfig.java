package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        // Configurar em memória
        auth.inMemoryAuthentication()
            .withUser("alex")
                    .password(passwordEncoder().encode("123"))
                    .roles("ADMIN")
            .and()
            .withUser("joao")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN");
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.httpBasic()

                .and()
                .authorizeRequests()
                    // Liberar and points que não precissão autenticação para acessar
                    .antMatchers("/v1/cozinhas/**").permitAll()
                    // autoriza qualquer autorização que está autenticada
                    .anyRequest().authenticated()
                .and()
                    // definindo que não será usado session na segurança
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    // Desabilitar CSRF
                    .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // método de criptografia, para incriptografar as senhas
        return new BCryptPasswordEncoder();
    }
}
