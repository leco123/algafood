package com.algaworks.algafood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
// BeanValidatorPluginsConfiguration Classe de configuração usado para lêr a anotattion do JPA para validação do Sweeger UI
@Import(BeanValidatorPluginsConfiguration.class)
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
                    .build()
                .apiInfo(apiInfo())
                .tags(new Tag("Cidades", "Gerencia as cidades"));
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                        .title("AlgaFood API")
                        .description("API aberta para clientes e restaurantes")
                        .version("1")
                        .contact(new Contact(
                                "Alex de Carvalho",
                                "https://www.meusite.com.br",
                                "meuemail@meusite.com.br"
                                ))
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
