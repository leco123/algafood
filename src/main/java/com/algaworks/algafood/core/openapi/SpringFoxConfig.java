package com.algaworks.algafood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer {

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
                    // Selecionar os andPoint que encontrar pode selecionar
                    .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
                    // Tudo que encontrar pode selecionar
                    .apis(RequestHandlerSelectors.any())

                    // selecionar os caminhos
                    //.paths(PathSelectors.ant("/restaurantes/*"))
                .build();
    }

    // Mapeamento para servir arquivos estáticos de arquivos SpringFox Swagger UI.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Definindo caminho do SwaggerUi
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        // mapeamento do webjars que contém as imagens e outros arquivos css
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
