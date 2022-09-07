
# Treinamento Algaworks Especialista em REST

Descrevi em tópicos o que aprendi no curso e também pesquisando na _internet_, como o curso é amplo dexei apenas informações que não conhecia ou não lembrava e até mesmo informação que conhecia, porém, de uma abordagem diferente.

## Aprendizados
- Variável serialVersionUID
- Mapeapento de Entidades
- Injeção de Dependência
- Implementar Resquisições _MediaTypes_: `Json` e `XML`;
- Implementar Requisições onde o cliente consiga fazer escolha em qual `MediaType` vai consumir API;
- Oque é REST e RESTful;

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

>Basicamente quando não informamos de forma explícita o valor do `serialVersionUID` o Java faz isso de forma implícita, 
porém é aqui que está o problema, quando adicionamos um novo atributo a classe esse valor é atualizado devido o Java usar os atributos da classe para gerar um 
Hash explícito ao `serialVersionUID`, porém ele cria isso como se fosse uma nova versão do arquivo e no momento de deserializar esse arquivo com a versão antiga não vai conseguir porque esta em 
uma versão diferente da primeira. Existe duas maneiras de resolver esse problema:


**1º A mais simples foi descrito no tópico acima**: É adicionar de forma explícita o _serialVersionUID_, assim que for adicionado o implements Serializable tópico: Como evitar erro Exception por causa do _serialVersionUID_;

**2ª Descubir qual era o valor _serialVersionUID que o Java tinha atribuido para classe**:

Descoberto o valor de serialVersionUID na versão antiga da classe, então, basta declará-lo explicitamente na nova versão para conseguir desserializar os objetos mais antigos:
````shell
$ serialver -classpath :target/artigo-java-serialVersionUID-1.0-SNAPSHOT.jar com.meudominio.serialversionuid.exemplo.NomeClasse
// RETORNO DO CONSOLE:
com.meudominio.serialversionuid.exemplo.NomeClasse: private static final long serialVersionUID = -3969352858203924755L;
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
É uma API desenvolvida em conformidade com as constraints, resumindo é uma API que segue todas as constraints obrigatórias, caso seja violado alguma constraint não deve ser reconhecida ou trata como ````RESTful````.

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

### Como implementar Verbo PATCH de forma dinâmica

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


## Links de documentações

- [Documentação do Spring Data JPA: Keywords de query methods](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation) chaves usadas para fazer consultas em banco
- [Mais informações sobre a váriavel `serialVersionUID`](https://blog.algaworks.com/serialversionuid/)  Veja o artigo completo da **AlgaWorks** sobre a váriavel `serialVersionUID` descrito por **Alexandre Afonso** 
- [Diferença entre Inner, Left, Right, Outer/Full e Cross Join](https://pt.stackoverflow.com/questions/6441/qual-%C3%A9-a-diferen%C3%A7a-entre-inner-join-e-outer-join)
