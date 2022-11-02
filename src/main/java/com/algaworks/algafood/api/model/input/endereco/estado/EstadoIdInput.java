package com.algaworks.algafood.api.model.input.endereco.estado;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class EstadoIdInput {

    @NotNull
    private Long id;
}
