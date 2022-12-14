package com.algaworks.algafood.core.springfox;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.cozinha.CozinhaModel;
import com.algaworks.algafood.api.v1.model.endereco.cidade.CidadeModel;
import com.algaworks.algafood.api.v1.model.endereco.estado.EstadoModel;
import com.algaworks.algafood.api.v1.model.pedido.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.restaurante.formas_pagamento.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.restaurante.produto.ProdutoModel;
import com.algaworks.algafood.api.v1.model.usuario.UsuarioModel;
import com.algaworks.algafood.api.v1.model.usuario.grupo.GrupoModel;
import com.algaworks.algafood.api.v1.model.usuario.permissao.PermissaoModel;
import com.algaworks.algafood.api.v1.openapi.model.*;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
// BeanValidatorPluginsConfiguration Classe de configura????o usado para l??r a anotattion do JPA para valida????o do Sweeger UI
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

    /**
     * Docket em portugu??s ?? sum??rio, representa uma classe do SpringFox de configura????o da OpenApi, para
     * reger defini????o usando especifica????o
     */
    @Bean
    public Docket apiDocketV1() {

        var typeResolver = new TypeResolver();
        // CONFIGURANDO
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .groupName("V1")
                // quais endicadores que deseja especificar para criar o json
                .select()
                    // Selecionar os andPoint que encontrar pode selecionar
                    .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
                    .paths(PathSelectors.ant("/v1/**"))
                    // Tudo que encontrar pode selecionar
                    .apis(RequestHandlerSelectors.any())

                    // selecionar os caminhos
                    //.paths(PathSelectors.ant("/restaurantes/*"))
                    .build()
                //useDefaultResponseMessages, desabilitar c??digos de status 406, 500... gerados de forma padr??o pelo Swegger UI
                .useDefaultResponseMessages(false)
                // Definir resposta padr??o para os m??todos usando globalResponseMessage
                .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
                .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
                // Parametros Globais usado no SquigglyConfig, usado para fazer filtros da requisi????o
                // e tamb??m est?? sendo adicionando par??metros de forma impl??cita
                /*.globalOperationParameters(Arrays.asList(
                        new ParameterBuilder()
                                .name("campos")
                                .description("Nomes das propriedades separadas por v??rgula")
                                .parameterType("query")
                                .modelRef(new ModelRef("string"))
                                .build()
                ))*/
                // Adicionar modelo de problem
                .additionalModels(typeResolver.resolve(Problem.class))
                // Ignorar Argumento ou propriedade no ParameterTypes que n??o deve ser mostrado na documenta????o
                .ignoredParameterTypes(ServletWebRequest.class,
                        URL.class,
                        URI.class,
                        URLStreamHandler.class,
                        Resource.class,
                        File.class,
                        InputStream.class)
                // Quando recurso faz uso de pagina????o Pageable, deve ser criado uma classe para subistituir essa
                // pagina????o PageableModelOpenApi.class, ?? apenas para fim de documenta????o
                .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
                .directModelSubstitute(Links.class, LinksModelOpenApi.class)

                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(PagedModel.class, CozinhaModel.class),
                        CozinhasModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(PagedModel.class, PedidoResumoModel.class),
                        PedidosResumoModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, CidadeModel.class),
                        CidadesModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, EstadoModel.class),
                        EstadosModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, FormaPagamentoModel.class),
                        FormasPagamentoModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, GrupoModel.class),
                        GruposModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, PermissaoModel.class),
                        PermissoesModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, ProdutoModel.class),
                        ProdutosModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, RestauranteBasicoModel.class),
                        RestaurantesBasicoModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, UsuarioModel.class),
                        UsuariosModelOpenApi.class))

                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(Arrays.asList(securityContext()))

                .apiInfo(apiInfoV1())
                .tags(new Tag("Cidades", "Gerencia as cidades"),
                        new Tag("Grupos","Gerencia os grupos de usu??rios"),
                        new Tag("Cozinhas", "Gerencia as cozinhas"),
                        new Tag("Formas de pagamento", "Gerencia as formas de pagamento"),
                        new Tag("Pedidos", "Gerencia os pedidos"),
                        new Tag("Restaurantes", "Gerencia os restaurantes"),
                        new Tag("Estados", "Gerencia os estados"),
                        new Tag("Produtos", "Gerencia os produtos de restaurantes"),
                        new Tag("Usu??rios", "Gerencia os usu??rios"),
                        new Tag("Estat??sticas", "Estat??sticas da AlgaFood"),
                        new Tag("Permiss??es", "Gerencia as permiss??es"));
    }

    private SecurityScheme securityScheme() {
        return new OAuthBuilder()
                .name("Algafood")
                .grantTypes(grantTypes())
                .scopes(scopes())
                .build();
    }

    private List<GrantType> grantTypes () {
        // Retorna passwordFlow
        return  Arrays.asList(new ResourceOwnerPasswordCredentialsGrant("/oauth/token"));
    }

    private SecurityContext securityContext () {
        var securityReference = SecurityReference.builder()
                .reference("Algafood")
                .scopes(scopes().toArray(new AuthorizationScope[0]))
                .build();
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(securityReference))
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<AuthorizationScope> scopes(){
        return Arrays.asList(new AuthorizationScope("READ","Acesso de leitura"),
                new AuthorizationScope("WRITE","Acesso de escrita"));
    }

    /**
     * STATUS GLOBAIS CODE??S PARA VERBO GET
     * 406 (Not Acceptable)
     * 500 (Internal Server Error)
     * @return List<ResponseMessage>
     */
    private List<ResponseMessage> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        // STATUS 500
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Erro interno do servidor")
                        // referenciando o modelo da resposta com a class Probrlem
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        // STATUS 406
                        .code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
                        .build()
        );
    }

    /**
     * STATUS GLOBAIS CODE??S PARA VERBO POST e PUT
     * 400 (Bad Request)
     * 406 (Not Acceptable)
     * 415 (Unsupported Media Type)
     * 500 (Internal Server Error)
     * @return List<ResponseMessage>
     */
    private List<ResponseMessage> globalPostPutResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        // STATUS 400
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Recurso n??o encontrado")
                        // referenciando o modelo da resposta com a class Probrlem
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        // STATUS 406
                        .code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
                        .build(),
                new ResponseMessageBuilder()
                        // STATUS 415
                        .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                        .message("Requisi????o recusada porque corpo est?? em um formato n??o suportado")
                        // referenciando o modelo da resposta com a class Probrlem
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        // STATUS 500
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Erro interno do servidor")
                        // referenciando o modelo da resposta com a class Probrlem
                        .responseModel(new ModelRef("Problema"))
                        .build()
        );
    }

    /**
     * STATUS GLOBAIS CODE??S PARA VERBO DELETE
     * 400 (Bad Request)
     * 500 (Internal Server Error)
     * @return List<ResponseMessage>
     */
    private List<ResponseMessage> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        // STATUS 400
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Recurso n??o encontrado")
                        .build(),
                new ResponseMessageBuilder()
                        // STATUS 500
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Erro interno do servidor")
                        .build()
        );
    }

    public ApiInfo apiInfoV1() {
        return new ApiInfoBuilder()
                        .title("AlgaFood API (Depreciada)")
                        .description("API aberta para clientes e restaurantes.<br>" +
                                "<strong>Essa vers??o da API est?? depreciada e deixar?? de existir a partir de 15/10/2023, " +
                                "use a ??ltima vers??o da API.")
                        .version("1")
                        .contact(new Contact(
                                "Alex de Carvalho",
                                "https://www.meusite.com.br",
                                "meuemail@meusite.com.br"
                                ))
                    .build();
    }

    // Mapeamento para servir arquivos est??ticos de arquivos SpringFox Swagger UI.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Definindo caminho do SwaggerUi
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        // mapeamento do webjars que cont??m as imagens e outros arquivos css
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
