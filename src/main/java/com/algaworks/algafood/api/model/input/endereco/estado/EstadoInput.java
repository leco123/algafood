package com.algaworks.algafood.api.model.input.endereco.estado;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class EstadoInput {

    @NotBlank
    private String nome;

}
