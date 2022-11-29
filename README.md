# Tópicos abordados

<!-- TOC -->
* [Tópicos abordados](#tpicos-abordados)
* [Treinamento Algaworks Especialista em REST (40% do Curso concluído)](#treinamento-algaworks-especialista-em-rest--40-do-curso-concludo-)
    * [Rodar projeto via line command](#rodar-projeto-via-line-command)
      * [WINDOWS](#windows)
      * [LINUX](#linux)
    * [Variável _`serialVersionUID`_ e sua importância na arquitetura Java](#varivel-_serialversionuid_-e-sua-importncia-na-arquitetura-java)
      * [O que é o serialVersionUID?](#o-que--o-serialversionuid)
  * [Oque é Predicado?](#oque--predicado)
  * [Como implementar consultas complexas que tem mais de uma propriedade](#como-implementar-consultas-complexas-que-tem-mais-de-uma-propriedade)
    * [consulta usando `Predicate`](#consulta-usando-predicate)
  * [Como serealizar o tipo Page do zero](#como-serealizar-o-tipo-page-do-zero)
* [CONCEITOS](#conceitos)
  * [Oque é REST e RESTful?](#oque--rest-e-restful)
  * [Conhecendo as constraints do REST](#conhecendo-as-constraints-do-rest)
  * [Diferença entre REST e RESTful](#diferena-entre-rest-e-restful)
      * [REST:](#rest-)
      * [RESTful ou REST API:](#restful-ou-rest-api-)
  * [Medindo a maturidade de sua (API) - "Richardson Maturity Model" (RMM)](#medindo-a-maturidade-de-sua--api-----richardson-maturity-model---rmm-)
    * [Dícas](#dcas)
    * [Como implementar Verbo PATCH de forma dinâmica porém de forma não amigável.](#como-implementar-verbo-patch-de-forma-dinmica-porm-de-forma-no-amigvel)
      * [Como evitar erros de NullPointerException usando o Optional lançado no Java 8](#como-evitar-erros-de-nullpointerexception-usando-o-optional-lanado-no-java-8)
      * [Propósitos dos Optional](#propsitos-dos-optional)
        * [Principais Métodos](#principais-mtodos)
    * [Como Fazer backup MySQL com dump](#como-fazer-backup-mysql-com-dump)
    * [Como usar injeção de dependência de biblioteca externa no projeto](#como-usar-injeo-de-dependncia-de-biblioteca-externa-no-projeto)
    * [Como reparar Flyway via linha de comando](#como-reparar-flyway-via-linha-de-comando)
  * [Como Implementar **Exception** global para usar em todo o projeto usando Spring](#como-implementar-exception-global-para-usar-em-todo-o-projeto-usando-spring)
  * [Como usar ExceptionUtils.getRootCause(ex) para retornar a causa da exception](#como-usar-exceptionutilsgetrootcause--ex--para-retornar-a-causa-da-exception)
  * [Como customizar mensagens usando o Resource Bundle do Bean Validation](#como-customizar-mensagens-usando-o-resource-bundle-do-bean-validation)
  * [Como criar uma validação específica usando annotattion](#como-criar-uma-validao-especfica-usando-annotattion)
  * [Como criar uma validação específica para FileUpload arquivos de uploads](#como-criar-uma-validao-especfica-para-fileupload-arquivos-de-uploads)
    * [Criar controller `RestauranteProdutoFotoController`](#criar-controller-restauranteprodutofotocontroller)
    * [Criar dto input](#criar-dto-input)
    * [Criar annotation `FileSize`](#criar-annotation-filesize)
    * [Criar validator `FileSizeValidator`](#criar-validator-filesizevalidator)
    * [Criar annotattion `FileContentType`](#criar-annotattion-filecontenttype)
    * [Criar validator `FileContentTypeValidator`](#criar-validator-filecontenttypevalidator)
    * [Criar mensagens para os campos de upload no arquivo `messages.properties`](#criar-mensagens-para-os-campos-de-upload-no-arquivo-messagesproperties)
    * [Informação importante, existe uma falha no sprint quando se usa uma constraint existente que estpa sendo](#informao-importante-existe-uma-falha-no-sprint-quando-se-usa-uma-constraint-existente-que-estpa-sendo)
  * [Boas praticas de data e hora](#boas-praticas-de-data-e-hora)
  * [Como criar filtros dinâmicos usando biblioteca Squiggly conforme propriedades do model](#como-criar-filtros-dinmicos-usando-biblioteca-squiggly-conforme-propriedades-do-model)
  * [Como validar parâmetros de um recurso do tipo pdf, evitando erro 406](#como-validar-parmetros-de-um-recurso-do-tipo-pdf-evitando-erro-406)
  * [Como salvar binários na base de dados, como um arquivo, existe algumas formas de fazer isso que é exemplificado abaixo.](#como-salvar-binrios-na-base-de-dados-como-um-arquivo-existe-algumas-formas-de-fazer-isso-que--exemplificado-abaixo)
    * [Exemplo-1, como salvar arquivo binário usando base64](#exemplo-1-como-salvar-arquivo-binrio-usando-base64)
    * [Exemplo-2, como salvar arquivo binário de forma nativa usando multipart/form-data](#exemplo-2-como-salvar-arquivo-binrio-de-forma-nativa-usando-multipartform-data)
  * [O problema do lazy loading com @OneToOne](#o-problema-do-lazy-loading-com-onetoone)
    * [1º Solução, Utilizando @MapsId para fazer da FK uma PK também](#1-soluo-utilizando-mapsid-para-fazer-da-fk-uma-pk-tambm)
    * [2º Solução é a forma comum que as pessoas implementam](#2-soluo--a-forma-comum-que-as-pessoas-implementam)
  * [Links de documentações](#links-de-documentaes)
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

**Serializar** um objeto, dentro da plataforma **Java**, significa converter o estado atual dele em outro formato "**padrão**" por exemplo: imagem, json, pdf... e depois disponibilizá-lo em um stream de _bytes_ que poderá ser escrito em disco ou transmitido.

**Cenários comuns para o uso do mecanismo de serialização**, dentro do ecossistema Java, são as invocações de métodos remotos (**RPC**) e também na replicação de **sessões** dos servidores web ou de aplicação.

**Classes que fazem uso do Serializable**: _wrappers_ juntamente com os primitivos - "_esses não implementam, pois, não são classes, mas podem ser serializados_".

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
> porém é aqui que está o problema, quando adicionamos um novo atributo a classe esse valor é atualizado devido o Java usar os atributos da classe para gerar um
> Hash explícito ao `serialVersionUID`, porém ele cria isso como se fosse uma nova versão do arquivo e no momento de deserializar esse arquivo com a versão antiga não vai conseguir porque esta em
> uma versão diferente da primeira. Existe duas maneiras de resolver esse problema:

**1º A mais simples foi descrito no tópico acima**: É adicionar de forma explícita o _serialVersionUID_, assim que for adicionado o implements Serializable tópico: Como evitar erro Exception por causa do _serialVersionUID_;

**2ª Descubir qual era o valor _serialVersionUID que o Java tinha atribuido para classe**:

Descoberto o valor de serialVersionUID na versão antiga da classe, então, basta declará-lo explicitamente na nova versão para conseguir desserializar os objetos mais antigos:

````shell
$ serialver -classpath :target/artigo-java-serialVersionUID-1.0-SNAPSHOT.jar com.meudominio.serialversionuid.exemplo.NomeClasse
// RETORNO DO CONSOLE:
com.meudominio.serialversionuid.exemplo.NomeClasse: private static final long serialVersionUID = -3969352858203924755L;
````

## Oque é Predicado?

No Java 8, o Predicate é uma interface funcional e, portanto, pode ser usado como destino de atribuição para uma expressão lambda ou referência de método.

Na matemática, um predicado é comumente entendido como uma função de valor booleano 'P: X? {true, false}' , chamada de predicado em X. Informalmente, um forte.
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
ERRO: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'pedidoController': Lookup method resolution failed; nested exception is java.lang.IllegalStateException: Failed to introspect Class [com.algaworks.algafood.api.controller.pedido.PedidoController] from ClassLoader [org.springframework.boot.devtools.restart.classloader.RestartClassLoader@2643d422]
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

_Constraints_ é uma restrição há algo que limita ou controla o que você pode fazer, abaixo esta listado as constraints do padrão REST

— **Cliente-servidor:** devem poder evoluir separadamente sem qualquer dependência entre elas.

— **Stateless:** Sem estador, aplicação não deve possuir estado, resumindo a requisição feita ao servidor deve conter tudo que precisa para ser processado, o servidor não pode manter sessão nele mesmo, isso significa que o servidor não manterá o histórico de uso;

— **Cache:** API pode fazer caches das requisições ao servidor ao fazer uma nova requisição? A requisição para o mesmo endpoint pode ser configurada para buscar no cache, melhorando a escalabilidade e desempenho, sitando alguns exemplos: nomes de cidades, endereços, enfim esses dados que dificilmente sofreram alterações.

— **Interface** Uniforme: Conjunto de operações bem definidas no sistema, uma vez definida deve seguir isso como verdade total;

— **Sistema em camadas:** Entre o cliente que prove a API e o cliente que consome API existem outros servidores no meio que fornecem outras camadas como segurança, cache, balanceamento de carga, etc.

— **Código sob demanda:** Opcional, o servidor pode enviar como resposta algum código que pode ser executado no cliente, porém é muito pouco usado.

## Diferença entre REST e RESTful

#### REST:

É o estilo cultural que possui as **constraints**, mencionadas acima:

- Cliente-servidor (Client-Server);
- Stateless;
- Cache (Cacheable);
- Interface Uniforme (Uniform Interface);
- Sistema em camadas (Layered system);

#### RESTful ou REST API:

É uma API desenvolvida em conformidade com as constraints, resumindo é uma API que segue todas as constraints obrigatórias, caso seja violado alguma constraint não deve ser reconhecida ou trata como ``RESTful``.

## Medindo a maturidade de sua (API) - "Richardson Maturity Model" (RMM)

Quem criou o modelo de Maturidade foi _Richardson_ e para saber se uma API é REST, faça uma pergunta: **-Em qual nível de maturidade de acordo com Richardson, foi desenvolvida API?**

- **Nível 3**: "Level Three Services" `HATEOAS` Último nível considerado a glória do REST, na teoria apenas o nível 3 deve ser considerado como REST de acordo com [Roy Fielding](https://roy.gbiv.com/) Cientista de computação

  **Explicação:** O nível 3(três) de maturidade faz o uso eficiente dos três fatores. **URIs**, **HTTP** e **HATEOAS**.
- **Nível 2**: "Level Two Services" Verbos HTTP -> Apesar dos desenvolvedores purista não considerarem REST, porém a maioria dos desenvolvedores nomearam sendo REST

  **Explicação:** O nível 2(dois) de maturidade faz o uso eficiente de URIs e verbos HTTP. Nos níveis anteriores o protocolo HTTP estava sendo usado superficialmente.

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

import com.algaworks.algafood.api.model.endereco.EnderecoModel;
import com.algaworks.algafood.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
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
![Exemplo de como validar parâmetros quando for arquivo pdf](D:\repository\spring\algafood-api\src\main\resources\images\validar_parametros_pdf.png "header parâmetros")


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

### Template do corpo de e-mails com Apache FreeMarker
 


## Links de documentações

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
