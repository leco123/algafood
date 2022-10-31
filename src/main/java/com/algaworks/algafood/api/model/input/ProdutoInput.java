package com.algaworks.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoInput {

    @NotBlank
    private String nomeProduto;

    @NotBlank
    private String descricaoProduto;

    @NotNull
    @PositiveOrZero
    private BigDecimal precoProduto;

    @NotNull
    private Boolean ativo;
}
