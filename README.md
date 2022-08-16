
# Treinamento Algaworks Especialista em REST

Uma breve descrição sobre o que esse projeto faz e para quem ele é


## Conhecendo as constraints do REST

— **Constraints:** É uma restrição é algo que limita ou controla o que você pode fazer. 

— **Cliente-servidor:** devem poder evoluir separadamente sem qualquer dependência entre elas.

— **Stateless:** Sem estador, aplicação não deve possuir estado, resumindo a requisição feita ao servidor deve conter tudo que precisa para ser processado, o servidor não pode manter sessão no servidor, isso significa que o servidor não manterá o histórico de uso;

— **Cache:** API pode fazer cache das requisições ao servidor, resumindo ao fazer uma nova requisição não, na verdade, não chega ser concretizado a requisição e sim buscado essa requisição no cache, melhorando a escalabilidade e desempenho.

— **Interface** Uniforme: Conjunto de operações bem definidas no sistema, uma vez definida deve seguir isso como verdade total;

— **Sistema em camadas:** Entre o cliente que prove a API e o cliente que consome API existe outro servidor no meio que fornece outras camadas como segurança, cache, balanceamento de carga, etc.

— **Código sob demanda:** Opcional, o servidor pode enviar como resposta algum código que pode executado no cliente, porém é muito pouco usado.
## Diferença entre REST e RESTful

#### REST: 
É o estilo cultural que possui as **constraints**, mencionadas acima: 
- Cliente-servidor;
- Stateless;
- Cache; 
- Interface Uniforme; 
- Sistema em camadas;

#### RESTful ou REST API: 
É uma API desenvolvida em conformidade com as constraints, resumindo é uma API que segue todas as constraints obrigatórias, caso seja violado alguma constraint não deve ser reconhecida ou trata como ````RESTful````.

## Aprendizados

O que aprendi construindo esse projeto? 

Quais desafios foram enfrentandos e como foram superador?

## Termos

O que aprendi construindo esse projeto? 