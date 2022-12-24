package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .anyRequest().authenticated()
                .and()
                    .cors().
                and()
                    // Oauth Resource emite um opaqueToken, é um chave que não da pra identificar oque existe dentro dela
                    .oauth2ResourceServer()
                    //.opaqueToken()
                    .jwt();
    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        var secretKey = new SecretKeySpec("algaworkssdfsdf878451548748454f5asdfasdas".getBytes(), "HmacSHA256");
//
//        return NimbusJwtDecoder
//                .withSecretKey(secretKey)
//                .build();
//    }

}
