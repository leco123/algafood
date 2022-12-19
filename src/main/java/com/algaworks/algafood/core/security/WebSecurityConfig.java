package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
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
}
