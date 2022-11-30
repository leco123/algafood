package com.algaworks.algafood.core.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurer Define método de Callback para configurar o spring MVC
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Método usado para habilitar o CORS globalmente
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // DEFININDO QUALQUER CAMINHO E QUALQUER ORIGIN
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");

                // PADRÃO DO SPRING
                // .allowedMethods("GET","HEAD","POST")
                // .allowedMethods("*")
                // .maxAge(30); // 30 PADRÃO
    }
}
