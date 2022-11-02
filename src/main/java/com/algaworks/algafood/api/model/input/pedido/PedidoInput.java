package com.algaworks.algafood.api.model.input.pedido;

import com.algaworks.algafood.api.model.input.endereco.EnderecoInput;
import com.algaworks.algafood.api.model.input.restaurante.RestauranteIdInput;
import com.algaworks.algafood.api.model.input.restaurante.formas_pagamento.FormaPagamentoIdInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
public class PedidoInput {

    @Valid
    @NotNull
    private RestauranteIdInput restaurante;

    @Valid
    @NotNull
    private EnderecoInput enderecoEntrega;

    @Valid
    @NotNull
    private FormaPagamentoIdInput formaPagamento;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemPedidoInput> itens;

}
