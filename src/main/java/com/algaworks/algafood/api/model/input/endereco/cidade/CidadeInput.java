package com.algaworks.algafood.api.model.input.endereco.cidade;

import com.algaworks.algafood.api.model.input.endereco.estado.EstadoIdInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CidadeInput {

    @ApiModelProperty(example = "Pato Branco", required = true)
    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInput estado;

}