package com.algaworks.algafood.api.model.input.usuario.grupo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class GrupoInput {

    @NotBlank
    private String nome;

}
