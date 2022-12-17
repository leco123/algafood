package com.algaworks.algafood.api.v1.model.input.endereco.estado;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class EstadoInput {

    @ApiModelProperty(example = "Minas Gerais", required = true)
    @NotBlank
    private String nome;

}
