package com.algaworks.algafood.api.v1.model.input.usuario;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UsuarioInputId {

    @NotNull
    private Long id;
}