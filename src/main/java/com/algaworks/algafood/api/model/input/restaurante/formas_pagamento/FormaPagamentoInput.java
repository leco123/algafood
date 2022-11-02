package com.algaworks.algafood.api.model.input.restaurante.formas_pagamento;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class FormaPagamentoInput {

    @NotBlank
    private String descricao;

}