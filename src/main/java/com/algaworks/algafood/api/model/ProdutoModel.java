package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoModel {
    private Long id;
    private String nomeProduto;
    private String descricaoProduto;
    private BigDecimal precoProduto;
    private Boolean ativo;
}
