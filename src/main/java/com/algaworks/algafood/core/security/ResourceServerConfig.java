package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collections;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter;
        http
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/v1/cozinhas/**").hasAnyAuthority("EDITAR_COZINHAS")
                    .antMatchers(HttpMethod.PUT, "/v1/cozinhas/**").hasAnyAuthority("EDITAR_COZINHAS")
                    .antMatchers(HttpMethod.GET, "/v1/cozinhas/**").authenticated()
                    //.anyRequest().authenticated()
                    .anyRequest().denyAll()
                .and()
                .cors().and()
                // Oauth Resource emite um opaqueToken, é um chave que não da pra identificar oque existe dentro dela
                //.opaqueToken()
                .oauth2ResourceServer()
                    .jwt()
                    .jwtAuthenticationConverter(jwtAuthenticationConverter());
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter () {
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {

            var authorities = jwt.getClaimAsStringList("authorities");

            if (authorities == null) {
                authorities = Collections.emptyList();
            }
            return authorities.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        });

        return jwtAuthenticationConverter;
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
