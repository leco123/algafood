package com.algaworks.algafood.api.v1.model.input.endereco.cidade;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CidadeIdInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;
}
