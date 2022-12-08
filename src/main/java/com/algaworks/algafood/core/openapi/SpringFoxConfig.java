package com.algaworks.algafood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    /**
     * Docket em português é sumário, representa uma classe do SpringFox de configuração da OpenApi, para
     * reger definição usando especificação
     */
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                // CONFIGURANDO
                // quais endicadores que deseja especificar para criar o json
                .select()
                    // Tudo que encontrar pode selecionar
                    .apis(RequestHandlerSelectors.any())
                .build();
    }
}
