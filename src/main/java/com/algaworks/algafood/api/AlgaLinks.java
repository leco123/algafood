package com.algaworks.algafood.api;

import com.algaworks.algafood.api.controller.pedido.PedidoController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class AlgaLinks {

//  Variáveis de páginas
    private static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
                new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
        );

//  Variáveis de filtros
    public Link linkToPedidos() {

        TemplateVariables filtroVariables = new TemplateVariables(
                new TemplateVariable("clienteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restauranteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoInicio", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoFim", TemplateVariable.VariableType.REQUEST_PARAM));

        String pedidoUrl = WebMvcLinkBuilder.linkTo(PedidoController.class).toUri().toString();

//        pedidoModel.add(WebMvcLinkBuilder.linkTo(PedidoController.class).withRel("pedidos"));
//       UriTemplate, recebe 2(dois) parâmetros
//       1º urlString exemplo "http://localhost:8080/pedidos"
//       2º TemplateVariables variaveis da página ex: {"page","sort","size"... outros}

        return new Link(UriTemplate.of(pedidoUrl, PAGINACAO_VARIABLES.concat(filtroVariables)), "pedidos");
    }
}
