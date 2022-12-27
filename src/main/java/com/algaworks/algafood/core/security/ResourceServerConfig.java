package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter;
        http
            .csrf().disable()
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

            var scopesAuthoritiesConverter = new JwtAuthenticationConverter();
            Collection<GrantedAuthority> grantedAuthorities = (Collection<GrantedAuthority>) scopesAuthoritiesConverter.convert(jwt);

            // Pegando toda colleção de Authorities;
            var collecaoAuthorities = authorities.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            // adicionando dentro da colleção granteAuthorities
            grantedAuthorities.addAll(collecaoAuthorities);

            return grantedAuthorities;
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
