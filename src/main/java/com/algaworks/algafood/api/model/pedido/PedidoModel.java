package com.algaworks.algafood.api.model.pedido;

import com.algaworks.algafood.api.model.restaurante.formas_pagamento.FormaPagamentoModel;
import com.algaworks.algafood.api.model.restaurante.RestauranteResumoModel;
import com.algaworks.algafood.api.model.endereco.EnderecoModel;
import com.algaworks.algafood.api.model.usuario.UsuarioModel;
import com.algaworks.algafood.domain.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoModel {

    private Long id;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;
    private RestauranteResumoModel restaurante;
    private UsuarioModel cliente;
    private FormaPagamentoModel formaPagamento;
    private EnderecoModel enderecoEntrega;
    private List<ItemPedidoModel> itens;
}
