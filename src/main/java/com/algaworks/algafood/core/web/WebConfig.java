package com.algaworks.algafood.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

/**
 * WebMvcConfigurer Define método de Callback para configurar o spring MVC
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private __ApiDeprecationHandler apiDeprecationHandler;

    @Autowired
    private ApiRetirementHandler apiRetirementHandler;

    /**
     * Método usado para habilitar o CORS globalmente
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // DEFININDO QUALQUER CAMINHO E QUALQUER ORIGIN
        registry.addMapping("/**")
                //.allowedOrigins("*")
                .allowedMethods("*");

                // PADRÃO DO SPRING
                // .allowedMethods("GET","HEAD","POST")
                // .allowedMethods("*")
                // .maxAge(30); // 30 PADRÃO
    }

//    @Override
//    public void configureContentNegotiation (ContentNegotiationConfigurer configurer) {
//        /** Definindo qual MediaType padrão **/
//        configurer.defaultContentType(AlgaMediaTypes.V2_APPLICATION_JSON);
//    }


    @Override
    public void addInterceptors (InterceptorRegistry registry) {
          // aviso que api está depreciada
//        registry.addInterceptor(apiDeprecationHandler);

        // API desligada retorna http status 410
        //registry.addInterceptor(apiRetirementHandler);
    }

    /**
     * Para funcionar o shallowEtag Basta adicionar essa configuração
     */
    @Bean
    public Filter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }
}
