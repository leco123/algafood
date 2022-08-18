
# Treinamento Algaworks Especialista em REST

Uma breve descrição sobre o que esse projeto faz e para quem ele é

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

## Medindo a Maturidade de sua API - "Richardson Maturity Model" (RMM)
Pra saber se uma API é REST pergunte a ela qual o nível de maturidade da API de acordo com Richardson, quem criou o modelo de Maturidade Richardson;

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

#### Exemplo de como implementar PATCH dinâmico

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


## Termos

O que aprendi construindo esse projeto? 
