# Tópicos abordados

<!-- TOC -->
* [Treinamento Algaworks Especialista em REST (40% do Curso concluído)](#treinamento-algaworks-especialista-em-rest--40-do-curso-concluído-)
    * [Rodar projeto via line command](#rodar-projeto-via-line-command)
      * [WINDOWS](#windows)
      * [LINUX](#linux)
    * [Variável _`serialVersionUID`_ e sua importância na arquitetura Java](#variável-serialversionuid-e-sua-importância-na-arquitetura-java)
      * [O que é o serialVersionUID?](#o-que-é-o-serialversionuid)
  * [Oque é Predicado?](#oque-é-predicado)
  * [Como implementar consultas complexas que tem mais de uma propriedade](#como-implementar-consultas-complexas-que-tem-mais-de-uma-propriedade)
    * [consulta usando `Predicate`](#consulta-usando-predicate)
  * [Como serealizar o tipo Page do zero](#como-serealizar-o-tipo-page-do-zero)
* [CONCEITOS](#conceitos)
  * [Oque é REST e RESTful?](#oque-é-rest-e-restful)
  * [Conhecendo as constraints do REST](#conhecendo-as-constraints-do-rest)
  * [Diferença entre REST e RESTful](#diferença-entre-rest-e-restful)
      * [REST:](#rest-)
      * [RESTful ou REST API:](#restful-ou-rest-api-)
  * [Medindo a maturidade de sua (API) - "Richardson Maturity Model" (RMM)](#medindo-a-maturidade-de-sua--api-----richardson-maturity-model---rmm-)
    * [Dícas](#dícas)
    * [Como implementar Verbo PATCH de forma dinâmica porém de forma não amigável.](#como-implementar-verbo-patch-de-forma-dinâmica-porém-de-forma-não-amigável)
      * [Como evitar erros de NullPointerException usando o Optional lançado no Java 8](#como-evitar-erros-de-nullpointerexception-usando-o-optional-lançado-no-java-8)
      * [Propósitos dos Optional](#propósitos-dos-optional)
        * [Principais Métodos](#principais-métodos)
    * [Como Fazer backup MySQL com dump](#como-fazer-backup-mysql-com-dump)
    * [Como usar injeção de dependência de biblioteca externa no projeto](#como-usar-injeção-de-dependência-de-biblioteca-externa-no-projeto)
    * [Como reparar Flyway via linha de comando](#como-reparar-flyway-via-linha-de-comando)
  * [Como Implementar **Exception** global para usar em todo o projeto usando Spring](#como-implementar-exception-global-para-usar-em-todo-o-projeto-usando-spring)
  * [Como usar ExceptionUtils.getRootCause(ex) para retornar a causa da exception](#como-usar-exceptionutilsgetrootcause--ex--para-retornar-a-causa-da-exception)
  * [Como customizar mensagens usando o Resource Bundle do Bean Validation](#como-customizar-mensagens-usando-o-resource-bundle-do-bean-validation)
  * [Como criar uma validação específica usando annotattion](#como-criar-uma-validação-específica-usando-annotattion)
  * [Como criar uma validação específica para FileUpload arquivos de uploads](#como-criar-uma-validação-específica-para-fileupload-arquivos-de-uploads)
    * [Criar controller `RestauranteProdutoFotoController`](#criar-controller-restauranteprodutofotocontroller)
    * [Criar dto input](#criar-dto-input)
    * [Criar annotation `FileSize`](#criar-annotation-filesize)
    * [Criar validator `FileSizeValidator`](#criar-validator-filesizevalidator)
    * [Criar annotattion `FileContentType`](#criar-annotattion-filecontenttype)
    * [Criar validator `FileContentTypeValidator`](#criar-validator-filecontenttypevalidator)
    * [Criar mensagens para os campos de upload no arquivo `messages.properties`](#criar-mensagens-para-os-campos-de-upload-no-arquivo-messagesproperties)
    * [Informação importante, existe uma falha no sprint quando se usa uma constraint existente que estpa sendo](#informação-importante-existe-uma-falha-no-sprint-quando-se-usa-uma-constraint-existente-que-estpa-sendo)
  * [Boas praticas de data e hora](#boas-praticas-de-data-e-hora)
  * [Como criar filtros dinâmicos usando biblioteca Squiggly conforme propriedades do model](#como-criar-filtros-dinâmicos-usando-biblioteca-squiggly-conforme-propriedades-do-model)
  * [Como validar parâmetros de um recurso do tipo pdf, evitando erro 406](#como-validar-parâmetros-de-um-recurso-do-tipo-pdf-evitando-erro-406)
  * [Como salvar binários na base de dados, como um arquivo, existe algumas formas de fazer isso que é exemplificado abaixo.](#como-salvar-binários-na-base-de-dados-como-um-arquivo-existe-algumas-formas-de-fazer-isso-que-é-exemplificado-abaixo)
    * [Exemplo-1, como salvar arquivo binário usando base64](#exemplo-1-como-salvar-arquivo-binário-usando-base64)
    * [Exemplo-2, como salvar arquivo binário de forma nativa usando multipart/form-data](#exemplo-2-como-salvar-arquivo-binário-de-forma-nativa-usando-multipartform-data)
  * [O problema do lazy loading com @OneToOne](#o-problema-do-lazy-loading-com-onetoone)
    * [1º Solução, Utilizando @MapsId para fazer da FK uma PK também](#1º-solução-utilizando-mapsid-para-fazer-da-fk-uma-pk-também)
    * [2º Solução é a forma comum que as pessoas implementam](#2º-solução-é-a-forma-comum-que-as-pessoas-implementam)
  * [Como usar API Amazon S3 para salvar objetos](#como-usar-api-amazon-s3-para-salvar-objetos)
  * [Soluções para envio de e-mails transacionais](#soluções-para-envio-de-e-mails-transacionais)
  * [Como implementar eventos](#como-implementar-eventos)
  * [Oque é CORS e como evitar bloqueio de CORS](#oque-é-cors-e-como-evitar-bloqueio-de-cors)
  * [Caches HTTP Request](#caches-http-request)
  * [ETags](#etags)
    * [Status da requisição](#status-da-requisição)
      * [Como implementar cache no Spring](#como-implementar-cache-no-spring)
      * [Diretivas de Cache-Control na resposta HTTP](#diretivas-de-cache-control-na-resposta-http)
      * [Deep Etags](#deep-etags)
  * [Como fazer documentação usando a especificação OpenApi](#como-fazer-documentação-usando-a-especificação-openapi)
  * [Como Ativar a compresão de resposta http para diminuir os bytes e deixando mais rápida a requisição](#como-ativar-a-compresão-de-resposta-http-para-diminuir-os-bytes-e-deixando-mais-rápida-a-requisição)
* [ATUALIZAÇÕES](#atualizações)
    * [18.15. Referenciando modelo de representação de problema com códigos de status de erro](#1815-referenciando-modelo-de-representação-de-problema-com-códigos-de-status-de-erro)
    * [18.22. Desafio: descrevendo documentação de endpoints de cozinhas](#1822-desafio--descrevendo-documentação-de-endpoints-de-cozinhas)
    * [18.24. Desafio: descrevendo documentação de endpoints de formas de pagamento](#1824-desafio--descrevendo-documentação-de-endpoints-de-formas-de-pagamento)
    * [18.25. Descrevendo parâmetros globais em operações](#1825-descrevendo-parâmetros-globais-em-operações)
* [SPRING HATEOAS](#spring-hateoas)
  * [O que é Spring HATEOAS?](#o-que-é-spring-hateoas)
  * [Hypermedias com Spring HETEOAS](#hypermedias-com-spring-heteoas)
  * [Paginação com Spring HATEOAS](#paginação-com-spring-hateoas)
* [RETROCOMPATIBILIDADE VS QUEBRA DE COMPATIBILIDADE](#retrocompatibilidade-vs-quebra-de-compatibilidade)
* [VERSIONAMENTO DE API's](#versionamento-de-apis)
  * [versionamento da API por Media Type](#versionamento-da-api-por-media-type)
    * [Como definir MediaType de versionamento](#como-definir-mediatype-de-versionamento)
  * [versionamento da API por URI](#versionamento-da-api-por-uri)
* [LOG](#log)
  * [Configurar o appender do Loggly no Logback](#configurar-o-appender-do-loggly-no-logback)
    * [Como criar um Appender para log](#como-criar-um-appender-para-log)
  * [Serviço log nuvem](#serviço-log-nuvem)
* [SEGURANÇA REST APIs](#segurança-rest-apis)
    * [Definiçõe de papéis "Roles" OAuth2](#definiçõe-de-papéis--roles--oauth2)
    * [Fluxo de Resource Owner Password Credentials Grant (Não é o mais seguro)](#fluxo-de-resource-owner-password-credentials-grant--não-é-o-mais-seguro-)
* [CONHECIMENTOS DIVERSOS](#conhecimentos-diversos)
  * [PROXY](#proxy)
    * [Oque é e como funciona Proxy](#oque-é-e-como-funciona-proxy)
    * [Proxy reverso](#proxy-reverso)
    * [Proxy para todos](#proxy-para-todos)
* [LINKS DE DOCUMENTAÇÕES](#links-de-documentações)
<!-- TOC -->

# Treinamento Algaworks Especialista em REST (40% do Curso concluído)

Descrevi em tópicos o que aprendi no curso e também pesquisando na _internet_, como o curso é amplo dexei apenas informações que não conhecia ou não lembrava e até mesmo informação que conhecia, porém, de uma abordagem diferente.

### Rodar projeto via line command

#### WINDOWS
````shell
./mvnw clean spring-boot:run
````
#### LINUX
````shell
sudo mvn clean spring-boot:run
````

### Variável _`serialVersionUID`_ e sua importância na arquitetura Java

Para compreender sobre a váriável _`serialVersionUID`_ é nescessário comprender alguns conceitos funcionalidades.

**Oque é serializar um objeto ou serialização**

**Serializar** um objeto, dentro da plataforma **Java**, significa converter o estado atual dele em outro formato 
"**padrão**" por exemplo: imagem, json, pdf... e depois disponibilizá-lo em um stream de _bytes_ que poderá ser escrito
em disco ou transmitido.

**Cenários comuns para o uso do mecanismo de serialização**, dentro do ecossistema Java, são as invocações de métodos 
remotos (**RPC**) e também na replicação de **sessões** dos servidores web ou de aplicação.

**Classes que fazem uso do Serializable**: _wrappers_ juntamente com os primitivos - "_esses não implementam, pois, 
não são classes, mas podem ser serializados_".

#### O que é o serialVersionUID?

Esse é um atributo utilizado para controlar explicitamente a compatibilidade entre o **.class** usado para serializar e o **.class** que será utilizado na desserialização.

**Porque e quando usar?**: O controle é necessário porque um **.class** pode ter sofrido alterações e ainda assim se manter compatível com sua versão anterior.

Caso você não informe o atributo _serialVersionUID_, o Java o fará por você na hora em que for compilar e para gerar o valor de _serialVersionUID_ são levados em consideração alguns aspectos de estrutura da classe:

* **Nome da classe e seus modificadores**;
* **Nomes das interfaces que a classe implementa**;
* **Atributos e seus modificadores**;
* **Outros**: podem ser conferida na [documentação conferir na documentação Oracle JAVASE 8](https://docs.oracle.com/javase/8/docs/platform/serialization/spec/class.html#a4100)
* **Como evitar erro Exception por causa do _serialVersionUID_**: Sempre que for usado o implements Serializable deve ser declarado o valor do _serialVersionUID_ para evitar Exception no futuro: **java.io.InvalidClassException**, este erro acontece por:

Imagine que foi adicionado um novo atributo a classe:

> Basicamente quando não informamos de forma explícita o valor do `serialVersionUID` o Java faz isso de forma implícita,
> porém é aqui que está o problema, quando adicionamos um novo atributo a classe esse valor é atualizado devido o 
Java usar os atributos da classe para gerar um
> Hash explícito ao `serialVersionUID`, porém ele cria isso como se fosse uma nova versão do arquivo e no momento de 
deserializar esse arquivo com a versão antiga não vai conseguir porque esta em
> uma versão diferente da primeira. Existe duas maneiras de resolver esse problema:

**1º A mais simples foi descrito no tópico acima**: É adicionar de forma explícita o _serialVersionUID_, assim que for 
adicionado o implements Serializable tópico: Como evitar erro Exception por causa do _serialVersionUID_;

**2ª Descubir qual era o valor _serialVersionUID que o Java tinha atribuido para classe**:

Descoberto o valor de serialVersionUID na versão antiga da classe, então, basta declará-lo explicitamente na nova 
versão para conseguir desserializar os objetos mais antigos:

````shell
$ serialver -classpath :target/artigo-java-serialVersionUID-1.0-SNAPSHOT.jar com.meudominio.serialversionuid.exemplo.NomeClasse
// RETORNO DO CONSOLE:
com.meudominio.serialversionuid.exemplo.NomeClasse: private static final long serialVersionUID = -3969352858203924755L;
````

## Oque é Predicado?

No Java 8, o Predicate é uma interface funcional e, portanto, pode ser usado como destino de atribuição para uma 
expressão lambda ou referência de método.

Na matemática, um predicado é comumente entendido como uma função de valor booleano 'P: X? {true, false}' , 
chamada de predicado em X. Informalmente, um forte.
Pode ser pensado como um operador ou função que retorna um valor que é true ou false.

## Como implementar consultas complexas que tem mais de uma propriedade

### consulta usando `Predicate`

1. **Criar classe PedidoFilter**
2. **Extender o `JpaSpecificationExecutor<Pedido>` na classe `PedidoRepository`**
3. **Implementar o conceito de especification em `PedidoSpecs`**
4. **Implementar o a busca dos parâmetros no Controller**

1-Criar class para o filtro Dto, class que representa os filtro que precisamos especificar, representa as propriedades que desejo fazer uma consulta.

Quando usar pesquisa por data e hora de passar a annotation `@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)`
para evitar erro de formatação de data e hora o spring não consegue reconhecer o formato string
````shell
ERRO: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'pedidoController': Lookup method resolution failed; nested exception is java.lang.IllegalStateException: Failed to introspect Class [pedido.com.algaworks.algafood.api.v1.controller.PedidoController] from ClassLoader [org.springframework.boot.devtools.restart.classloader.RestartClassLoader@2643d422]
````

````java
@Getter
@Setter
public class PedidoFilter {

    private Long clienteId;
    private Long restauranteId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoInicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoFim;
}
````

2-Sera necessário extender o `JpaSpecificationExecutor<Pedido>` para o `PedidoRepository`

````java
@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {

    Optional<Pedido> findByCodigo(String codigo);

    @Override
    @Query("from Pedido p " +
            "join fetch  p.cliente " +
            "join fetch  p.restaurante r " +
            "join fetch  r.cozinha")
    List<Pedido> findAll();
}
````

3-Implementar consulta

````java
public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
        return (root, query, builder) -> {

            // if usado para validar se é um page, para evitar erro count,
            // o spring tenta fazer um count no fetch, porém é impossível fazer isso no sql
            // dessa forma deve ser válidado o queryResulType, para saber o resultado da query, se for
            // count não deixar entrar no if
            if (Pedido.class.equals(query.getResultType())) {
                // Resolvendo problema do n+1, que nada mais é que fazer várias consultas na base de dados,
                // mas passando o root.fetch vai retornar o resultado em um único sql, usando api do Criteria
                root.fetch("restaurante").fetch("cozinha");
                root.fetch("cliente");
            }

            var predicates = new ArrayList<Predicate>();
            //adicionar predicates no arraylist
            if (filtro.getClienteId() != null) {
                predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
            }
            if (filtro.getRestauranteId() != null) {
                predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
            }
            if (filtro.getDataCriacaoInicio() != null) {
                // data maior ou igual a data passada no parâmetro
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            }
            if (filtro.getDataCriacaoFim() != null) {
                // data menor ou igual a data passada no parâmetro
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
````

4-implemetar no controller para receber os parâmetros via url exemplo `{{URL}}/pedidos?restauranteId=10&clienteId=10`

````java
    //@GetMapping
    public List<PedidoResumoModel> pesquisar(PedidoFilter filtro) {
        List<Pedido> todosPedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro));
        return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
    }
````

para mais informações pesquisar pelo módulo Spring data JPA, que explica os conceitos e como funciona Criteria, Especification e outros.


## Como serealizar o tipo Page do zero

- Criar class `PageJsonSerializer` para representar o serialiozador de paginas para todos os recursos que usarem Page
- Criar class `PageableTranslator` para converter os parametros personalizados
- Criar método `traduzirPageable` dentro do `PedidoController` para fazer conversão dos parâmetros
- Atualizar método de `pesquisar` da classe `PedidoController`

````java
@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {

    /**
     * Método que pode ser chamado para solicitar a implementação
     * para serializar valores do tipo que esse serializador manipula.
     *
     * @param value       Valor a ser serializado; não pode ser nulo.
     * @param gen         Gerador usado para gerar o conteúdo Json resultante
     * @param serializers O provedor que pode ser usado para obter serializadores para
     *                    serializar o valor de objetos contém, se houver.
     */
    @Override
    public void serialize(Page<?> page, JsonGenerator gen, SerializerProvider serializers) throws IOException {

      gen.writeStartObject();  // Iníciar objeto

      // escrever pra mim uma propriedade de objeto, o nome dessa propriedade
      // é content eo conteudo dela é page.getContent()
      gen.writeObjectField("content", page.getContent());

      // CONFIGURAR AS PROPRIEDADES DA PÁGINA

      // tamanho da página, quantos elementos deve mostrar por página
      gen.writeNumberField("size", page.getSize());

      // total de elementos encontrado na base de dados
      gen.writeNumberField("totalElements", page.getTotalElements());

      // total de páginas
      gen.writeNumberField("totalPages", page.getTotalPages());

      // qual pagina que esta acessando atualmente
      gen.writeNumberField("number", page.getNumber());

      gen.writeEndObject();  // Finalizar objeto
    }
}
````

````java
public class PageableTranslator {

    public static Pageable translate(Pageable pageable, Map<String, String> fieldsMapping) {

        var ordes = pageable.getSort().stream()
                // filter valida se a proprieadade não existe e ignora ela
                .filter(order -> fieldsMapping.containsKey(order.getProperty()))
                .map(order -> new Sort.Order(order.getDirection(), fieldsMapping.get(order.getProperty())))
                .collect(Collectors.toList());

        return PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(ordes));
    }
}
````
Criar método dentro do `PedidoController` para fazer conversão dos parâmetros
````java
    private Pageable traduzirPageable(Pageable apiPagable) {

        var mapeamento = ImmutableMap.of(
        "codigo","codigo",
        "restaurante.nome","restaurante.nome",
        "nomeCliente","cliente.nome",
        "valorTotal","valorTotal",
        "valorTotal","valorTotal"
        );

        return PageableTranslator.translate(apiPagable, mapeamento);
        }
````

Atualizar método de `pesquisar` da classe `PedidoController`
````java
@GetMapping
    public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro,  @PageableDefault(size = 10)  Pageable pageable) {

        traduzirPageable(pageable);

        Page<Pedido> pedidoPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);

        List<PedidoResumoModel> pedidoResumoModel = pedidoResumoModelAssembler.toCollectionModel(pedidoPage.getContent());

        Page<PedidoResumoModel> pedidoResumoModelPage = new PageImpl<>(pedidoResumoModel, pageable, pedidoPage.getTotalElements());

        return pedidoResumoModelPage;
    }
````


# CONCEITOS

## Oque é REST e RESTful?

Existem 2(dois) tipos de desenvolvedores que usam REST:

- **Puristas:** São os que seguem a rísca as constraints do RESTFUL
- **Pragmáticos:** São os que seguem algumas contrainsts

## Conhecendo as constraints do REST

_Constraints_ é uma restrição há algo que limita ou controla o que você pode fazer, abaixo esta listado as constraints 
do padrão REST

— **Cliente-servidor:** devem poder evoluir separadamente sem qualquer dependência entre elas.

— **Stateless:** Sem estador, aplicação não deve possuir estado, resumindo a requisição feita ao servidor deve conter 
tudo que precisa para ser processado, o servidor não pode manter sessão nele mesmo, isso significa que o servidor não 
manterá o histórico de uso;

— **Cache:** API pode fazer caches das requisições ao servidor ao fazer uma nova requisição? A requisição para o mesmo 
endpoint pode ser configurada para buscar no cache, melhorando a escalabilidade e desempenho, sitando alguns exemplos: 
nomes de cidades, endereços, enfim esses dados que dificilmente sofreram alterações.

— **Interface** Uniforme: Conjunto de operações bem definidas no sistema, uma vez definida deve seguir isso como 
verdade total;

— **Sistema em camadas:** Entre o cliente que prove a API e o cliente que consome API existem outros servidores 
no meio que fornecem outras camadas como segurança, cache, balanceamento de carga, etc.

— **Código sob demanda:** Opcional, o servidor pode enviar como resposta algum código que pode ser executado no 
cliente, porém é muito pouco usado.

## Diferença entre REST e RESTful

#### REST:

É o estilo cultural que possui as **constraints**, mencionadas acima:

- Cliente-servidor (Client-Server);
- Stateless;
- Cache (Cacheable);
- Interface Uniforme (Uniform Interface);
- Sistema em camadas (Layered system);

#### RESTful ou REST API:

É uma API desenvolvida em conformidade com as constraints, resumindo é uma API que segue todas as constraints 
obrigatórias, caso seja violado alguma constraint não deve ser reconhecida ou trata como ``RESTful``.

## Medindo a maturidade de sua (API) - "Richardson Maturity Model" (RMM)

Quem criou o modelo de Maturidade foi _Richardson_ e para saber se uma API é REST, faça uma pergunta: **-Em qual nível 
de maturidade de acordo com Richardson, foi desenvolvida API?**

- **Nível 3**: "Level Three Services" `HATEOAS` Último nível considerado a glória do REST, na teoria apenas o nível 
3 deve ser considerado como REST de acordo com [Roy Fielding](https://roy.gbiv.com/) Cientista de computação

  **Explicação:** O nível 3(três) de maturidade faz o uso eficiente dos três fatores. **URIs**, **HTTP** e **HATEOAS**.
- **Nível 2**: "Level Two Services" Verbos HTTP -> Apesar dos desenvolvedores purista não considerarem REST, porém a 
maioria dos desenvolvedores nomearam sendo REST

  **Explicação:** O nível 2(dois) de maturidade faz o uso eficiente de URIs e verbos HTTP. Nos níveis anteriores o 
protocolo HTTP estava sendo usado superficialmente.

  Neste nível a API suporta os diversos verbos **HTTP**:

  - **POST** - Criar
  - **GET** - Ler
  - **PUT** - Atualizar
  - **DELETE** - Excluir
  - **PATCH** - Atualizar parcialmente
  - **Nível 1**: "Level One Services" Recursos -> Não é consideado como REST.

    **Explicação:**

    -O nível 1(um) de maturidade já considera a utilização eficiente de URIs.

    -Os recursos são mapeados, mas ainda não emprega o uso eficiente dos verbos.

    -Geralmente utilizam apenas GET e POST.
  - **Nível 0**: "Level Zero Services" POX -> Não é considerado como REST.

    **Explicação:**

    -O nível 0(zero) de maturidade não utiliza recursos de URI, HTTP Methods e HATEOAS.

    -A API têm um único URI e usa um único método HTTP (normalmente POST).

### Dícas

### Como implementar Verbo PATCH de forma dinâmica porém de forma não amigável.

O exemplo a seguir representa um PATCH para classe de Restaurante.

Criar uma classe Controller e adiciona o método que será implementado o PATCH e como usar **_Reflections do Spring_**
**Implementado no commit:** 4.34. Finalizando a atualização parcial com a API de Reflections do Spring

```java
@PatchMapping("/{restaurantid}")
public ResponseEntity<?> atualizarParcial(@PathVariable Long restaurantid,
@RequestBody Map<String, Object> campos) {
		Restaurante restauranteAtual = crudRestauranteServide.porId(restaurantid);
		if (restauranteAtual == null ) {
			return ResponseEntity.notFound().build();
		}
		merge(campos, restauranteAtual);
		return atualizar(restaurantid, restauranteAtual);
	}
```

Criar método merge onde será aplicada toda a regra de comparação dos campos atualizados sem quebrar tipagem

```java

private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
  ObjectMapper objectMapper = new ObjectMapper();
  // Estamos dizendo aqui objectMapper convert pra mim dados de Origem em um objecto do tipo Restaurante
  // criando uma instância restauranteOrigem
  Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
  
  dadosOrigem.forEach((String nomePropriedade, Object valorPropriedade) -> {
    // findField Retorna instância de um campo
    Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);

    // É necessário setar o field como true devido as propriedades de restaurante serem declaradas como private
    field.setAccessible(true);
  
    // getField retorna o valor da propriedade representado por field
    Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

    System.out.println("+------------------------- PROPRIEDADES PATH ---------------------------------+");
    System.out.println("| Informação data/hora: "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));
    System.out.println("| Nome propriedade: "+nomePropriedade+ " Valor propriedade: "+valorPropriedade);
    System.out.println("| Nome propriedade: "+nomePropriedade+ " Novo Valor propriedade: "+novoValor);
    System.out.println("+-----------------------------------------------------------------------------+");

    ReflectionUtils.setField(field, restauranteDestino, novoValor);
  });
}
```

#### Como evitar erros de NullPointerException usando o Optional lançado no Java 8

#### Propósitos dos Optional

- Evitar erros `NullPointerException`.
- Parar de fazer verificações de valor nulo do tipo `if (cliente != null)`.
- Escrever código mais limpo e elegante.

##### Principais Métodos

**empty** - Retorna uma instância de Optional vazia.

```java
    Optional<Cliente> retorno = Optional.empty();
```

**of** - Retorna um `Optional` com o valor fornecido, mas o valor não pode ser nulo. Se não tiver certeza de que o valor não é nulo use `Optional.ofNullable`.

```java
   Optional<Cliente> retorno = Optional.of(buscaCliente(cpf));
```

**ofNullable** - Se um valor estiver presente, retorna um Optional com o valor, caso contrário, retorna um `Optional` vazio. Este é um dos métodos mais indicados para criar um `Optional`.

```java
   Optional<Cliente> retorno = Optional.ofNullable(buscaCliente(cpf));
```

**filter** - Se um valor estiver presente e o valor corresponder ao predicado retorna um `Optional` com o valor, se não, retorna um `Optional` vazio.

```java
   Optional<Cliente> retorno = buscaCliente(cpf).filter(cliente -> !cliente.getNome().isEmpty());
```

**isPresent** - Se um valor estiver presente retorna `true`, se não, retorna `false`.

```java
   Optional<Cliente> retorno = Optional.ofNullable(buscaCliente(cpf));
        if (retorno.isPresent()) {
            System.out.println("Cliente encontrado.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
```

**get** - Se um valor estiver presente retorna o valor, caso contrário, lança `NoSuchElementException`. Então para usar `get` é preciso ter certeza de que o `Optional` não está vazio.

```java
   Optional<Cliente> retorno = Optional.ofNullable(buscaCliente(cpf));
        if (retorno.isPresent()) {
            Cliente cliente = retorno.get();
        }
```

**ifPresent** - Se um valor estiver presente, executa a ação no valor, caso contrário, não faz nada.

```java
   public void login(String cpf, String senha) {
        dao.buscaPorCPF(cpf).
        ifPresent(cliente -> cliente.realizaLogin(senha));
   }
```

**map** -  Se um valor estiver presente retorna um `Optinal` com o resultado da aplicação da função de mapeamento no valor, caso contrário, retorna um `Optional` vazio.

```java
   if (optCliente.isPresent()) {
        String nome = optCliente.map(Cliente::getNome);
   }
```

**flatMap** -  Semelhante a `map`, se um valor estiver presente, retorna o resultado da aplicação da função de mapeamento no valor, caso contrário retorna um `Optional` vazio. A diferença é que `flatMap` pode se aplicado a um mapeamento que também retorna um `Optional`.

```java
   Endereco endereco = buscaCliente(cpf).flatMap(Cliente::getEndereco).get();
```

### Como Fazer backup MySQL com dump

Para fazer um dump (backup dos dados) database "nomebanco" no MySQL, use o seguinte comando:

```sql
mysqldump --host localhost --user root --password --databases nomebanco > dump-nomebanco.sql
```

Para criar o database "nomebanco" a partir do dump, execute o comando:

```sql
mysql --host localhost --user root --password < dump-nomebanco.sql
```

### Como usar injeção de dependência de biblioteca externa no projeto

Deve ser criado um pacote para adicionar essas classes externas, em nosso exemplo foi
criado o package `core` e adiciona todas as bibliotecas externas como `SimpleModule` da biblioteca jackson,
`LocalValidatorFactoryBean` para as nossas validações de propriedades e `ModelMapper` com ele é possível mapear modelos
complexos, com nenhuma ou poucas configurações - sempre seguindo convenções.
As classe que instância essas blibliotecas e framework devem ser anotadas com `@Configuracion`.

````java
package com.algaworks.algafood.core.modelmapper;

import endereco.com.algaworks.algafood.api.v1.model.EnderecoModel;
import com.algaworks.algafood.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper () {
        var modelMapper = new ModelMapper();

        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
                Endereco.class, EnderecoModel.class);

        enderecoToEnderecoModelTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

        return modelMapper;
    }
}
````

### Como reparar Flyway via linha de comando

Primeiro deve ser criado um arquivo flyway.properties e dentro dele conforme exemplo abaixo

```properties
flyway.url=jdbc:mysql://localhost:3306/nomedobanco?createDatabaseIfNotExist=true&serverTimezone=UTC&enabledTLSProtocols=TLSv1.2
flyway.user=root
flyway.password=123456
```

Executar comando

````shell
./mvnw flyway:repair -Dflyway.configFiles=src/main/resources/flyway.properties
````

## Como Implementar **Exception** global para usar em todo o projeto usando Spring

No Spring é muito fácil de implementar uma classe Exception global, que serve de base para todo o projeto, basta usar
a annotattion ``@ControllerAdvice`` segue abaixo o exemplo de como implementar:

````java
@ControllerAdvice //Define que todas as exception do projeto serão tratadas por aqui
public class ApiExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensage(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e) {
        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensage(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
    }
}
````

## Como usar ExceptionUtils.getRootCause(ex) para retornar a causa da exception

Para retornar a causa da exception primeiro deve ser adicionado no pom.xml a dependência do `org.apache.commons`, com ele
será possível capturar a causa raíz.

```xml
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-lang3</artifactId>
    </dependency>
```

Como implementar

````java
@ControllerAdvice //Define que todas as exception do projeto serão tratadas por aqui
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        if(rootCause instanceof InvalidFormatException) {
            // chamar método espcialista em tratar esse tipo InvalidFormatException
            return handleInvalidFormatException((InvalidFormatException)rootCause, headers, status, request);
        }
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisição está inválido. Verifique o erro de sintaxe.";
        Problem problem = createProblemBuilder(status, problemType, detail).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }
}
````

## Como customizar mensagens usando o Resource Bundle do Bean Validation

Erros que podem acontecer durante o desenvolvimento das mensagens.
-nome precisa ser messages.properties para substituir o valor padrão das mensagens.

criar arquivo `messages.properties`s e adiconar a mensagen de forma global ou espefífica para cada propriedade

````properties
NotBlank={0} é obrigatório
NotNull={0} é obrigatório
PositiveOrZero={0} deve ser um valor maior ou igual a zero

Multiplo={0} Deve ser um valor múltipo de {1}
TaxaFrete.invalida={0} está inválida, informe um valor positivo
ValorZeroIncluirDescricao={1} deve conter {2}

# Cozinha
cozinhaIdInput.nome=Nome da cozinha
cozinhaIdInput.id=Código da cozinha

# Restaurante
restaurante.id=Código do restaurante
NotNull.restauranteInput.taxaFrete={0} é obrigatória
NotNull.restauranteInput.cozinha={0} é obrigatória
restauranteInput.nome=Nome do restaurante
restauranteInput.cozinha=Cozinha do restaurante
## neste caso sera pego o valor restaurante.taxaFrete + TaxaFrete.invalida
## Resultado = Taxa de frete do restaurante está inválida, informe um valor positivo
restauranteInput.taxaFrete=Taxa de frete do restaurante

# Estado
estadoInput.nome=Nome do estado
estadoInput.id=Código do estado

# Cidade
cidadeInput.nome=Nome da cidade
cidadeInput.estado=Estado da cidade

# Formas de pagamento
formaPagamento.id=Código da forma de pagamento
NotBlank.formaPagamentoInput.descricao={0} é obrigatória
formaPagamentoInput.id=Código da forma de pagamento
formaPagamentoInput.descricao=Descrição da forma de pagamento

# Endereço
endereco=Endereço
endereco.logradouro=Logradouro
endereco.numero=Número do logradouro
endereco.cidade=Cidade
endereco.cidade.id=Código da cidade
endereco.cep=CEP
endereco.bairro=Bairro

# Grupo
grupoInput.nome=Nome do grupo

# Usuário
NotBlank.usuarioComSenhaInput.senha={0} é obrigatória
usuarioComSenhaInput.nome=Nome do usuário
usuarioComSenhaInput.email=E-mail do usuário
usuarioComSenhaInput.senha=Senha do usuário

usuarioInput.nome=Nome do usuário
usuarioInput.email=E-mail do usuário

NotBlank.senhaInput.senhaAtual={0} é obrigatória
NotBlank.senhaInput.novaSenha={0} é obrigatória
senhaInput.senhaAtual=Senha atual
senhaInput.novaSenha=Nova senha

# Pedido
NotNull.pedidoInput.itens.produtoId=Código do produto no item é obrigatório
NotNull.pedidoInput.itens.quantidade=Quantidade no item é obrigatório
Size.pedidoInput.itens=O pedido deve ter pelo menos um item
pedidoInput.restaurante=Restaurante
pedidoInput.formaPagamento=Forma de pagamento
pedidoInput.enderecoEntrega=Endereço de entrega
pedidoInput.itens=Itens do pedido
enderecoEntrega.logradouro=Logradouro do endereço de entrega
enderecoEntrega.numero=Número do logradouro do endereço de entrega
enderecoEntrega.cidade=Cidade do endereço de entrega
enderecoEntrega.cidade.id=Código da cidade do endereço de entrega
enderecoEntrega.cep=CEP do endereço de entrega
enderecoEntrega.bairro=Bairro do endereço de entrega

````

## Como criar uma validação específica usando annotattion

vamos usa como exemplo annotattion `@TaxaFrete`

````java
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE }) // onde pode ser usada
@Retention(RUNTIME) // executada em tempo de execuçaõ
@Constraint(validatedBy = { }) // precisar definir a anotação com constraint
@PositiveOrZero // Não é obrigatório mas pode ser usado uma annotattion já existente
public @interface TaxaFrete {

    @OverridesAttribute(constraint = PositiveOrZero.class, name = "message")
    String message() default "{TaxaFrete.invalida}"; // pega a descrição da propriedade definida messages.properties

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
````

## Como criar uma validação específica para FileUpload arquivos de uploads


### Criar controller `RestauranteProdutoFotoController`

````java
@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

  @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public void atualizarFoto(@PathVariable Long restauranteId,
                            @PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput) {

    var nomeArquivo = UUID.randomUUID().toString()
            + "_" + fotoProdutoInput.getArquivo().getOriginalFilename();

    var arquivoFoto = Path.of("/Users/thiago/Desktop/catalogo", nomeArquivo);

    System.out.println(fotoProdutoInput.getDescricao());
    System.out.println(arquivoFoto);
    System.out.println(fotoProdutoInput.getArquivo().getContentType());

    try {
      fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
````

### Criar dto input

````java
@Getter
@Setter
public class FotoProdutoInput {

    @NotNull
    @FileSize(max="500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile arquivo;

    @NotBlank
    private String descricao;
}
````

### Criar annotation `FileSize`

````java
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { FileSizeValidator.class })
public @interface FileSize {


  String message() default "tamanho do arquivo inválido";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };

  // pega a descrição da propriedade definida messages.properties
  String max();
}
````

### Criar validator `FileSizeValidator`

````java
public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

  private DataSize maxSize;

  @Override
  public void initialize(FileSize constraintAnnotation) {
    this.maxSize = DataSize.parse(constraintAnnotation.max());
  }

  @Override
  public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
    return value == null || value.getSize() <= this.maxSize.toBytes();
  }

}
````

### Criar annotattion `FileContentType`

````java
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { FileContentTypeValidator.class })
public @interface FileContentType {


  String message() default "Tipo de arquivo inválido";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };

  String[] allowed();
}
````

### Criar validator `FileContentTypeValidator`

````java
public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

  private List<String> allowedContentTypes;

  @Override
  public void initialize(FileContentType constraint) {
    this.allowedContentTypes = Arrays.asList(constraint.allowed());
  }

  @Override
  public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
    return multipartFile == null || this.allowedContentTypes.contains(multipartFile.getContentType());
  }

}
````

### Criar mensagens para os campos de upload no arquivo `messages.properties`

````properties
# Foto produto
fotoProdutoInput.arquivo=Arquivo da foto
fotoProdutoInput.descricao=Descrição da foto
FileSize.fotoProdutoInput.arquivo=A foto deve ter um tamanho máximo de {1}
````


### Informação importante, existe uma falha no sprint quando se usa uma constraint existente que estpa sendo

resolvida até a data de hoje 22/20/2022 [issue SPR-15967](https://github.com/spring-projects/spring-framework/issues/20519),
para explicar a falha vou usar o exemplo da annotattion `@TaxaFrete`, perceba que existe uma annotattion `@PositiveOrZero`
e mais abiaxo `@OverridesAttribute(constraint = PositiveOrZero.class, name = "message")`, nesta linha esta dizendo para
fazer um `OverridesAttribute` ou seja sobescreva o atributo `message` da classe `PositiveOrZero.class`, mas não é oque
esta acontecendo na prática mesmo deixando de forma explícita o Spring não consegue aplicar essa regra, para testar
basta acessar o arquivo `messages.properties` deixar comentado a propriedade `#PositiveOrZero` e rodar aplicação e
depois descomentar, vai perceber que ao comentar a propriedade vai aplicar a regra  correta da taxaFrete, porém não vai
conseguir usar a propriedade `PositiveOrZero` de forma customizada.

````properties
# Se descomentar o comentário ele vai aplicar a descrição que está na PositiveOrZero para a taxaFrete 
# resultado (restaurante.taxaFrete + PositiveOrZero) Taxa de frete do restaurante deve ser um valor maior ou igual a zero
#PositiveOrZero={0} deve ser um valor maior ou igual a zero
TaxaFrete.invalida={0} está inválida, informe um valor positivo
## neste caso sera pego o valor restaurante.taxaFrete + TaxaFrete.invalida
## Resultado = Taxa de frete do restaurante está inválida, informe um valor positivo
restaurante.taxaFrete=Taxa de frete do restaurante
````

## Boas praticas de data e hora

* 1-Usar **ISO-8601** pra formatar data e hora é o padrão internacional exemplo: `2019-10-12T14:15:38-03:00` deixa de forma explícita
  que se trata de uma data Brasileira `-03:00`
* 2-Aceite qualquer fuso horário
* 3-Armazene em UTC
* 4-Retorne em UTC, dexei a conversão ser implementada pelo frontend
* 5-Não Inclua o horário se não for necessário

Como converter data timezone usando função de banco `convert_tz`, exemplo de uso
````sql
select date(convert_tz('2019-11-03 02:00:30', '+00:00','-03:00'));
--Resultado
-- 2019-11-02 23:00:30
````

## Como criar filtros dinâmicos usando biblioteca Squiggly conforme propriedades do model

em caso de dúvidas verificar aula _[13.2. Limitando os campos retornados pela API com @JsonFilter do Jackson](https://app.algaworks.com/aulas/2034/limitando-os-campos-retornados-pela-api-com-jsonfilter-do-jackson?pagina=0)_

atenção, precisa ser adicionado a dependência no pom.xml

````xml
<dependency>
  <groupId>com.github.bohnman</groupId>
  <artifactId>squiggly-filter-jackson</artifactId>
  <version>1.3.18</version>
</dependency>
````

deve ser criado um pacote e uma classe de configuração para conseguir injetar a classe nosso pacote vai ser
`core.squiggly` e nossa classe `SquigglyConfig`.

````java
@Configuration
public class SquigglyConfig {

    /**
     * Cadas vez que fizer um filtro será passado por esse método antes de fazer a serealizacion das propriedades no model
     */
    @Bean
    public FilterRegistrationBean<SquigglyRequestFilter> squigglyRequestFilter(ObjectMapper objectMapper) {
        // RequestSquigglyContextProvider, já traz algumas informações sendo a propriedade {fields: ... }
        Squiggly.init(objectMapper, new RequestSquigglyContextProvider());

        var filterRegistration = new FilterRegistrationBean<SquigglyRequestFilter>();
        filterRegistration.setFilter(new SquigglyRequestFilter());
        filterRegistration.setOrder(1);

        return filterRegistration;
    }
}
````

Também sera necessário criar uma classe para criar os encodes da url, já que o tomcat não aceita alguns caracteres com `[]`
criar no pacote `core.squiggly` com nome `TomcatCustomizer`.

````java
// Referências:
// - https://stackoverflow.com/a/53613678
// - https://tomcat.apache.org/tomcat-8.5-doc/config/http.html
// - https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-configure-webserver

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class TomcatCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers(connector -> connector.setAttribute("relaxedQueryChars", "[]"));
    }
}
````

## Como validar parâmetros de um recurso do tipo pdf, evitando erro 406

Para evitar erro 406 basta passar no header no `key = Accept` e no value `application/pdf,application/json` mas é claro que deve existir as validações
a questão aqui é apenas pra dizer para o navegador: "Se não encontrar nada no `application/pdf` tente no `application/json`".
veja a imagem abaixo:
![Exemplo de como validar parâmetros quando for arquivo pdf](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/validar_parametros_pdf.png "header parâmetros")


## Como salvar binários na base de dados, como um arquivo, existe algumas formas de fazer isso que é exemplificado abaixo.

### Exemplo-1, como salvar arquivo binário usando base64

Base64 é um método de codificação de dados principalmente para binários, porém existe desavantagens em usar essa codificação para salvar arquivos,
principalmente quando se trata de vários uploads.

Neste caso é usando `Content-Type: application/json`

| #                                     | Vantagem | Desvantagem |
|---------------------------------------|----------|-------------|
| Desenvolvimento rápido                | X        |             |
| Arquivos pequenos                     | X        |             |
| Poucos download's/upload's            | X        |             |
| Arquivo fica 30% maior que o original |          | X           |
| Passado por Json                      | X        |             |
| Ocupa muita memória (String Giagante) |          | X           |
| Cliente Codificar                     |          | X           |
| Service Decodificar                   |          | X           |


````text
PUT /restaurantes/1/produtos/10/foto
````
Propriedades da imagem
`````json
{
  "nome":   "Nome da imagem",
  "descricao": "Descrição da imagem",
  "arquivo": "código da imagem base64"
}
`````

### Exemplo-2, como salvar arquivo binário de forma nativa usando multipart/form-data

Neste caso é usando `Content-Type: multipart/form-data boundary=XXX` e `Content-Disposition: form-data; name="descricao" filename="nome do arquivo.jpg"` 
oque é boundary? Limites, fronteira... boundary aceita qualquer caracter é ele que vai limitar um campo do outro em nosso caso como esta sendo usando XXX
será representado como limitado `--XXX` no payload

| #                                                         | Vantagem | Desvantagem |
|-----------------------------------------------------------|----------|-------------|
| Desenvolvimento demorado                                  |          | X           |
| Qualquer tamanho arquivo                                  | X        |             |
| Um ou mais download's/upload's                            | X        |             |
| Arquivo tamanho original                                  | X        |             |
| Não oucupa memória fica em um arquivo temporário servidor | X        |             |


````text
PUT /restaurantes/1/produtos/10/foto
Content-Type: multipart/form-data boundary=XXX
````

Propriedades da imagem passado no payload da requisição
`````text
--XXX
Content-Disposition: form-data; name="descricao"
Content-Type: image/jpeg

aqui fica o conteúdo da imagem ou seja o binário

--XXX
Content-Disposition: form-data; name="descricao"

nome do arquivo
--XXX--
`````
## O problema do lazy loading com @OneToOne
### 1º Solução, Utilizando @MapsId para fazer da FK uma PK também

Existe um jeito também para não precisar inverter o owner. Basta utilizar @MapsId, como veremos a seguir e também
implementado no treinamento.

````java
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class FotoProduto {


  @EqualsAndHashCode.Include
  @Id
  @Column(name = "produto_id")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  // usado para declarar que esta sendo usado o mesmo id do produto para fazer relacionamento precisa
  // dessa annotation para o JPA conseguir fazer o mapeamento.
  //    Quando usar essa abordagem só precisa mudar a lógica em vez de usar o produto para chegar na imagem, 
  //    usa imagem para chegar no produto
  @MapsId
  private Produto produto;

  private String nomeArquivo;
  private String descricao;
  private String contentType;
  private Long tamanho;

}
````

### 2º Solução é a forma comum que as pessoas implementam

**Como não fazer**
````java
@Entity
public class Funcionario {
 
    @Id
    private Integer id;
 
    private String nome;
 
    // Non-Owner da relação
    @OneToOne(mappedBy = "funcionario", fetch = FetchType.LAZY)
    private ContaCorrente contaCorrente;
 
    // getters e setters omitidos
 
}
````
**Como fazer** 
Basicamente, você vai trocar o uso mappedBy pelo uso de @JoinColumn ou seja, 
remover o `mappedBy = "funcionario"` e adicionar um `@JoinColumn(name = "conta_corrente_id")`

````java
@Entity
public class Funcionario {
 
    // Passa a ser o owner da relação
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_corrente_id")
    private ContaCorrente contaCorrente;
 
    // As outras propriedades, e todos os getters e setters, foram omitidos
 
}
````

## Como usar API Amazon S3 para salvar objetos

Adicionar propriedades no `application.properties`
````properties
# AMAZON S3 STORAGE
##### as configurações da s3 ficam no profile por isso esta comentado
algafood.storage.s3.id-chave-acesso=*********
algafood.storage.s3.chave-acesso-secreta=********************
algafood.storage.s3.bucket=algafood-test-carvalho
algafood.storage.s3.regiao=us-east-1
algafood.storage.s3.diretorio-fotos=catalogo
````

Adicionar dependências no `pom.xml`

````xml
<dependency>
  <groupId>com.amazonaws</groupId>
  <artifactId>aws-java-sdk-s3</artifactId>
  <version>${aws-java-sdk-s3.version}</version>
</dependency>
````

Criar class de configuração `AmazonS3Config`
````java
  @Configuration
  public class AmazonS3Config {
  
      @Autowired
      private StorageProperties storageProperties;
  
      @Bean
      public AmazonS3 amazonS3() {
          var credentials = new BasicAWSCredentials(
                  storageProperties.getS3().getIdChaveAcesso(),
                  storageProperties.getS3().getChaveAcessoSecreta());
  
          return AmazonS3ClientBuilder
                  .standard()
                      .withCredentials(new AWSStaticCredentialsProvider(credentials))
                      .withRegion(storageProperties.getS3().getRegiao())
                  .build();
      }
  }
````

Adicionar Class de serviço `S3FotoStorageService` que representa crud servidor amazon 
````java
@Service
public class S3FotoStorageService implements FotoStorageService {

  @Autowired
  private AmazonS3 amazonS3;

  @Autowired
  private StorageProperties storageProperties;

  @Override
  public InputStream recuperar(String nomeArquivo) {
    return null;
  }

  @Override
  public void armazenar(NovaFoto novaFoto) {
    try {
      // Preparando Payload para fazer a chamada na API da AMAZON
      String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());
      var objectMetadata = new ObjectMetadata();
      objectMetadata.setContentType(novaFoto.getContentType());

      var putObjectRequest = new PutObjectRequest(
              storageProperties.getS3().getBucket(),
              caminhoArquivo,
              novaFoto.getInputStream(),
              objectMetadata
      ).withCannedAcl(CannedAccessControlList.PublicRead);

      // FAZENDO CHAMADA
      amazonS3.putObject(putObjectRequest);
    } catch (Exception e ){
      throw new StorageException("Não foi possível enviar arquivo para Amazon S3.", e);
    }
  }

  private String getCaminhoArquivo(String nomeArquivo) {
    return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
  }
  @Override
  public void remover(String nomeArquivo) {

  }
}
````

## Soluções para envio de e-mails transacionais

Email, quando acontece alguma coisa no sistema.

Serviços de envio de e-mail
- Amazon SES-Simple Email Service, existe plano free e precisa cadastrar cartão de crédito;
- MAILCHIMP, não existe plano free e precisa cadastrar cartão de crédito; 
- Mailgun, existe plano free e precisa cadastrar cartão de crédito;
- SendGrid, pode enviar até 100 email por dias gratuitamente e não precisa cadastrar cartão de crédito;

Para os Templates do corpo de e-mails usar o Apache FreeMarker
 
## Como implementar eventos

- `extends AbstractAggregateRoot<Pedido>`
````java
public class Pedido extends AbstractAggregateRoot<Pedido> {
    // ...
}
````
- Criar class que representa o evento ex: PedidoConfirmadoEvent
````java
package com.algaworks.algafood.domain.event;

@Getter
@AllArgsConstructor
public class PedidoConfirmadoEvent {
    private Pedido pedido;
}
````
- Registrar o evento
````java
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Pedido extends AbstractAggregateRoot<Pedido> {
  
    // outras propriedades...
  
  public void confirmar() {
    setStatus(StatusPedido.CONFIRMADO);
    setDataConfirmacao(OffsetDateTime.now());
    //Não esta disparando evento apenas registrando
    registerEvent(new PedidoConfirmadoEvent(this));
  }
}
````
- Criar classe que fica escutando o evento e executa ações
````java
package com.algaworks.algafood.domain.listener;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

    @Autowired
    private EnvioEmailService envioEmailService;

    @TransactionalEventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {

        Pedido pedido = event.getPedido();

        var mensagem = EnvioEmailService.Mensagem
                .builder()
                    .assunto(pedido.getRestaurante().getNome() +" - Pedido confirmado")
                    .corpo("pedido-confirmado.html")
                    .variavel("pedido",pedido)
                    .destinatario(pedido.getCliente().getEmail())
                .build();

        envioEmailService.enviar(mensagem);
    }
}
````

## Oque é CORS e como evitar bloqueio de CORS




## Caches HTTP Request
O tipo de cache é definido de acordo com o local em que o conteúdo é armazenado.

- **Cache de browser/local** - esse armazenamento é feito no navegador. Todos os navegadores possuem um armazenamento local, 
que serve normalmente para resgatar recursos previamente acessados. Esse tipo de cache é privado, já que os recursos 
armazenados não são compartilhados.
- **Cache de proxy** - esse armazenamento, também chamado de cache intermediário, é feito no servidor proxy, entre o cliente e
o servidor de origem. Esse é um tipo de cache compartilhado, já que é utilizado por vários clientes, e geralmente é 
mantido por provedores.
- **Cache de gateway** - também chamado de proxy reverso, é uma camada independente, separada, e esse armazenamento fica entre
o cliente e a aplicação. Ele armazena em cache as requisições feitas pelo cliente e as envia para a aplicação e faz o 
mesmo com as respostas, enviando da aplicação para o cliente. Se um recurso for solicitado novamente, o cache retorna a
resposta, antes de chegar à aplicação. Também é um cache compartilhado, mas pelos servidores e não pelos usuários.
- **Cache de aplicação** - esse armazenamento ocorre dentro da aplicação e permite que o desenvolvedor especifique quais 
arquivos o navegador deve armazenar em cache e os disponibilize para usuários mesmo quando estiverem offline.

<img src="https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/img_pages/pmk-769-http-caching-diagram-1.png" title="Como funciona o caching http" width="800"/>

Nos headers (cabeçalhos) das requisições e das respostas são dadas diretivas para definir características do 
armazenamento em cache. Por exemplo:

<img src="https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/img_pages/headers.png" title="Headers" width="800"/>

## ETags

Em etag é basicamente um identificador para uma resposta a uma negociação de conteúdo. Se o conteúdo varia, a Etag 
varia, não necessariamente o endereço do recurso solicitado varia. Quando você faz uma requisição, ela pode variar a 
resposta de acordo com o contexto.

Levando em conta que a etag de um determinado arquivo (que você não especificou o tipo) pode variar, você não vai 
encontrar uma etag para um arquivo, vai encontrar uma etag para um determinado contexto de requisição a um arquivo.

### Status da requisição
O Etag fica no cabeçalho de uma requisição, é usando quando trabalhamos com caches ajudando identificar se um recurso
houve mudança, existe 2(duas) condições ou estados que **FRESH** e **STALE**.


- **Fresh**: Quando a representação pode usar recursos do cache e não houve mudança no cabeçalho, com isso evita fazer
requisições desnecessárias.
- **Stale**: Quando a representação está velha, vencida isso siginifica que vai precisar ser executada uma nova 
requisição para o servidor.

Exemplo de uso real:

Imagine que temos um cabeçalho `.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))` que foi definido um cache de 
10(dez) segundos, na primeira requisição para um determinado recurso o próprio navegador já identifica o cabeçalho da requisição
e deixa o recurso em cache por 10(dez) segundos com status de `Fresh`, se houver novas requisição dentro desse período
o navegador não vai buscar uma nova requisição, vai pegar aquela que está em cache.

#### Como implementar cache no Spring

Criar arquivo de configuração
````java

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

  /**
   * Para funcionar o shallowEtag Basta adicionar essa configuração
   */
  @Bean
  public Filter shallowEtagHeaderFilter() {
    return new ShallowEtagHeaderFilter();
  }
}
````
Setar o método de cache `cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))`
````java

    @GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId) {
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

		FormaPagamentoModel formaPagamentoModel =  formaPagamentoModelAssembler.toModel(formaPagamento);

		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(formaPagamentoModel);
	}
````

Depois de entendido como funciona os estados do cache, agora pode ser explicado como funciona as propriedades 
**Etag** e **If-None-Match**.

- **Etag**: Propriedade do cabeçalho que carrega algo que chamo de chave ou hash, basicamente é o código que representa o 
estado do cabeçalho, pra identificar se houve mudanças no recurso, também existe 2(duas) condições ou status para
fazer a indentificação dessas mudanças, `ETag: "05932f1679e6502d1feb68a7e7b24eb80"` e 
`If-None-Match: "05932f1679e6502d1feb68a7e7b24eb80"`

- **If-None-Match**: Propriedade do cabeçalho que carrega o mesmo valor da Etag, porém ele só é mostrado no cabeçalho 
quando é encontrado alguma mudança, por isso que o navegador retorna a chave pra identificar que existe uma mudança do
cabeçalho pra aquele recurso, basicamente é como se fosse feito uma pergunta para o servidor, -- Servidor esse cabeçalho
do recurso com a chave If-None-Match: "05932f1679e6502d1feb68a7e7b24eb80" sofreu alterações deseja continuar usando os
recursos em caches, abaixo tem 2(dois) exemplos de cabelhado explicando:

1º Primeira solicitação representa uma requisição usando recurso de cache, isso siginifica que não esta sendo feito
uma nova requisição para o servidor apenas usando os recurso que em cache ou seja em estado de Fresh, no caso abaixo
siginifica que a requisição foi feita no intervalo dos 10(dez) segundos.


````text

-----> GERAL
HTTP/1.1 200
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Access-Control-Allow-Origin: *
Cache-Control: max-age=10
ETag: "05932f1679e6502d1feb68a7e7b24eb80"
Content-Type: application/json;charset=UTF-8
Content-Length: 118
Date: Wed, 07 Dec 2022 18:48:53 GMT

-----> Cabeçalho de resposta
Access-Control-Allow-Origin: *
Cache-Control: max-age=10
Content-Length: 118
Content-Type: application/json;charset=UTF-8
Date: Wed, 07 Dec 2022 18:48:53 GMT
ETag: "05932f1679e6502d1feb68a7e7b24eb80"
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers

````

2º Segunda solicitação representa uma requisição feita para o servidor, siginifica que houve alteração no cabeçalho, 
onde o navegador identificou ter passado dos 10(dez) segundos e precisou ser feita uma nova requisição para o servidor e
agora existe uma nova propriedade com **If-None-Match** `If-None-Match: "05932f1679e6502d1feb68a7e7b24eb80"`, com o valor
da chave do **Etag**.

If-None-Match: Deve ser interpretado como uma pergunta para o servidor, **Posso usar o rescurso em cache ou devo fazer 
uma nova requisição para o servidor pra trazer o recurso atualizado?**

````text

-----> GERAL
Solicitar URL: http://localhost:8080/formas-pagamento
Método da solicitação: GET
Código de status: 200 
Endereço remoto: [::1]:8080
Política do referenciador: strict-origin-when-cross-origin

-----> Cabeçalho de resposta
Access-Control-Allow-Origin: *
Cache-Control: max-age=10
Content-Length: 118
Content-Type: application/json;charset=UTF-8
Date: Wed, 07 Dec 2022 18:52:24 GMT
ETag: "05932f1679e6502d1feb68a7e7b24eb80"
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers

-----> Cabeçalho de solicitação
Accept: */*
Accept-Encoding: gzip, deflate, br
Accept-Language: pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7,ru;q=0.6
Connection: keep-alive
DNT: 1
Host: localhost:8080
If-None-Match: "05932f1679e6502d1feb68a7e7b24eb80"
Origin: http://127.0.0.1:8000
Referer: http://127.0.0.1:8000/
sec-ch-ua: "Not?A_Brand";v="8", "Chromium";v="108", "Google Chrome";v="108"
sec-ch-ua-mobile: ?0
sec-ch-ua-platform: "Windows"
Sec-Fetch-Dest: empty
Sec-Fetch-Mode: cors
Sec-Fetch-Site: cross-site
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36

````

Imagens representando processo do **Etag** e **If-None-Match**
Resumindo é feito uma requisição para o servidor e no meio do caminho é verificado que esta tudo igual e retorna o status
304 dizendo pra usar oque esta em cache.

por isso deve tomar cuidado que alguns navegadores camuflão o status 304 nestes casos e retornam apenas o status 200, 
lembrando que não vai interferir no sistema porém é algo que precisa ter o conhecimento consiguir enteder o porque é 
usado dessa forma, para ter certeza que o código é o correto, basta usar o programa  para oegar a requisição correta.
[Wireshark - software para análise de tráfego de rede](https://www.wireshark.org/) 

Status 304 é um status que representa usa oque esta em cache por que não foi nada modificado.
![304 modified](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/readme/304-modified.png)

![if none match](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/readme/if-none-match.png)


#### Diretivas de Cache-Control na resposta HTTP
- **cachePrivate**: Resposta pode ser armazenada em apenas caches locais ou seja navegador, deve ser usado quando os 
dados do usuário é útil apenas para um único usuário.
- **cachePublic**: Resposta pode ser armazenada em todos os locais, proxy, local..., lembrando que o padrão já é public, 
mas caso precise deixar explícito
- **max-age**: Tempo do cache
- **noCache()**: Esta diretiva causa muita confusão devido o seu nome noCache, fazendo intender que não é pra fazer cache, 
porém sua funcionalidade correta é obrigar todas as requisições de determinado recurso fazer uma nova requisição para o 
servidor, resumindo é uma requisição que sempre vai estar em Stale.
- **noStore**: Esta diretiva, seginifica que ninguém pode armazenar a resposta em nenhum tipo de cache.

#### Deep Etags

É implementado para evitar que todo processamento seja feito do lado do servidor

````java
    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

        FormaPagamentoModel formaPagamentoModel =  formaPagamentoModelAssembler.toModel(formaPagamento);

        return ResponseEntity.ok()
        .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
        //.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
        //.cacheControl(CacheControl.noCache())
        //.cacheControl(CacheControl.noStore())
        .body(formaPagamentoModel);
    }
````

## Como fazer documentação usando a especificação OpenApi

**OpenApi**: É um padrão ou especificação para criação documentação de uma API.

Uma API aberta é uma API disponível para desenvolvedores externos. Isto não significa que seja uma API livre sem 
autenticação, sem controle ou sem custos. Uma determinada organização pode criar uma API aberta gratuita ou uma API 
aberta que seja paga. O conceito do “Open” tem a ver com o fato da API estar disponível para outras empresas e 
desenvolvedores. Do contrário, caso um sistema tenha uma API disponível, mas que seja acessível apenas a sistemas da 
própria empresa, então é uma API fechada.

- **Swagger Editor**:
- **SpringFox**: Usado para criar configuração da documentação, gerando json da definição, baseado nas configurações da 
`SpringFoxConfig`.
- **Swagger UI**: Renderizar documentação em html, usando Json com especificação OpenApi.
- **Swagger Codegen**:

## Como Ativar a compresão de resposta http para diminuir os bytes e deixando mais rápida a requisição
no application.properties adicionar propriedade 
````properties
# ativar compressão de payload
server.compression.enabled=true
# 2KB é o padrão
#server.compression.min-response-size=2KB
````

# ATUALIZAÇÕES

### 18.15. Referenciando modelo de representação de problema com códigos de status de erro

Atualizando para o SpringFox 3.0 e Open API 3
Este documento irá te auxiliar a fazer esta aula com a versão 3.0.0 do Spring Fox e suas dependências.

Observação: Este documento é apenas para os que querem usar as versões mais recentes das dependências contidas nesta aula.

Configurando o model de erro Global
O método responseModel não existe na classe ResponseBuilder do SpringFox 3, assim como o ModelRef que foi depreciado. Vamos utilizar os métodos representation e apply para realizar a mesma configuração.

Primeiramente na classe SpringFoxConfig precisamos criar um método que ira gerar a referência para classe Problem:

````java
  private Consumer<RepresentationBuilder> getProblemaModelReference() {
    return r -> r.model(m -> m.name("Problema")
    .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
    q -> q.name("Problema").namespace("com.algaworks.algafood.api.exceptionhandler")))));
  }
````

Em seguida aplicar essa configuração nas respostas globais, além de utilizar o método representation com o parametro MediaType.APPLICATION_JSON:

````java
  private List<Response> globalGetResponseMessages() {
    return Arrays.asList(
    new ResponseBuilder()
    .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
    .description("Erro interno do Servidor")
    .representation( MediaType.APPLICATION_JSON )
    .apply(getProblemaModelReference())
    .build(),
    new ResponseBuilder()
    .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
    .description("Recurso não possui representação que pode ser aceita pelo consumidor")
    .build()
    );
  }
````

````java
    private List<Response> globalPostPutResponseMessages() {
      return Arrays.asList(
        new ResponseBuilder()
        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
        .description("Requisição inválida (erro do cliente)")
        .representation( MediaType.APPLICATION_JSON )
        .apply(getProblemaModelReference())
        .build(),
        new ResponseBuilder()
        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
        .description("Erro interno no servidor")
        .representation( MediaType.APPLICATION_JSON )
        .apply(getProblemaModelReference())
        .build(),
        new ResponseBuilder()
        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
        .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
        .build(),
        new ResponseBuilder()
        .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
        .description("Requisição recusada porque o corpo está em um formato não suportado")
        .representation( MediaType.APPLICATION_JSON )
        .apply(getProblemaModelReference())
        .build()
      );
    }
````

````java
  private List<Response> globalDeleteResponseMessages() {
    return Arrays.asList(
      new ResponseBuilder()
      .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
      .description("Requisição inválida (erro do cliente)")
      .representation( MediaType.APPLICATION_JSON )
      .apply(getProblemaModelReference())
      .build(),
      new ResponseBuilder()
      .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
      .description("Erro interno no servidor")
      .representation( MediaType.APPLICATION_JSON )
      .apply(getProblemaModelReference())
      .build()
    );
  }
````

### 18.22. Desafio: descrevendo documentação de endpoints de cozinhas

Atualizando para o SpringFox 3.0 e Open API 3

Atualizando para o SpringFox 3.0 e Open API 3
Este documento irá te auxiliar a fazer esta aula com a versão 3.0.0 do Spring Fox e suas dependências.

Observação: Este documento é apenas para os que querem usar as versões mais recentes das dependências contidas nesta aula.

Evitando um NullPointerException
Ao adicionar o `produces = MediaType.APPLICATION_JSON_VALUE` na Annotation `@RequestMapping` nos Controllers de Grupo ou 
Cidade, o SpringFox 3 nos apresentará um NullPointerException. Isso é um problema conhecido que ainda não foi corrigido 
nessa biblioteca. Acontece devido a alguns métodos das Controllers terem o retorno void, como os de DELETE, que somado 
ao `produces = MediaType.APPLICATION_JSON_VALUE` gera um NullPointerException.

Sendo assim, é necessário adicionar o produces em cada um dos métodos, com exceção daqueles que retornam void, ao 
invés de adicioná-los na Controller.

Deixando as implementações da seguinte forma:

````java
package com.algaworks.algafood.api.controller;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController implements CozinhaControllerOpenApi {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;

	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<CozinhaModel> listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

		List<CozinhaModel> cozinhasModel = cozinhaModelAssembler
				.toCollectionModel(cozinhasPage.getContent());

		Page<CozinhaModel> cozinhasModelPage = new PageImpl<>(cozinhasModel, pageable,
				cozinhasPage.getTotalElements());

		return cozinhasModelPage;
	}

	@GetMapping(value = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CozinhaModel buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);

		return cozinhaModelAssembler.toModel(cozinha);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
		cozinha = cadastroCozinha.salvar(cozinha);

		return cozinhaModelAssembler.toModel(cozinha);
	}

	@PutMapping(value = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CozinhaModel atualizar(@PathVariable Long cozinhaId,
	                              @RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);
		cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
		cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);

		return cozinhaModelAssembler.toModel(cozinhaAtual);
	}

	@DeleteMapping(value = "/{cozinhaId}", produces = {})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinha.excluir(cozinhaId);
	}

}
````

### 18.24. Desafio: descrevendo documentação de endpoints de formas de pagamento

Atualizando para o SpringFox 3.0 e Open API 3
Este documento irá te auxiliar a fazer esta aula com a versão 3.0.0 do Spring Fox e suas dependências.

Observação: Este documento é apenas para os que querem usar as versões mais recentes das dependências contidas nesta aula.

Evitando um NullPointerException
Ao adicionar o produces = MediaType.APPLICATION_JSON_VALUE na Annotation @RequestMapping nos Controllers de Grupo ou Cidade, o SpringFox 3 nos apresentará um NullPointerException. Isso é um problema conhecido que ainda não foi corrigido nessa biblioteca. Acontece devido a alguns métodos das Controllers terem o retorno void, como os de DELETE, que somado ao produces = MediaType.APPLICATION_JSON_VALUE gera um NullPointerException.

Sendo assim, é necessário adicionar o produces em cada um dos métodos, com exceção daqueles que retornam void, ao invés de adicioná-los na Controller.

Deixando as implementações da seguinte forma:

````java
package com.algaworks.algafood.api.controller;

@RestController
@RequestMapping(path = "/grupos")
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private CadastroGrupoService cadastroGrupo;

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;

	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<GrupoModel> listar() {
		List<Grupo> todosGrupos = grupoRepository.findAll();

		return grupoModelAssembler.toCollectionModel(todosGrupos);
	}

	@GetMapping(path = "/{grupoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoModel buscar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

		return grupoModelAssembler.toModel(grupo);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);

		grupo = cadastroGrupo.salvar(grupo);

		return grupoModelAssembler.toModel(grupo);
	}

	@PutMapping(path = "/{grupoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoModel atualizar(@PathVariable Long grupoId,
	                            @RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);

		grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);

		grupoAtual = cadastroGrupo.salvar(grupoAtual);

		return grupoModelAssembler.toModel(grupoAtual);
	}

	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		cadastroGrupo.excluir(grupoId);
	}

}
````

### 18.25. Descrevendo parâmetros globais em operações
Atualizando para o SpringFox 3.0 e Open API 3
Este documento irá te auxiliar a fazer esta aula com a versão 3.0.0 do Spring Fox e suas dependências.

Nenhuma mudança que já não tenha sido mencionada anteriormente é necessária para essa atualização.

Substituindo componente depreciado
O método globalRequestParameters foi depreciado (assim como a classe ParameterBuilder), no seu lugar vamos utilizar o método globalRequestParameters e o seu respectivo builder o RequestParameterBuilder.

Na classe SpringFoxConfig deixe a implementação da seguinte forma:

````java
	.globalRequestParameters(Collections.singletonList(
            new RequestParameterBuilder()
                    .name("campos")
                    .description("Nomes das propriedades para filtrar na resposta, separados por vírgula")
                    .in(ParameterType.QUERY)
                    .required(true)
                    .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                    .build())
    )
````


# SPRING HATEOAS

## O que é Spring HATEOAS?

Spring-HATEOAS é uma biblioteca de APIs que podemos usar para criar representações REST que seguem o padrão HATEOAS.

## Hypermedias com Spring HETEOAS

HATEOAS ou (Hypertext as the Engine of Application State) é um modelo que permite que você navegue entre seus endpoints 
de forma dinâmica através de links e URLs e sem precisar do conhecimento prévio sobre a API. Nesse caso o cliente 
descobrirá as URLs conforme navega entre seus recursos.

**Resumo, Hateoas:**

aplicação consumidora terá um link inicial e a partir dele terá acesso a outros recursos caso necessário.

Exemplo de Hateoas
![Conhecendo especificações para formatos Hypermedia](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/readme/conhecendo-especificacoes-para-formatos-hypermedia.png "Conhecendo especificações para formatos Hypermedia")


## Paginação com Spring HATEOAS
![Paginação com Spring HATEOAS](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/img_pages/diagrama-hypermedia-em-recurso-com-paginacao.drawio.png)

# RETROCOMPATIBILIDADE VS QUEBRA DE COMPATIBILIDADE
* **Retrocompatibilidade**: Manter compatibilidade para versões antigas
* **Quebra de compatibilidade**: Quebrar compatibilidade para versões antigas

# VERSIONAMENTO DE API's 
## versionamento da API por Media Type
// versão 1
````text
Accept:application/vnd.localhost.v2+json
````
// versão 2
````text
Accept:application/vnd.localhost.v2+json
````
### Como definir MediaType de versionamento 

Classe `WebConfig` adicionar método `configureContentNegotiation`
````java
@Override
    public void configureContentNegotiation (ContentNegotiationConfigurer configurer) {
        /** Definindo qual MediaType padrão **/
        configurer.defaultContentType(AlgaMediaTypes.V2_APPLICATION_JSON);
    }
````
## versionamento da API por URI


# LOG

## Configurar o appender do Loggly no Logback

Appender é um componente que tem função de escrever os log's em algum lugar, console, em um arquivo, em algum webservices...

### Como criar um Appender para log

Adicionar Extensão appender LogBack no arquivo pom.xml
````xml
    <dependency>
      <groupId>org.logback-extensions</groupId>
      <artifactId>logback-ext-loggly</artifactId>
      <version>0.1.5</version>
    </dependency>
````
Criar arquivo `logback-spring.xml` dentro da pastar `resource`.
````xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
  <include resource="org/springframework/boot/logging/logback/base.xml"/>
   
  <springProperty name="logglyToken" source="logging.loggly.token" />

  <appender name="loggly" class="ch.qos.logback.ext.loggly.LogglyAppender">
    <endpointUrl>https://logs-01.loggly.com/bulk/${logglyToken}/tag/logback</endpointUrl>
    <pattern>%d{"ISO8601", UTC} %p %t %c %M - %m%n</pattern>
  </appender>

  <appender name="logglyAsync" class="ch.qos.logback.classic.AsyncAppender">
    <appender-ref ref="loggly" />
  </appender>

  <root level="info">
    <appender-ref ref="loggly" />
  </root>

</configuration>
````

## Serviço log nuvem
[Loggly: solução de gerenciamento de logs na nuvem](https://www.loggly.com/)

API Token e Customer Token
Existem dois tipos de token no Loggly: API Token e Customer Token.
O API Token serve apenas para consultar logs, não é possível registrar logs com ele. Esse tipo de token é obtido no link
abaixo, substituindo "meu-dominio" pelo valor adequado:

https://meu-dominio.loggly.com/account/users/api/tokens

Há um aviso no topo da página de API Tokens do Loggly, sobre seu escopo de leitura:
![api-tokens](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/img_pages/4OIG7T.jpg)


No nosso caso, precisamos de um Customer Token, pois vamos registrar logs. Para criar um token, acesse o link abaixo 
substituindo "meu-dominio" pelo valor adequado, ou através do menu conforme a imagem:

https://meu-dominio.loggly.com/tokens
![custmoer-tokens](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/img_pages/dzEast.jpg)


# SEGURANÇA REST APIs

* Mecanismo de Autenticação: Quando usuário está logado na API
* Mecanismo de Autorização Quando usuário tem permissão para acessar o recurso



Adicionar no `pom.xml`
````xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
````

Implementar segurança usando HTTP Basic Authentication
![seguranca-http-basic](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/img_pages/seguranca-http-basic.png)

O método de segurança HTTP Basic, disponibiliza por padrão usuário: `user` e a senha é retornado no console da aplicação: 
````shell
Using generated security password: 67ebc5a1-7b45-40fa-bee3-69a76519e30b
````

ou pode configurar uma senha padrão no arquivo `apllication.properties`
````properties
spring.security.user.name=algafood
spring.security.user.password=123456
````

Implementar segurança usando OAuth2
![seguranca-oauth2](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/img_pages/seguranca-oauth2.png)


### Definiçõe de papéis "Roles" OAuth2
![roles](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/img_pages/roles-papeis.png)

* Resource serve: REST API
* Resource Owner: Representa o usuário Final, o dono dos recursos, ex: O João tem um restaurante e faz a manipulação dos
registros como cadastra pratos, promoções, ingredientes... 
  * Pode Autorizar outras entidades a acessarem seus recursos em seu nome
* Client: Pode ser uma aplicação Web, Mobile, etc, o interesse é acessar os recursos protegidos do `Resource Server`
* Authorization Server: Servidor que autentica o `Resource Owner` e garante o acesso para o `client` 
acessar o `Resource Server` em nome do Resource Owner.

![seguranca-oauth2-1](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/img_pages/seguranca-oauth2_1.png)


### Fluxo de Resource Owner Password Credentials Grant (Não é o mais seguro)

Você pode encontrar o mesmo fluxo mas com nomes diferentes:
* **Password Credencials**;
* **Password Flow**;
* **Password Grant**;
* **Password Crendentials Type**;

mas todos seguinificam a mesma coisa.

**Resource Owner Password Credentials Grant**: É a forma de obter o Access Token "Token de acesso" apartir de um,
usuário e senha, é trocado um usuário e senha em troca de um Access Token
* O `Cliente` oferece usuário e senha para o `Authorization Server`
* O `Authorization Server` emite um Access Token para esse `client`
* Depois do acessToken estar com o cliente pode usar para acessar os recursos do `Resource Serve` em nome do 
`Resource Owner` "Usuário Final".
  
![Fluxo de Resource Owner Password Credentials Grant](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/img_pages/seguranca-oauth2_2-retorna-acess-token.png)

### Access Token e Refresh Token, Quando usar

Tanto o Access Token como Refresh Token ambos são usados para gerar um novo token, porém existe uma pequena diferença 
entre eles, o Acess Token é usado para solicitar o primeiro Token, porém existe um tempo limite o qual vai expirar esse
token, se não tiver ninguém usando o token o processo e finalizado, porém caso alguém esta acessando algum recuro e o
token expíra é aí que entra o Refresh Token um token diferente o papel dele é ser usado para ser gerado um novo Access 
Token sem que preciser informar as credencias novamente. 

* **Access Token expíra em**: 12 horas, esse é o valor padrão;
* **Refresh Token expíra em**: 30 dias, esse é o valor padrão;

**Solicitação de Access Token**

![Fluxo representa Acess Token Expirado](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/img_pages/fluxo-access-token-expirou.png)

**Refrech Token**
![Fluxo representa Refresh Token](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/img_pages/fluxo-refresh-token.png)

### Fluxo Client Credentials Grant
Este fluxo é usado para acessos da própria aplicação backend, como um serviço que fica rodando backend, neste caso
não existe o papel do Resource Owner "Usuário final"

![Fluxo Client Credentials Grant](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/img_pages/fluxo-client-credentials-grant.png)

# CONHECIMENTOS DIVERSOS

## PROXY

![Oque é e como funciona Proxy](https://raw.githubusercontent.com/leco123/algafood/master/src/main/resources/images/img_pages/Topologia-proxy-1024x343.png)

### Oque é e como funciona Proxy
Os servidores proxy são normalmente utilizados como ponte entre origem e destino de uma requisição.

**Para que serve?**

- **Controle de acesso** – É possivel para os adminsitradores do servidor proxy permitir que determinados usuários tenham ou 
não acesso a Internet através de restrições aplicadas ao login do próprio usuário ou ao endereços IP, provendo ao 
ambiente uma camada a mais de proteção.

- **Filtro de conteúdo** – Estando no meio do caminho, o servidor também permite que determinados sites sejam ou não acessados. 
Dentre as regras que podem ser aplicadas estão as destinadas ao bloqueio de sites específicos, podendo chegar ao 
bloqueio de categorias inteiras.

- **Cache** – Outro uso muito comum para Web Proxies é fazer com que eles exerçam a função de cache. Isso faz com que, após 
- um acesso a uma página, o proxy armazene o conteúdo da página em seu sistema. Após isso, as demais requisições à esta 
- mesma página não precisarão sair para a Internet, pois o conteúdo já está armazenado na memória do proxy.


### Proxy reverso

![Proxy reverso](https://raw.githubusercontent.com/leco123/algafood/25154f973fe3cb1ebc9083ae13ea835449b26d69/src/main/resources/images/img_pages/proxy_reverso.png)

Um outro uso bem comum são os servidores de Proxy Reverso. Nos exemplos citados acima, a origem da conexão era sempre 
dentro da rede, passava pelo proxy até a Internet. No caso do proxy reverso, a origem das requisições estão na Internet 
e buscam acessar um servidor dentro do ambiente, conforme imagem abaixo:

Proxies reversos são normalmente usados para tratar requisições destinadas à servidores que hospedam páginas de Internet, 
alguns dos benefícios de sua utilização são:

- **Balanceamento de carga** – Como a estrutura do servidor de proxy reverso permite que ele tenha conexão com vários 
servidores de destino, ele pode direcionar as requisições para cada um deles sem sobrecarregar nenhum. Como uma 
outra característica de segurança, as requisições de Internet conhecerão apenas o endereço IP do proxy e não de 
todos os servidores e páginas que a empresa tem.

- **Cache** – Como no exemplo de cache de web, proxies também são utilizados para optimizar as requisições entre origem e 
destino. O servidor de proxy reverso armazena elementos da página armazenada nos servidores internos, buscando de 
tempos em tempos por atualizações do conteúdo, isso faz com que os servidores de página recebam ainda menos requisições 
de rede, permitindo que trabalhem ainda melhor.

### Proxy para todos

Não podemos falar de proxy sem falar dos free proxies. Eles são páginas de Internet, como por exemplo 
o https://free-proxy-list.net/ , que fornecem endereços de servidores proxy ao redor do mundo “totalmente de graça”. 
Qualquer pessoa na Internet pode fazer as devidas configurações em seu navegador e usar o servidor oferecido 
para navegar na web.

# LINKS DE DOCUMENTAÇÕES

- [Documentação do Spring Data JPA: Keywords de query methods](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation) chaves usadas para fazer consultas em banco
- [Mais informações sobre a váriavel `serialVersionUID`](https://blog.algaworks.com/serialversionuid/)  Veja o artigo completo da **AlgaWorks** sobre a váriavel `serialVersionUID` descrito por **Alexandre Afonso**
- [Diferença entre Inner, Left, Right, Outer/Full e Cross Join](https://pt.stackoverflow.com/questions/6441/qual-%C3%A9-a-diferen%C3%A7a-entre-inner-join-e-outer-join)
- [Projeto da comunidade dev sobre oque deve ser adicionar no `.gitigonre`](https://github.com/github/gitignore)
- [Biblioteca Squiggly, usado para fazer filtros de campos dinâmicos ou limitar as propriedades de recursos](https://gist.github.com/thiagofa/ce48c08e4caae34c5dca0a7a5c252666)
- [Java Predicate](https://www.linkedin.com/pulse/java-predicate-jos%C3%A9-r-f-junior/?originalSubdomain=pt)
- Pesquisar artefatos do respositório central do Maven https://search.maven.org
  - [Biblioteca Jasperreports 6.20.0](https://central.sonatype.dev/artifact/net.sf.jasperreports/jasperreports/6.20.0)
  - [Biblioteca Jasperreports-functions 6.20.0](https://central.sonatype.dev/artifact/net.sf.jasperreports/jasperreports-functions/6.20.0)
- Evitando dores de cabeça usando OneToOne de forma Bidirecional, conforme explica o artigo, Mapeamento de Entidades-Lazy loading [OneToOne](https://blog.algaworks.com/lazy-loading-com-mapeamento-onetoone)
- Biblioteca Apache [<#FREEMARKER>](https://freemarker.apache.org/)
- [Boas práticas de HTML para e-mails](https://ajuda.locaweb.com.br/wiki/boas-praticas-de-html-para-email-marketing-ajuda-locaweb)
- [Definição de requisição simples, de acordo com CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS#simple_requests)
- [Caching http request](https://www.azion.com/pt-br/blog/o-que-e-http-caching-e-como-ele-funciona/)
- [Wireshark - software para análise de tráfego de rede](https://www.wireshark.org/)
- [Etag, If-None-Match](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Headers/If-None-Match)
- [Hateoas, principais HyperMídias]
  - Principal e mais usado, [HyperMídias -> HAL - Hypertext Application Language](https://stateless.group/hal_specification.html)
  - [HyperMídias -> UBER - Uniform Basis for Exchanging Representations](https://rawgit.com/uber-hypermedia/specification/master/uber-hypermedia.html)
  - [HyperMídias -> JSON:API](https://jsonapi.org/)
  - [HyperMídias -> JSON-LD - JSON for Linking Data](https://json-ld.org/)
  - [HyperMídias -> Collection+JSON](http://amundsen.com/media-types/collection/format/)
  - [HyperMídias -> Siren](https://github.com/kevinswiber/siren)
- **CONHECIMENTOS DIVERSOS**
- [Proxy](https://www.welivesecurity.com/br/2019/12/20/o-que-e-um-proxy-e-para-que-serve/)
- LOG´s
  - Atual - [Logback-Framework de log para aplicações javas](https://logback.qos.ch/)
  - Antigo - [SLF4j-Framework de log para aplicações javas](https://www.slf4j.org/)
  - [Loggly: solução de gerenciamento de logs na nuvem](https://www.loggly.com/)
- Autenticação
  - [Especificação do OAuth 2.0](https://tools.ietf.org/html/rfc6749)
  - [Spring Security OAuth](https://spring.io/projects/spring-security-oauth)
  - [Spring Security](https://spring.io/projects/spring-security)
  - [Spring Security OAuth 2.0 Roadmap Update](https://spring.io/blog/2019/11/14/spring-security-oauth-2-0-roadmap-update)
  - [Dependências para resolver problema com Spring Security OAuth2 e Java 11+](https://gist.github.com/thiagofa/ef9a40d495016cb2581add41b5cbde1b)
  - [RFC 7662 - OAuth 2.0 Token Introspection](https://tools.ietf.org/html/rfc7662)
