# Treinamento Algaworks Especialista em REST (40% do Curso concluído)

Descrevi em tópicos o que aprendi no curso e também pesquisando na _internet_, como o curso é amplo dexei apenas informações que não conhecia ou não lembrava e até mesmo informação que conhecia, porém, de uma abordagem diferente.

## API Documentação Swagger

[Link de documentação](http://localhost:8080/spring-security-rest/api/v2/api-docs)

## Aprendizados

- Variável serialVersionUID
- Mapeapento de entidades
- Injeção de dependência
- Implementar resquisições _MediaTypes_: `Json` e `XML`;
- Implementar requisições onde o cliente consiga fazer escolha em qual `MediaType` vai consumir API;
- Oque é REST e RESTful;
- Usar injeção de dependência de biblioteca externa no projeto

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

## Links de documentações

- [Documentação do Spring Data JPA: Keywords de query methods](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation) chaves usadas para fazer consultas em banco
- [Mais informações sobre a váriavel `serialVersionUID`](https://blog.algaworks.com/serialversionuid/)  Veja o artigo completo da **AlgaWorks** sobre a váriavel `serialVersionUID` descrito por **Alexandre Afonso**
- [Diferença entre Inner, Left, Right, Outer/Full e Cross Join](https://pt.stackoverflow.com/questions/6441/qual-%C3%A9-a-diferen%C3%A7a-entre-inner-join-e-outer-join)
- [Projeto da comunidade dev sobre oque deve ser adicionar no `.gitigonre`](https://github.com/github/gitignore)
- [Biblioteca Squiggly, usado para fazer filtros de campos dinâmicos ou limitar as propriedades de recursos](https://gist.github.com/thiagofa/ce48c08e4caae34c5dca0a7a5c252666)
- [Java Predicate](https://www.linkedin.com/pulse/java-predicate-jos%C3%A9-r-f-junior/?originalSubdomain=pt)
