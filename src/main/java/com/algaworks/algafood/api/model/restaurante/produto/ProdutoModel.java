package com.algaworks.algafood.api.model.restaurante.produto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Setter
@Getter
public class ProdutoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Espetinho de Cupim")
    private String nome;

    @ApiModelProperty(example = "Acompanha farinha, mandioca e vinagrete")
    private String descricao;

    @ApiModelProperty(example = "12.50")
    private BigDecimal preco;

    @ApiModelProperty(example = "true")
    private Boolean ativo;
}
