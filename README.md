
# Treinamento Algaworks Especialista em REST

Descrevi em tópicos o que aprendi no curso e também pesquisando na _internet_, como o curso é amplo dexei apenas informações que não conhecia ou não lembrava e até mesmo informação que conhecia, porém, de uma abordagem diferente.

# CONCEITOS



## CONCEITO: O QUE É REST e RESTful?

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



## Aprendizados

### Exemplo de como implementar PATCH dinâmico

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


### Dícas

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

## Links de documentações

- [Documentação do Spring Data JPA: Keywords de query methods](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation) chaves usadas para fazer consultas em banco