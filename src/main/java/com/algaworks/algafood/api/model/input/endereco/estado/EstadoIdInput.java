package com.algaworks.algafood.api.model.input.endereco.estado;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class EstadoIdInput {

    @ApiModelProperty(example = "1")
    @NotNull
    private Long id;
}
