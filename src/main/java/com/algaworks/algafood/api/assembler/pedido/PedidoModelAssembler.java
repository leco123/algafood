package com.algaworks.algafood.api.assembler.pedido;

import com.algaworks.algafood.api.controller.FormaPagamentoController;
import com.algaworks.algafood.api.controller.endereco.CidadeController;
import com.algaworks.algafood.api.controller.pedido.PedidoController;
import com.algaworks.algafood.api.controller.restaurante.RestauranteController;
import com.algaworks.algafood.api.controller.restaurante.RestauranteProdutoController;
import com.algaworks.algafood.api.controller.usuario.UsuarioController;
import com.algaworks.algafood.api.model.pedido.PedidoModel;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelAssembler
        extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoModelAssembler() {
        super(PedidoController.class, PedidoModel.class);
    }

    @Override
    public PedidoModel toModel(Pedido pedido) {
        PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModel);

        TemplateVariables pageVariables = new TemplateVariables(
                new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
        );

        String pedidoUrl = WebMvcLinkBuilder.linkTo(PedidoController.class).toUri().toString();

//        pedidoModel.add(WebMvcLinkBuilder.linkTo(PedidoController.class).withRel("pedidos"));
//       UriTemplate, recebe 2(dois) parâmetros
//       1º urlString exemplo "http://localhost:8080/pedidos"
//       2º TemplateVariables variaveis da página ex: {"page","sort","size"... outros}
        pedidoModel.add(new Link(UriTemplate.of(pedidoUrl, pageVariables), "pedidos"));

        pedidoModel.getRestaurante().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class)
                .buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoModel.getCliente().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .buscar(pedido.getCliente().getId())).withSelfRel());

        // Passamos null no segundo argumento, porque é indiferente para a
        // construção da URL do recurso de forma de pagamento
        pedidoModel.getFormaPagamento().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FormaPagamentoController.class)
                .buscar(pedido.getFormaPagamento().getId(), null)).withSelfRel());

        pedidoModel.getEnderecoEntrega().getCidade().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class)
                .buscar(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel());

        pedidoModel.getItens().forEach(item -> {
            item.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteProdutoController.class)
                    .buscar(pedidoModel.getRestaurante().getId(), item.getProdutoId()))
                    .withRel("produto"));
        });

        return pedidoModel;
    }
}